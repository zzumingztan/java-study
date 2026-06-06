package jichengtest.Jihe;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        //创建队列对象
        Queue<String> queue = new LinkedList<>();
        //添加元素
        queue.offer("hello");
        queue.offer("world");
        queue.offer("java");
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
    }
}
