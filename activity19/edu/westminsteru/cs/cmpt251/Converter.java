/**
 * Converter.java
 *
 * A program that does conversion between °F and °C.
 */

package edu.westminsteru.cs.cmpt251;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Converter {

    private static final Pattern TEMPERATURE_PATTERN = Pattern.compile(
        "^\\s*([+-]?\\d+(\\.\\d+)?)\\s*°?([CcFf])\\s*$"
    );

    private static double fahrenheitToCelsius(double F) {
        return 5.0 / 9 * (F - 32);
    }

    private static double celsiusToFahrenheit(double C) {
        return 9.0 / 5 * C + 32;
    }

    private static void doConversion(String input) {
        Matcher m = TEMPERATURE_PATTERN.matcher(input);
        if (m.matches()) {
            double number = Double.parseDouble(m.group(1));
            String unit = m.group(3);

            switch (unit) {
            case "F":
            case "f":
                double C = fahrenheitToCelsius(number);
                System.out.printf("\t%f°F = %f°C\n\n", number, C);
                break;
            case "C":
            case "c":
                double F = celsiusToFahrenheit(number);
                System.out.printf("\t%f°C = %f°F\n\n", number, F);
                break;
            }
        } else {
            System.err.println("I don’t understand that temperature! 🙁\n");
        }
    }

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a temperature (e.g. 14 F or -9.8°C) or hit Enter to quit: ");
            String line = in.nextLine();

            if (line.isBlank())
                break;

            doConversion(line);
        }
    }
}
