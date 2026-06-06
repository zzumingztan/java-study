package text2;

public class Test {
    public static void main(String[] args)
    {
        Person p = new Person("xiaohuihui",20,"男");
        System.out.println(p.getName()+p.getAge()+p.getGender());
        Bicycle b = new Bicycle("凤凰",10);
        p.driver(b);
        Car c = new Car("保时捷",300, 4, 4, "V8双涡轮增压");
        p.driver(c);

    }
}
