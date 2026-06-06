package work4;

public class GenericMethod2 {
    public static <T> void StaticMethod(T t){
        System.out.print(t);
    }
    public <T> void OtherMethod(T t){
        System.out.print(t);
    }
    public  static void main(String[] args){
        GenericMethod2.StaticMethod("abcdefg\n");//方法一
        GenericMethod2.<String>StaticMethod("abcdefg\n");//方法二
        GenericMethod2 staticFans = new GenericMethod2();
        staticFans.OtherMethod(new Integer(123) + '\n');//方法一
        staticFans.<Integer>OtherMethod(new Integer(123) + '\n');//方法二
    }
}
