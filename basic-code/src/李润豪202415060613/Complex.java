//package work2;
//class Complex {
//    private double real;
//    private double image;
//    //构造方法
//    public Complex(double real, double image) {
//        this.real = real;
//        this.image = image;
//    }
//    //加法
//    public Complex add(Complex other)
//    {
//        double r = this.real + other.real;
//        double i = this.image + other.image;
//        return new Complex(r, i);
//    }
//    //减法
//    public Complex subtract(Complex other)
//        {
//        double r = this.real - other.real;
//        double i = this.image - other.image;
//        return new Complex(r, i);
//    }
//    //乘法
//    public Complex multiply(Complex other)
//        {
//        double r = this.real * other.real - this.image * other.image;
//        double i = this.real * other.image + this.image * other.real;
//        return new Complex(r, i);
//    }
//
//    //toString格式化输出
//    @Override
//    public String toString() {
//        if(image >= 0){
//            return real + " + " + image + "i";
//        }
//        return real + " - " + (-image) + "i";
//    }
//}
////测试类
//class TestComplex {
//    public static void main(String[] args) {
//        Complex c1 = new Complex(1, 2);
//        Complex c2 = new Complex(3, -4);
//        System.out.println(c1);
//        System.out.println(c2);
//        System.out.println(c1.add(c2));
//        System.out.println(c1.subtract(c2));
//        System.out.println(c1.multiply(c2));
//    }
//}
