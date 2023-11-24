import java.awt.*;

public class Asteroid extends Polygon{

    public Asteroid(Point[] shape, Point position, double rotation) {
        super(shape, position, rotation);
    }

    @Override
    public void paint(Graphics brush, Color color) {
        Point[] points = super.getPoints();
        int[] xCoors = new int[points.length];
        int[] yCoors = new int[points.length];

        int counter = 0;
        for (Point p : points) {
            xCoors[counter] = (int) p.x;
            yCoors[counter] = (int) p.y;
            counter++;
        }

        brush.setColor(color);
        brush.drawPolygon(xCoors,yCoors, points.length);
    }

    @Override
    public void move() {
//        Point new_point = new Point(position.x + 0.5, position.y + 0.5);
//        super.position = new_point;
        position.x += Math.cos(Math.toRadians(rotation));
        position.y += Math.sin(Math.toRadians(rotation));

        // TO-DO
        /**
         * Have asteroid move back on the screen once they go off the screen.
         *
         * You will do this by checking the value of position.x and position.y
         * and determine if they are outside of the bounds of the screen
         * specified by Asteroids.SCREEN_WIDTH and Asteroids.SCREEN_HEIGHT
         * If so, reposition the x and/or y coordinates.
         *
         * i.e. if an asteroid moves off the right-side of the screen
         * have it re-appear on the left side of the screen.
         */
        boolean positionChanged = false;
        Point new_point = null;
        if(position.x > Asteroids.SCREEN_WIDTH){
            new_point = new Point(0, position.y);
            positionChanged = true;
        } else if (position.x < 0) {
            new_point = new Point(Asteroids.SCREEN_WIDTH, position.y);
            positionChanged = true;
        }

        if(position.y > Asteroids.SCREEN_HEIGHT){
            new_point = new Point(position.x, 0);
            positionChanged = true;
        } else if (position.y < 0) {
            new_point = new Point(position.x, Asteroids.SCREEN_HEIGHT);
            positionChanged = true;
        }

        if(positionChanged){
            super.position = new_point;
        }
    }
}
