/**
 * Worker.java
 * 
 * This thread is used to demonstrate the difference between notify() and
 * condition variables.
 */

public class Worker implements Runnable {
	private DoWork pile;
	private String name;
	private int number;

	public Worker(DoWork pile, String name, int number) {
		this.pile = pile;
		this.name = name;
		this.number = number;
	}

	public void run() {
		while (true) {
			SleepUtilities.nap();
			pile.doWork(number);
		}
	}

}
