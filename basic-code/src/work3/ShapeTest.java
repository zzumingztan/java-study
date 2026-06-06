package work3;

public class ShapeTest {
    public static void main(String[] args) {
        Circle circle1 = new Circle();
        System.out.println("圆1（默认半径）：");
        System.out.println("半径：" + circle1.getRadius());
        System.out.printf("周长：%.2f\n", circle1.calPerimeter());
        System.out.printf("面积：%.2f\n\n", circle1.calArea());
        
        Circle circle2 = new Circle(5.0);
        System.out.println("圆2（半径为5）：");
        System.out.println("半径：" + circle2.getRadius());
        System.out.printf("周长：%.2f\n", circle2.calPerimeter());
        System.out.printf("面积：%.2f\n\n", circle2.calArea());
        
        circle2.setRadius(10.0);
        System.out.println("圆2（修改半径为10）：");
        System.out.println("半径：" + circle2.getRadius());
        System.out.printf("周长：%.2f\n", circle2.calPerimeter());
        System.out.printf("面积：%.2f\n\n", circle2.calArea());
        
        Rectangle rect1 = new Rectangle(4.0, 6.0);
        System.out.println("长方形1（宽4，高6）：");
        System.out.printf("周长：%.2f\n", rect1.calPerimeter());
        System.out.printf("面积：%.2f\n\n", rect1.calArea());
        
        Rectangle rect2 = new Rectangle(10.0, 20.0);
        System.out.println("长方形2（宽10，高20）：");
        System.out.printf("周长：%.2f\n", rect2.calPerimeter());
        System.out.printf("面积：%.2f\n", rect2.calArea());
    }
}
