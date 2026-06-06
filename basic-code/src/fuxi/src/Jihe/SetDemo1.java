package jichengtest.Jihe;

import java.util.HashSet;
import java.util.Set;

//测试Set
public class SetDemo1 {
    public static void main(String[] args){
        //添加元素,乱序不重复
        Set<String> set = new HashSet<>();
        set.add("hello");
        set.add("aello");
        set.add("world");
        set.add("java");
        set.add("java");
        System.out.println(set);
        System.out.println(set.size());
        //删除元素
        set.remove("hello");
        System.out.println(set);
    }
}
