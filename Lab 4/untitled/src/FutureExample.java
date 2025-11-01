/**
 * CollatzTwo program using exectuors/callable/futures
 */

import java.util.concurrent.*;
import java.util.*;

class CollatzTwo implements Callable<List<Integer>>
{
    private int upper;

//    List<Integer> the_finalist = new ArrayList<Integer>();

    public CollatzTwo(int upper) {
        this.upper = upper;
    }

    public List<Integer> call() {
        List<Integer> the_finalist = new ArrayList<Integer>();
        while (upper != 1)
        {
            if (upper%2 == 0)
            {
                upper = upper / 2;
            } else {
                upper = (3*upper) + 1;
            }
            the_finalist.add(upper);
        }
        return the_finalist;
    }
}


public class FutureExample
{
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Attempt1 <integer>");
            System.exit(0);
        }
        else {
            int upper = Integer.parseInt(args[0]);

            ExecutorService pool = Executors.newSingleThreadExecutor();
            Future<List<Integer>> result = pool.submit(new CollatzTwo(upper));

            try {
                System.out.println("collatz = " + result.get());
            }
            catch (InterruptedException | ExecutionException ie) { }

            // Notice the difference if shutdown() is invoked.

            pool.shutdown();
        }
    }
}