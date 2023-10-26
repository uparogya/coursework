/**
 * QueensGUI.java
 * 
 * A graphical representation of a n x n chessboard. The draw() method
 * is passed an n x n array of char with values either '*' (for empty)
 * or 'Q' (representing a Queen.) The board is then drawn with a graphical
 * representation of a Queen.
 * 
 * @author Sasha Gagne 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class QueensGUI {

	private JFrame mFrame;
	private QueensPanel mQueensPanel;
	private int mWidth;
	private int mHeight;

	/**
	 * Create an object of this class to pop up a window, then call myBoard.draw(char[][]) to have it draw the given board.
	 * @param width The width of the chess board
	 * @param height The height of the chess board
	 */
	public QueensGUI(int width, int height){
		mWidth = width;
		mHeight = height;
		
		mFrame = new JFrame("N Queens");
		
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mQueensPanel = new QueensPanel(mWidth, mHeight);
		mFrame.add(mQueensPanel);
		mFrame.setResizable(false);
		
		mFrame.pack();
		mFrame.setSize(mWidth*50, mHeight*50 + 20);
		mFrame.setVisible(true);
	}
	
	/** 
	 * Call this to redraw the board with the supplied char[][]
	 * @param toDraw The char[][] filled with 'Q' or '*' representing the board
	 */
	public void draw(char[][] toDraw){
		mQueensPanel.mQueens = toDraw;
		mQueensPanel.repaint();
	}
	
	// Our private Panel class onto which we do the drawing
	
	@SuppressWarnings("serial")
	private class QueensPanel extends JPanel{
		
		// The blue background color, in RGB
		private final Color mBlue = new Color(228,226,252);
		
		private int mBlocksWide, mBlocksHigh;
		private char[][] mQueens;
		private BufferedImage mQueenImage;
		private boolean mLoadedImage;
		
		private QueensPanel(int width, int height){
			setLayout(null);
			mQueens = new char[width][height];
			mBlocksWide = width;
			mBlocksHigh = height;
			
			// Try to load in queen.png
			// If we can't find it, we'll just draw a big 'Q'
			// NOTE: This will load in queen.png from whatever folder/package this class is contained in. (Normally /src/)
			InputStream input = this.getClass().getResourceAsStream("queen.png");
			try {
				mQueenImage = ImageIO.read(input);
				mLoadedImage = true;
			} catch (Exception e) {
				mLoadedImage = false;
			}
		}
		
		@Override
		public void paintComponent(Graphics g){
			drawBackground(g);
			drawImages(g);
		}
		
		// Draw each Queen in the array mQueens onto the board
		public void drawImages(Graphics g){
			g.setFont(new Font("Arial", Font.BOLD, 36));
			
			for(int i = 0; i < mBlocksWide; i++){
				for(int j = 0; j < mBlocksHigh; j++){
					
					if(mQueens[j][i] == 'Q'){
						
						// Draw the image, or a big 'Q' if no image exists
						if(mLoadedImage)
							g.drawImage(mQueenImage, i*50 + 5, j*50 + 5, null);
						else
							g.drawString("Q", i*50 + 10, j*50 + 40);
					}
				}
			}
		}
		
		// Draw the checkerboard background
		private void drawBackground(Graphics g){
			for(int i = 0; i < mBlocksWide; i++){
				for(int j = 0; j < mBlocksHigh; j++){
					
					if((i+j) % 2 == 0)
						g.setColor(Color.white);
					else
						g.setColor(mBlue);
					
					g.fillRect(i*50, j*50, 50, 50);
					
					// Draw the outline
					g.setColor(Color.black);
					g.drawRect(i*50, j*50, 50, 50);
				}
			}
		}
	}
}
