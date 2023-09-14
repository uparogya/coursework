/**
 * Generate the co-primes from (1,1) to (m,n)
 *
 * AROGYA UPADHYAYA
 * To Run The Commented Code (Argument Version):
 * Uncomment = 11-15
 * Comment = 19-25
 * Run = java src/CoPrimes.java 15 15
 *
 */
import java.util.Scanner;
public class CoPrimes {
	public static void main(String[] args) {
//		if (args.length != 2) {
//			System.err.println("Must be passed two integer values > 0");
//			System.exit(0);
//		}
		
//		int m = Integer.parseInt(args[0]);
//		int n = Integer.parseInt(args[1]);

//		System.out.print("Enter the first coordinate (m) : ");
//		Scanner userInput1 = new Scanner(System.in);
//		int m = userInput1.nextInt();
		int m = Integer.parseInt(args[0]);

//		System.out.print("Enter the second coordinate (n): ");
//		Scanner userInput2 = new Scanner(System.in);
//		int n = userInput2.nextInt();
		int n = Integer.parseInt(args[1]);

		if (m < 1 || n < 1){
			System.err.println("Must be passed two integer values > 0");
			System.exit(0);
		}

		System.out.println("Relatively Positioned Graph (required one): ");
		generatePairsAndPlot(m + 1, n + 1);

		System.out.println();

		System.out.println("Actual Graph (optional one): ");
		generatePairsAndPlotInReverse(m + 1, n + 1);
	}

	public static void generatePairsAndPlot(int m, int n) {
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				// i first generated all (1,1) to (m,n)
				// and then i checked if they have common factor and printed true false for my reference
//				System.out.print("(" + i + ", " + j + ") -> ");
//				System.out.println(hasCommonFactor(i,j));
//				System.out.println("");
//				System.out.println(6%4);
				if (hasCommonFactor(i,j)) System.out.print("   ");
				else System.out.print(" * ");
			}
			System.out.println();
		}
	}

	public static boolean hasCommonFactor(int int1, int int2) {
		// i realized that you gave euclid's solution after i did on my own... so i kept mine
		// code up to 47 is pretty straight forward
		if (int1 == 1 || int2 == 1) return false;
		int remainder;
		if(int1 >= int2) remainder = int1 % int2;
		else remainder = int2 % int1;
		if (remainder == 0) return true;
//		if (int1 % remainder == int2 % remainder) return true;
// 		&& (int1 % remainder == 0 && int2 % remainder == 0)
		// i experimented with this for about half hr... tried different ways... this worked so i kept this... i am just dividing both numbers by the numbers up to remainder to see the common one...
		for (int x = 2; x <= remainder; x++) {
			if (int1 % x == 0 && int2 % x == 0) return true;
		}
		return false;
	}

	public static void generatePairsAndPlotInReverse(int m, int n) {
		for (int i = m - 1; i > 0; i--) { //this printed till 16 so i had to -1 but did it here instead of main for no reason
			for (int j = 1; j < n; j++) {
				if (hasCommonFactor(i,j)) System.out.print("   ");
				else System.out.print(" * ");
			}
			System.out.println();
		}
	}

}