import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AlgorithmTest {

    ArrayList<Integer> coins = new ArrayList<Integer>();

    public AlgorithmTest(String file) throws FileNotFoundException {
        Scanner inFile = new Scanner(new File(file));
        while (inFile.hasNext()) {
            coins.add(inFile.nextInt());
        }
        inFile.close();
    }

    public void printCoins() {
        System.out.println("+++++++++++");
        System.out.print("Coins:    ");
        for (int i = 0; i < coins.size(); i++) {
            System.out.print(" " + coins.get(i) + " ");
        }
        System.out.print("\nPosition: ");
        for (int i = 0; i < coins.size(); i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        System.out.println("+++++++++++");
    }

    public void playGame() {
        System.out.println("Let's play the coin game!");
        int computerSum = 0;
        int humanSum = 0;
        Scanner keyboard = new Scanner(System.in);

        while (coins.size() != 0){
            int computerChoice; //stores computer choice
            printCoins(); //initial display of leftover coins
            System.out.println("Human: " + humanSum + " | Computer: " + computerSum + "\n"); //displays score

            int first = 0; int last = coins.size() - 1; //first and last index initialization

            //if the computer selects index 0, next choice for the user will be either first+1 or last
            int nc1 = coins.get(first + 1) + coins.get(last);
            //if the computer selects index last, next choice for the user will be either first or last-1
            int nc2 = coins.get(first) + coins.get(last - 1);

            //adding the two choices will give the maximum of what user may get
            //thus selecting the least sum will ensure that the computer will always be on the top
            if(nc1 < nc2){
                computerChoice = first;
            }else{
                computerChoice = last;
            }

            computerSum += coins.get(computerChoice);

            System.out.println("Computer chose value from position " + computerChoice);
            System.out.println("Computer's value was " + coins.get(computerChoice));
            coins.remove(computerChoice);

            printCoins();

            //choice for the user
            System.out.print("Enter Your Choice (0/"+ (coins.size()-1) +"): ");
            int humanChoice = keyboard.nextInt();
            if(humanChoice != 0 && humanChoice != coins.size()-1){
                System.err.println("Only 1 or " + (coins.size()-1) + " are allowed!");
                System.exit(1);
            }
            humanSum += coins.get(humanChoice);
            coins.remove(humanChoice);
            System.out.println("");

            //to prevent array out of bound, automatically choosing the highest value when only 2 left
            if(coins.size() == 2){
                printCoins();
                System.out.println("Human: " + humanSum + " | Computer: " + computerSum + "\n");
                if(coins.get(0) > coins.get(1)){
                    computerChoice = 0;
                    humanChoice = 1;
                }else {
                    humanChoice = 0;
                    computerChoice = 1;
                }
                computerSum += coins.get(computerChoice);
                humanSum += coins.get(humanChoice);
                System.out.println("Computer chose value from position " + computerChoice);
                System.out.println("Computer's value was " + coins.get(computerChoice));
                System.out.println("Only coin left is " + coins.get(humanChoice));
                coins.remove(0);coins.remove(0); //removing all the coins
            }
        }

        System.out.println("\n\n+++++++++++++++\n");
        System.out.println("Game Over! Final Score: ");
        System.out.println("Human: " + humanSum + " | Computer: " + computerSum);
        keyboard.close();



    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Provide a file on the command line");
            System.exit(0);
        }

        AlgorithmTest game = new AlgorithmTest(args[0]);
//        AlgorithmTest game = new AlgorithmTest("file5.txt");

        game.playGame();
    }

}