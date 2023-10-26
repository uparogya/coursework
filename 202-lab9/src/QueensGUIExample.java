/**
 * Demonstration of using the QueensGUI object to display a completed game board.
 * 
 */
public class QueensGUIExample {
	public static void main(String[] args) throws InterruptedException{
		QueensGUI gui = new QueensGUI(4, 4);
		
		char[][] toDraw = new char[][]{{'*','*','Q','*'},{'Q','*','*','*'},{'*','*','*','Q'},{'*','Q','*','*'}};
		gui.draw(toDraw);
	}
}
