package work2;
public  abstract class Shape
{
    public abstract double calArea();
}

class Triangle extends Shape
{
    private double base;
    private double height;
    //构造方法
    public Triangle(double base,double height)
    {
        this.base=base;
        this.height=height;
    }
    //重写

    public double calArea()
    {
        return 0.5*base*height;
    }
}
class Rectangle extends Shape
{
    private double width;
    private double height;
    //构造方法
    public Rectangle(double width,double height)
    {
        this.width=width;
        this.height=height;
    }
    //重写
    public double calArea()
    {
        return width*height;
    }
}
class Main
{
    public  void main(String[] args)
    {
        Shape shape1=new Triangle(5,6);
        System.out.println("三角形的面积为："+shape1.calArea());
        Shape shape2= new Rectangle(7,8);
        System.out.println("矩形的面积为："+shape2.calArea());
    }
}
