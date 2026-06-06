package work5;

public class Person
{
    private String name;
    private int age;

    // 构造方法
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 重写 toString
    @Override
    public String toString() {
        return "Person{name=" + name + ", age=" + age + "}";
    }

    // 重写 equals
    @Override
    public boolean equals(Object obj)
    {
        // 1. 地址相同直接返回 true
        if (this == obj)
            return true;

        // 2. 为空 或 类型不同返回 false
        if (obj == null || getClass() != obj.getClass())
            return false;

        // 3. 强制转型
        Person other = (Person) obj;

        // 4. 比较成员变量
        return age == other.age && name.equals(other.name);
    }

    // 重写 hashCode
//    @Override
//    public int hashCode()
//    {
//        int result = name.hashCode();
//        result = 31 * result + age;
//        return result;
//    }
}