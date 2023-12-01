import java.awt.*;

public class Star extends Circle{
    public Star(Point center, int radius) {
        super(center, radius);
    }

    @Override
    public void paint(Graphics brush, Color color) {
        Color starColor = null;
        int x = (int) (Math.random() * 10);
        if(x%2 == 0){
            starColor = Color.white;
        } else if (x%2 != 0 && x%3 == 0) {
            starColor = Color.gray;
        } else if (x == 5) {
            starColor = Color.black;
        } else{
            starColor = Color.lightGray;
        }
        brush.setColor(starColor);
        brush.fillOval((int)center.x,(int)center.y,radius,radius);
    }

    @Override
    public void move() {}
}
