package jichengtest.Jihe;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.addAll;

//collection测试
public class CollectionTest {
    public static void main(String[] args) {
        //创建集合对象
        Collection c = new ArrayList();
        //添加元素
        c.add("hello");
        c.add("world");
        c.add("java");
        c.add("java");
        System.out.println(c);
        //获取元素个数
        System.out.println(c.size());
        // 判断空集合
        System.out.println(c.isEmpty());
        //判断对象是不是在集合里
        System.out.println(c.contains("java"));
        System.out.println(c.contains("java1"));
        //判断方法接受的集合是否是c的子集
        Collection c1 = new ArrayList();
        c1.add("hello2");
        c1.add("world1");
        System.out.println(c.containsAll(c1));
        //向集合添加一个集合所有
        Collection c2 = new ArrayList();
        c2.add("hello3");
        c2.add("world2");
        c.addAll(c2);
        System.out.println(c);
        //删除集合元素
        c.remove("hello");
        System.out.println(c);
        //删除集合所有元素
        c.clear();
        System.out.println(c);
    }
}
