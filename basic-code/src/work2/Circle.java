package work2;

public class Circle
{
    private double radius;
    private static final double PI=3.1415926;
    //无参构造
    public Circle()
    {
        this.radius=1.0;
    }
    //有参构造（指定半径）
     public Circle(double radius) {
         this.radius = radius;
     }
     //计算周长
    public double getPerimeter(){
        return 2*PI*radius;
    }
    public double getArea(){
        return PI*radius*radius;
    }
    //get与set获取和修改半径
    public double getRadius(){
        return radius;
    }
    public void  setRadius(double radius){
        this.radius=radius;
    }

}
