
/*
CLASS: Point
DESCRIPTION: Ah, if only real-life classes were this straight-forward. We'll
             use 'Point' throughout the program to store and access 
             coordinates.
Original code by Dan Leyzberg and Art Simon
 */

public class Point implements Cloneable {
	// ordinarily we would make these private, but it makes for cleaner code
	// by allowing them to be accessed in other classes
	double x,y;

	public Point(double x, double y) 
	{ 
		this.x = x; this.y = y; 
	}

	public Point clone() {
		return new Point(x, y);
	}
}