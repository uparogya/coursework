/**
 * Packages.java
 *
 * A demonstration of using classes from other packages.
 */

import java.util.Scanner;

public class Packages {

    public static void main(String... args) {
        java.util.List<Double> numbers = new java.util.ArrayList<>();
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a number (or hit Enter to quit): ");

            String line = in.nextLine();
            if (line.isBlank())
                break;
                
            double x = Double.parseDouble(line);
            numbers.add(x);
        }

        System.out.println("\nYou entered the following numbers:\n" + numbers);
    }

}
