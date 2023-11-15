
/*
CLASS: AsteroidsGame
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
 */
import java.awt.*;

public class Asteroids extends Game {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;

	static int counter = 0;

	private Asteroid asteroid_1;
	private Point[] asteroid_1_points = {
			new Point(0,0),
			new Point(70,0),
			new Point(70,30),
			new Point(0,30)
	};
	private Point position_1 = new Point(600,200);

	private Asteroid asteroid_2;
	private Point[] asteroid_2_points = {
			new Point(0,0),
			new Point(70,20),
			new Point(50,40),
			new Point(20,35),
			new Point(10,20)
	};
	private Point position_2 = new Point(100,100);

	private Asteroid asteroid_3;
	private Point[] asteroid_3_points = {
			new Point(0,0),
			new Point(70,20),
			new Point(50,40)
	};
	private Point position_3 = new Point(300,40);

	public Asteroids() {
		super("Asteroids!", SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setFocusable(true);
		this.requestFocus();
		this.asteroid_1 = new Asteroid(asteroid_1_points,position_1,0);
		this.asteroid_2 = new Asteroid(asteroid_2_points,position_2,30);
		this.asteroid_3 = new Asteroid(asteroid_3_points,position_3,15);
	}

	public void paint(Graphics brush) {
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter,10,10);

		asteroid_1.paint(brush,Color.white);
		asteroid_2.paint(brush,Color.white);
		asteroid_2.move();
		asteroid_3.paint(brush,Color.white);
		asteroid_3.move();
	}

	public static void main (String[] args) {
		Asteroids a = new Asteroids();
		a.repaint();
	}
}