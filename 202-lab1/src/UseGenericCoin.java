/**
 * @author YOUR NAME GOED HERE
 * 
 * Some comments about what this code does.
 * 
 */

public class UseGenericCoin {

	public static void main(String[] args) {
		GenericCoin coin1 = new GenericCoin();
		GenericCoin coin2 = new GenericCoin();
		int headCoin1 = 0;
		int headCoin2 = 0;
		for (int i = 0; i < 100; i++){
			coin1.toss();
			coin2.toss();
			if(coin1.isHeads()) headCoin1++;
			if(coin2.isHeads()) headCoin2++;
		}
		System.out.println("Coin 1 landed on heads "+headCoin1+".0% of the time.");
		System.out.println("Coin 2 landed on heads "+headCoin2+".0% of the time.");
		System.out.println("");
		if(headCoin1 > headCoin2){
			System.out.println("The first coin was heads "+ (headCoin1 - headCoin2) +" more times.");
		} else if (headCoin1 < headCoin2) {
			System.out.println("The second coin was heads "+ (headCoin2 - headCoin1) +" more times.");
		} else {
			System.out.println("Both coins had the same number of heads.");
		}

		CoinGame game = new CoinGame();
		game.flipCoin();
	}
		

}
