package jichengtest.Jihe;

import java.util.ArrayList;
import java.util.List;

//集合List
public class ListDemo1 {
    public static void main(String[] args) {
        //创建集合对象
        List<Integer> list = new ArrayList<>();
        //添加元素
        list.add(3);
        list.add(4);
        list.add(3);
        list.add(2);
        for (int i = 0; i < list.size() ;i++) {
            System.out.print(list.get(i)+" ");
        }
        System.out.println();
        //迭代器
        for (Integer i : list) {
            System.out.print(i+" ");
        }
    }
}
