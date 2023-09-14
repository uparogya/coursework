import java.util.Scanner;
public class CoinGame {
    GenericCoin coin = new GenericCoin();
    int tosses = 0;
    int correctChoice = 0;
    public void flipCoin() {
        System.out.print("Heads or Tails? [H/T]: ");
        Scanner userInput = new Scanner(System.in);
        String choice = userInput.nextLine();
        coin.toss();
        tosses++;
        if((coin.isHeads() && choice.equals("H")) || (coin.isTails() && choice.equals("T"))) {
            correctChoice++;
            System.out.println("Correct!");
        }else{
            System.out.println("Incorrect");
        }

        System.out.print("Do you wish to continue? [Y/N]: ");
        Scanner continueOption = new Scanner(System.in);

        String continuationOption = continueOption.nextLine();
        if(continuationOption.equals("Y")){
            this.flipCoin();
        }else{
            System.out.println("------------- GAME ENDED -------------");
            System.out.println("Correct Guesses   : " + correctChoice);
            System.out.println("Total Coin Tossed : " + tosses);
            double winRate = (correctChoice/tosses) * 100;
            System.out.println("Win Rate          : " + winRate + "%");
        }
    }
}