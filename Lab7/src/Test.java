/**
 * Test harness for LRU and FIFO page replacement algorithms
 *
 * Usage:
 *	java [-Ddebug] Test <reference string size> <number of page frames>
 */

public class Test
{
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Test <number of page frames>");
            System.exit(-1);
        }

        // reference string for provided example
        //int[] referenceString = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};

        // reference string for in-class exercise
        int[] referenceString =  {3,4,7,7,5,4,0,5,4,3,3,6,0,7,5,6,1,4,2,6};

        /** Use either the FIFO or LRU algorithms */
        ReplacementAlgorithm fifo = new FIFO(Integer.valueOf(args[0]));
        ReplacementAlgorithm lru = new LRU(Integer.valueOf(args[0]));

        // output a message when inserting a page
        System.out.println("LRU");
        for (int i = 0; i < referenceString.length; i++) {
            lru.insert(referenceString[i]);
        }

        // output a message when inserting a page
        System.out.println("FIFO");
        for (int i = 0; i < referenceString.length; i++) {
            fifo.insert(referenceString[i]);
        }

        // report the total number of page faults
        System.out.println("LRU faults = " + lru.getPageFaultCount());
        System.out.println("FIFO faults = " + fifo.getPageFaultCount());
    }
}