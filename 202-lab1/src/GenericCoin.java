/**
 * @author Arogya Upadhyaya
 *
 * Some comments about what this code does.
 */
public class GenericCoin {
	public enum CoinSide {HEADS, TAILS};
	private CoinSide side;
	public GenericCoin() {
		side = CoinSide.HEADS;
	}

	public void setToHeads() {
		side = CoinSide.HEADS;
	}

	public void setToTails() {
		side = CoinSide.TAILS;
	}

	public void toss() {
		double randomNumber = java.lang.Math.random();
		if (randomNumber >= 0.5) {
			side = CoinSide.HEADS;
		} else {
			side = CoinSide.TAILS;
		}
	}

	public boolean isHeads(){
		if(side == CoinSide.HEADS) return true;
		return false;
	}

	public boolean isTails(){
		if(side == CoinSide.TAILS) return true;
		return false;
	}

	public String toString(){
		if(side == CoinSide.HEADS) return "Heads";
		else return "Tails";
	}


}