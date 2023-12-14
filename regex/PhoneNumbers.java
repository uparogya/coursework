import java.util.Scanner;
import java.util.regex.*;

/*
A class to validate phone numbers in the US American format. Examples:
- (123) 456-7890
- (123)456-7890
- 123-456-7890
- 123/456-7890
- 1234567890

RegEx:
^\(?[1-9][0-9]{2}[/) ]?[0-9]{3}-?[0-9]{4}

*/

public class PhoneNumbers {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(
        "^ *\\(?([1-9]\\d{2})[/)]? ?(\\d{3})-?(\\d{4}) *$"
    );

    public static void main (String[] args) {
        var in = new Scanner(System.in);
        System.out.print("Enter a phone number: \n\t");
        String input = in.nextLine();

        var match = PHONE_NUMBER_PATTERN.matcher(input);
        if(match.matches()) {
            System.out.println("VALID");
            /*
                .group(0) gives the entire matched string
                .group(1) is the contents of the *first* ()
            */
            String areaCode = match.group(1);
            String exchangeCode = match.group(2);
            String stationNumber = match.group(3);
            System.out.printf("Area Code: %s \nExchange Code: %s \nStation Number: %s \n",areaCode,exchangeCode,stationNumber);
        }else{
            System.out.println("INVALID");
        }
    }

}
