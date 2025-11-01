/**
 * DoWorkCV.java
 *
 * Figure 6.38
 *
 * This implements the doWork() method using condition variables.
 *
 */

import java.util.concurrent.locks.*;

public class DoWorkCV implements DoWork
{
	private int turn;
	private Lock lock;
	private Condition[] condVars;

	public DoWorkCV() {
		turn = 0;
		lock = new ReentrantLock();
		condVars = new Condition[TestIt.NUMBER_OF_THREADS];

		for (int i = 0; i < TestIt.NUMBER_OF_THREADS; i++)
			condVars[i] = lock.newCondition();
	}


	// myNumber is the number of the thread that wishes to do some work
	public void doWork(int myNumber) {
		lock.lock();

		try {
			// if it's not my turn, then wait 
			// until I'm signaled
			if (myNumber != turn)
				condVars[myNumber].await();

			// do some work for awhile
			System.out.println("Worker " + myNumber + " will do some work");
			SleepUtilities.nap();

			// now signal to the next waiting thread
			turn = (turn + 1) % TestIt.NUMBER_OF_THREADS;
			condVars[turn].signal();
		}
		catch (InterruptedException ie) { }
		finally {
			lock.unlock();
		}
	}

}
