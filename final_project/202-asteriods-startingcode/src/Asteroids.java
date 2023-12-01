
/*
CLASS: AsteroidsGame
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
 */
import java.awt.*;
import java.util.*;

public class Asteroids extends Game {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;

	static int counter = 0;

	private java.util.List<Asteroid> randomAsteroids = new ArrayList<Asteroid>();

	private Ship ship;

	//some variables for collision effect on the ship
	private int shipColorSustainValue = 0; // temp variables that counts to default the ship color
	final int shipColorMaxSustainValue = 800; // how long should the effect be visible
	private boolean shipHasCollided = false;

	private Star[] stars;

	public Asteroids() {
		super("Asteroids!", SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setFocusable(true);
		this.requestFocus();

		randomAsteroids = createRandomAsteroids(10,60,30);

		ship = createShip();
		this.addKeyListener(ship);

		stars = createStars(150,7);
	}

	private Ship createShip(){
		Point[] shipShape = {
				new Point(0,75),
				new Point(38.25,75),
				new Point(23.25,67.5),
				new Point(42,67.5),

				new Point(55,56.25),

				new Point(42,45),
				new Point(23.25,45),
				new Point(38.25,37.5),
				new Point(0,37.5),

				new Point(15,56.25)
		};

		Point startingPosition = new Point((width -Ship.SHIP_WIDTH)/2, (height - Ship.SHIP_HEIGHT)/2);
		int startingRotation = 0; // Start facing to the right

		return new Ship(shipShape, startingPosition, startingRotation);
	}

	//  Create an array of random asteroids
	private java.util.List<Asteroid> createRandomAsteroids(int numberOfAsteroids, int maxAsteroidWidth,
														   int minAsteroidWidth) {
		java.util.List<Asteroid> asteroids = new ArrayList<>(numberOfAsteroids);

		for(int i = 0; i < numberOfAsteroids; ++i) {
			// Create random asteroids by sampling points on a circle
			// Find the radius first.
			int radius = (int) (Math.random() * maxAsteroidWidth);
			if(radius < minAsteroidWidth) {
				radius += minAsteroidWidth;
			}
			// Find the circles angle
			double angle = (Math.random() * Math.PI * 1.0/2.0);
			if(angle < Math.PI * 1.0/5.0) {
				angle += Math.PI * 1.0/5.0;
			}
			// Sample and store points around that circle
			ArrayList<Point> asteroidSides = new ArrayList<Point>();
			double originalAngle = angle;
			while(angle < 2*Math.PI) {
				double x = Math.cos(angle) * radius;
				double y = Math.sin(angle) * radius;
				asteroidSides.add(new Point(x, y));
				angle += originalAngle;
			}
			// Set everything up to create the asteroid
			Point[] inSides = asteroidSides.toArray(new Point[asteroidSides.size()]);
			Point inPosition = new Point(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
			double inRotation = Math.random() * 360;
			asteroids.add(new Asteroid(inSides, inPosition, inRotation));
		}
		return asteroids;
	}

	public void paint(Graphics brush) {
		Color shipColor = null;
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);

		for (Star star : stars) {
			star.paint(brush,null);
		}

		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter,10,10);

		for (Asteroid asteroid : randomAsteroids) {
			asteroid.paint(brush,Color.white);
			asteroid.move();
			shipHasCollided = asteroid.collision(ship);
			shipColor = dynamicallyColorShip();
		}
		ship.paint(brush,shipColor);
		ship.move();
	}

	//this is my helper method to make the ship blink after it collides
	private Color dynamicallyColorShip() {
		Color shipColor = Color.magenta;
		if (shipHasCollided && shipColorSustainValue == 0){
			shipColorSustainValue++;
		}
		if(shipColorSustainValue>0 && shipColorSustainValue<=shipColorMaxSustainValue){
			if(shipColorSustainValue%9 == 0){
				shipColor = Color.white;
			}else{
				shipColor = Color.red;
			}
			shipColorSustainValue++;
		}else if(shipColorSustainValue > shipColorMaxSustainValue){
			shipColorSustainValue = 0;
			shipHasCollided = false;
			shipColor = Color.magenta;
		}

		return shipColor;
	}

	// Create a certain number of stars with a given max radius
	public Star[] createStars(int numberOfStars, int maxRadius) {
		Star[] stars = new Star[numberOfStars];
		for(int i = 0; i < numberOfStars; ++i) {
			Point center = new Point
					(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);


			int radius = (int) (Math.random() * maxRadius);
			if(radius < 1) {
				radius = 1;
			}
			stars[i] = new Star(center, radius);
		}


		return stars;
	}


	public static void main (String[] args) {
		Asteroids a = new Asteroids();
		a.repaint();
	}
}