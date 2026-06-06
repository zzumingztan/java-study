package work5;

public class TestPerson
{
    public static void main(String[] args)
    {
        Person p1 = new Person("张三", 20);
        Person p2 = new Person("张三", 20);
        Person p3 = p1;

        System.out.println("p1 == p2：" + (p1 == p2));
        System.out.println("p1.equals(p2)：" + p1.equals(p2));
        System.out.println("p1的hashCode：" + p1.hashCode());
        System.out.println("p2的hashCode：" + p2.hashCode());

        System.out.println("p1 == p3：" + (p1 == p3));
        System.out.println("p1.equals(p3)：" + p1.equals(p3));
        System.out.println("p3的hashCode：" + p3.hashCode());

        System.out.println(p1);
    }
}
