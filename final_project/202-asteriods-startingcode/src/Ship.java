import javax.script.ScriptEngine;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Ship extends Polygon implements java.awt.event.KeyListener{
    public static final int SHIP_WIDTH = 40;
    public static final int SHIP_HEIGHT = 25;

    public Ship(Point[] shape, Point position, double rotation) {
        super(shape, position, rotation);
    }

    private enum Movement {
        forward,
        left,
        right,
        none
    }
    private Movement currentDirection = Movement.none;

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
        switch (currentDirection){
            case forward:
                position.x += 3 * Math.cos(Math.toRadians(rotation));
                position.y += 3 * Math.sin(Math.toRadians(rotation));
                break;
            case left:
                super.rotation -= 3;
                break;
            case right:
                super.rotation += 3;
                break;
            case none:
                break;
        }

        if(position.y > Asteroids.SCREEN_HEIGHT){
            Point new_point = new Point(position.x, 0);
            super.position = new_point;
        }else if(position.y < 0){
            Point new_point = new Point(position.x, Asteroids.SCREEN_HEIGHT);
            super.position = new_point;
        }
        if(position.x < 0){
            Point new_point = new Point(Asteroids.SCREEN_WIDTH, position.y);
            super.position = new_point;
        }else if(position.x > Asteroids.SCREEN_WIDTH){
            Point new_point = new Point(0, position.y);
            super.position = new_point;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                currentDirection = Movement.forward;
                break;
            case KeyEvent.VK_LEFT:
                currentDirection = Movement.left;
                break;
            case KeyEvent.VK_RIGHT:
                currentDirection = Movement.right;
                break;
            default:
                currentDirection = Movement.none;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT){
            currentDirection = Movement.none;
        }
    }
}
