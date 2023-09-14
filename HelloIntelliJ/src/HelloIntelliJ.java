import java.util.Scanner;
import java.util.Random;
public class HelloIntelliJ {
    public static void main(String[] args) {
        System.out.print("Enter the number: ");
        Scanner userInput = new Scanner(System.in);
        int number = userInput.nextInt();
        evenlyDivisible(number);

        String[] famousCSFolks = {
                "Ada Lovelace",
                "Grace Hopper",
                "Joan Clarke",
                "Jean Jennings Bartik",
                "Anita Borg",
                "Frances Elizabeth Allen",
                "Lois Haibt",
                "Margaret Hamilton"
        };

        System.out.println("Three famous computer scientists are: ");
        famous(famousCSFolks, 0);
    }

    public static void evenlyDivisible(int number) {
        for (int i = 1; i < number; i++) {
            if(i % 3 == 0){
                System.out.println(i);
            }
        }
    }

    public static void famous(String[] folks, int count) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(folks.length);
        System.out.println(folks[randomNumber]);
        count += 1; if(count < 3) famous(folks, count);
    }
}
