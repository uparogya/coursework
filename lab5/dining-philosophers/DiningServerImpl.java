/**
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl  implements DiningServer
{  
	// different philosopher states
	enum State {THINKING, HUNGRY, EATING};
	
	// number of philosophers
	public static final int NUMBER_OF_PHILOSOPHERS = 5;
	
	// array to record each philosopher's state
	private State[] state;

	private Condition[] condVars;

	private Lock lock;
	
	public DiningServerImpl()
	{
		// array of philosopher's state
		state = new State[NUMBER_OF_PHILOSOPHERS];

		lock = new ReentrantLock();
		condVars = new Condition[NUMBER_OF_PHILOSOPHERS];
		
		// originally all philosopher's are thinking
		for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
			state[i] = State.THINKING;
			condVars[i] = lock.newCondition();
		}
	}
	
	// called by a philosopher when they wish to eat 
	public void takeForks(int pnum)  {
		lock.lock();
		state[pnum] = State.HUNGRY;
		test(pnum);
		try {
			if (state[pnum] != State.EATING) {
				condVars[pnum].await();
			}
		}
		catch (InterruptedException ie) { }
		finally {
			lock.unlock();
		}
	}
	
	// called by a philosopher when they are finished eating 
	public void returnForks(int pnum) {
		state[pnum] = State.THINKING;
		test((pnum + 4) % 5);
		test((pnum + 1) % 5);
	}
	
	private void test(int i)
	{
		// in other words ... if I'm hungry and my left and
		// right nieghbors aren't eating, then let me eat!
		lock.lock();
		try {
			if (((state[(i + 4) % 5]) != State.EATING) && (state[i] == State.HUNGRY) && (state[(i + 1) % 5] != State.EATING)) {
				state[i] = State.EATING;
				condVars[i].signal();
			}
		}
		finally {
			lock.unlock();
		}
		
	}
	
	// return the index of the left neighbor of philosopher i
	private int leftNeighbor(int i)
	{
        return ((i + 1) % NUMBER_OF_PHILOSOPHERS);
    }
	
	// return the index of the right neighbor of philosopher i
	private int rightNeighbor(int i)
	{
        return ((i + 4) % NUMBER_OF_PHILOSOPHERS);
	}
}
