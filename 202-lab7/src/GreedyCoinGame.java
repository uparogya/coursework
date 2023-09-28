/**
 * Plays Greedy Coin game such that the computer never loses.
 * 
 * [ YOUR NAME GOES HERE]
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GreedyCoinGame {

	ArrayList<Integer> coins = new ArrayList<Integer>();
	
	public GreedyCoinGame(String file) throws FileNotFoundException {
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

		while(true) {
			int computerChoice;
			int leftSum = 0;
			int rightSum = 0;

			//Prints the coin choices for computer and human
			printCoins();

			//Prints the total score
			System.out.println("Human: " + humanSum + " | Computer: " + computerSum + "\n");

			//The red blue strategy
			for (int i = 0; i < coins.size(); i++) {
				if (i % 2 == 0) {
					leftSum += coins.get(i);
				} else {
					rightSum += coins.get(i);
				}
			}

			//Choosing the best one for computer
			if (leftSum >= rightSum) {
				computerChoice = 0;
			} else {
				computerChoice = coins.size()-1;
			}

			//Adding the score for computer
			computerSum += coins.get(computerChoice);

			//Letting the user know what computer chose
			System.out.println("Computer chose value from position " + computerChoice);
			System.out.println("Computer's value was " + coins.get(computerChoice));
			coins.remove(computerChoice); //Removing the computer's choice

			//Breaking the loop of only one coin is left
			if(coins.size() == 1){
				System.out.println("Only coin left is " + coins.get(0));
				humanSum += coins.get(0);
				break;
			}

			//Prints the leftover coins for the human
			printCoins();

			//Taking input of human's choice
			System.out.print("Enter Your Choice (0/"+ (coins.size()-1) +"): ");
			int humanChoice = keyboard.nextInt();
			if(humanChoice != 0 && humanChoice != coins.size()-1){
				System.err.println("Only 1 or " + (coins.size()-1) + " are allowed!");
				System.exit(1);
			}

			//Adding the score for human
			humanSum += coins.get(humanChoice);
			coins.remove(humanChoice); //Removing the human's choice
			System.out.println("");
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

		GreedyCoinGame game = new GreedyCoinGame(args[0]);

		game.playGame();
	}

}
