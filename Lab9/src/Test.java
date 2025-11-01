/**
 * Test harness for disk scheduling algorithms
 *
 * Usage:
 *	java Test <cylinder string size> <initial cylinder position>
 *
 * For those that modify this so it works with an elevator-style algorithm
 * where the direction needs to be specified, use a Java property, such as
 *
 *  java -Ddir=UP <cylinder string size> <initial cylinder position>
 *
 * Details showing how to use system properties are provided below.
 */

public class Test
{
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Test <cylinder string size> <initial cylinder position>");
            System.exit(0);
        }

        /**
         * For those implementing an elevator algoirthm, the initial
         * direction of the algorithm must be set. This can be done by
         * using a system property. If this program is run using the 
         * -D property, the String direction will be set. For example, if
         * the program was run as
         *      java -Ddirection=UP 1000 53
         *
         * the String direction would be set to "UP".
         * If no property is used at runtime, the value of direction is null.
         */
        String direction = System.getProperty("direction");

        Generator ref = new Generator(Integer.parseInt(args[0]));
        int ip = Integer.parseInt(args[1]);

        //int[] referenceString = ref.getCylinders();

        // below is the reference string example used in the text
        int[] referenceString = {98, 183, 37, 122, 14, 124, 65, 67};

        /**
         * Using this example from the text:
         * FCFS is 640 total head movement,
         * SCAN is 208 total head movement.
         */

        for (int n : referenceString)
            System.out.print(n+",");

        System.out.println();
        DiskScheduler[] algorithms = new DiskScheduler[2];
        algorithms[0]  = new FCFS(referenceString,ip);
        algorithms[1]  = new SCAN(referenceString,ip);

        for (DiskScheduler algorithm : algorithms)
            System.out.println(algorithm.getClass() + " = " + algorithm.serviceRequests());
    }
}