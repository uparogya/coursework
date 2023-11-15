import java.awt.*;

public class Asteroid extends Polygon{

    public Asteroid(Point[] shape, Point position, double rotation) {
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
        brush.drawPolygon(xCoors,yCoors, points.length);
    }

    @Override
    public void move() {
        Point new_point = new Point(position.x + 0.5, position.y + 0.5);
        super.position = new_point;
    }
}
