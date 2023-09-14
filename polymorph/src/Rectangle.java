public class Rectangle implements Shape{

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    public double getPerimeter() {
        return 2 * (length + width);
    }

    public double getArea() {
        return (length * width);
    }
}
