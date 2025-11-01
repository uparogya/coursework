/**
 * This program creates a separate thread by implementing the Runnable interface.
 */

/**
 * The instance of the shared object
 */

class Sum
{
    private int sum;

    public int get() {
        return sum;
    }

    public void set(int sum) {
        this.sum = sum;
    }
}

/**
 * This runs as a separate Java thread.
 *
 * This performs a summation from 1 .. upper 
 */
class Collatz implements Runnable
{
    private int upper;
    private Sum sumValue;

    public Collatz(int upper) {
        this.upper = upper;
    }

    public void run() {
        while (upper != 1)
        {
            if (upper%2 == 0)
            {
                upper = upper / 2;
            } else {
                upper = (3*upper) + 1;
            }
            System.out.println(upper);
        }
    }
}

public class Driver
{
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage java Driver <integer>");
            System.exit(0);
        }

        if (Integer.parseInt(args[0]) < 0) {
            System.err.println(args[0] + " must be >= 0");
            System.exit(0);
        }

        // Create the shared object
        int upper = Integer.parseInt(args[0]);

        Thread worker = new Thread(new Collatz(upper));
        worker.start();

        try {
            worker.join();
        } catch (InterruptedException ie) { System.err.println(ie); }
    }
}