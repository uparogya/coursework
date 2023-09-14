/**
 * A class that represents a three dimensional point.
 *
 * AROGYA UPADHYAYA & WILLIAM MYERS
 *
 */
public class ThreeDimensionalPoint {
    double x;
    double y;
    double z;

    public ThreeDimensionalPoint() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public ThreeDimensionalPoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return this.x;
    }
    public double shiftX(double amount) {
        return this.x + amount;
    }
    public void rotateX(int theta){
        double tempY = this.y;
        double tempZ = this.z;
        this.x = this.x;
        this.y = (tempY * java.lang.Math.cos(theta)) - (tempZ * java.lang.Math.sin(theta));
        this.z = (tempY * java.lang.Math.sin(theta)) + (tempZ * java.lang.Math.cos(theta));
    }

    public double getY() {
        return this.y;
    }
    public double shiftY(double amount) {
        return this.y + amount;
    }
    public void rotateY(int theta){
        double tempX = this.x;
        double tempZ = this.z;
        this.x = (tempX * java.lang.Math.cos(theta)) + (tempZ * java.lang.Math.sin(theta));
        this.y = this.y;
        this.z = -(tempX * java.lang.Math.sin(theta)) + (tempZ * java.lang.Math.cos(theta));
    }

    public double getZ() {
        return this.z;
    }
    public double shiftZ(double amount) {
        return this.z + amount;
    }
    public void rotateZ(int theta){
        double tempX = this.x;
        double tempY = this.y;
        this.x = (tempX * java.lang.Math.cos(theta)) - (tempY * java.lang.Math.sin(theta));
        this.y = (tempX * java.lang.Math.sin(theta)) + (tempY * java.lang.Math.cos(theta));
        this.z = this.z;
    }
}
