/**
 * Example program illustrating iterative and recursive
 * solutions to the incredibly simply problem of
 * printing out a list of numbers from n ... 1
 */

public class Recurse
{
    /**
     * Iterative version that prints out
     * a list of numbers from n .. 0
     */
    public static void iterative(int n) {
        for (int i = n; i >= 0; i--)
            System.out.println(i + " ");
    }

    /**
     * Recursive version that prints out
     * a list of numbers from 0 .. n
     */
    public static void recursive(int n) {
        if (n == 0)
            return;
        else {
            System.out.println("Before = " + n);
            recursive(n - 1);
            System.out.println("After = " + n);
        }
    }

    /**
     * To be completed in class
     */
    public static int factorial(int n) {
        if(n == 0){
            return 1;
        }
        return n * factorial(n-1);
//        int value = 1;
//        for(int i = 1; i <= n; i++){
//            value = value * i;
//        }
//        return value;
    }

    /**
     * To be completed in class
     */
    public static void displayForwards(int[] values, int position) {

    }

    /**
     * To be completed in class
     */
    public static void displayBackwards(int[] values, int position) {

    }


    public static void main(String args[]) {
//        if (args.length != 1) {
//            System.out.println("Usage: java Recurse <n>");
//            System.exit(0);
//        }

        System.out.println(factorial(5));

		/*
		System.out.println(factorial(Integer.parseInt(args[0])));

		System.out.println();

		int[] nums = {5,10,15,20,25};

		System.out.println("Forwards ....");
		displayForwards(nums,0);

		System.out.println("Backwards ....");
		displayBackwards(nums,0);
		*/

    }
}