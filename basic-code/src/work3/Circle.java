package work3;

public class Circle implements Shape {
    private double radius;
    private static final double PI = 3.1415926;
    
    public Circle() {
        this.radius = 1.0;
    }
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calArea() {
        return PI * radius * radius;
    }
    
    @Override
    public double calPerimeter() {
        return 2 * PI * radius;
    }
    
    public double getRadius() {
        return radius;
    }
    
    public void setRadius(double radius) {
        this.radius = radius;
    }
}
