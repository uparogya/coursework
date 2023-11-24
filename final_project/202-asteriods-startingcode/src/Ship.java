import java.awt.*;

public class Ship extends Polygon{
    public Ship(Point[] shape, Point position, double rotation) {
        super(shape, position, rotation);
    }

    @Override
    public void paint(Graphics brush, Color color) {
        Point[] points = super.getPoints();
        brush.setColor(color);
        int[] xCoors = new int[points.length];
        int[] yCoors = new int[points.length];
        int counter = 0;
        for (Point p : points) {
            xCoors[counter] = (int) p.x;
            yCoors[counter] = (int) p.y;
            counter++;
        }
        brush.fillPolygon(xCoors,yCoors, points.length);
    }

    @Override
    public void move() {
        position.x += 5;
        if(position.x > Asteroids.SCREEN_WIDTH){
            Point new_point = new Point(0, position.y);
            super.position = new_point;
        }
    }
}
