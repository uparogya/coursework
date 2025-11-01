/**
 * DoWorkNotify.java
 * 
 * This class is used to demonstrate the problems with using notify().
 * The program will shortly hang as the
 * incorrect thread (i.e. the thread whose turn is not next!) receives the
 * notification. 
 *
 * Figure 6.32
 *
 */

public class DoWorkNotify implements DoWork 
{
	private int turn = 0;
	
	// myNumber is the number of the thread that wishes to do some work
	public synchronized void doWork(int myNumber) {
		while (turn != myNumber) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		
		// do some work for awhile
		System.out.println("Worker " + myNumber + " will do some work");
		SleepUtilities.nap();
		
		// ok, we're finished. Now indicate to the next waiting
		// thread that it is their turn to do some work.
	
        turn = 	(turn + 1) % TestIt.NUMBER_OF_THREADS;
        
        // notify the next waiting thread	
        notify();
	}
	
}
