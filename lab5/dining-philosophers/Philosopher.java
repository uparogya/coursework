/**
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */


public class Philosopher implements Runnable
{
	private DiningServer server;
	private int       philNum;
	
	public Philosopher(DiningServer server, int philNum)
	{
		this.server = server;
		this.philNum = philNum;
	}
	
	private void thinking()
	{
        SleepUtilities.nap();
	}
	
	private void eating()
	{
		SleepUtilities.nap();
    }
	
	// philosophers alternate between thinking and eating
	public void run()
	{
		while (true)
		{
			System.out.println("philosopher " + philNum + " is thinking.");
			thinking();
			
			System.out.println("philosopher " + philNum + " is hungry.");
			
			server.takeForks(philNum);
			
			System.out.println("philosopher " + philNum + " is eating.");
			
			eating();
			
			System.out.println("philosopher " + philNum + " is done eating.");
			
			server.returnForks(philNum);
		}
	}
}
