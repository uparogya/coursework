import java.util.Scanner;

public class JavaExample {

    private static int add2(int x) {
        return x + 2;
    }

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);
        System.out.print("What is your favorite number? ");
        int n = in.nextInt();

        int better = add2(n);
        System.out.printf("Oh yeah? Well, I think %d is better! >:P\n", better);
    }
}
