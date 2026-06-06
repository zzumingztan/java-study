package work2;

public class ShapeTester
{
    public static void main(String[] args)
    {
        //无参构造
        Circle circle1=new Circle();
        System.out.println("圆1（半径为默认）：");
        System.out.println("半径："+circle1.getRadius());
        System.out.println("周长："+circle1.getPerimeter());
        System.out.println("面积："+circle1.getArea());
        //带参构造
        Circle circle2=new Circle(5);
        System.out.println("圆2（半径为5）：");
        System.out.println("半径："+circle2.getRadius());
        System.out.printf("周长：%2f\n",circle2.getPerimeter());
        System.out.printf("面积：%2f\n\n",circle2.getArea());
        //set与get
        circle2.setRadius(10);
        System.out.println("圆2（半径为10）：");
        System.out.println("半径："+circle2.getRadius());
        System.out.printf("周长：%2f\n",circle2.getPerimeter());
        System.out.printf("面积：%2f\n\n",circle2.getArea());
    }

}
