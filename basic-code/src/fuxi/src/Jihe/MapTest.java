package jichengtest.Jihe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

//map测试
public class MapTest {
    public static void main(String[] args) {
        //创建map对象
        Map map = new HashMap();
        //添加元素
        map.put("1","hello");
        map.put("2","world");
        map.put("3","java");
        map.put("4","java");
        System.out.println(map);
        //个数
        System.out.println(map.size());
        //判断是否为空
        System.out.println(map.isEmpty());
        //判断是否包含某个key
        System.out.println(map.containsKey("1"));
        //判断是否包含某个value
        System.out.println(map.containsValue("hello"));
        //获取某个key对应的value
        System.out.println(map.get("1"));
        //获取value的key
        System.out.println(map.keySet());
        //返回map中所有值的collection对象collection<V>values
        System.out.println(map.values());
        //返回map中所有键值对对象entrySet
        System.out.println(map.entrySet());
        //remove删除
        System.out.println(map.remove("1"));
        System.out.println(map);
        System.out.println(map.remove("5"));
        System.out.println(map);
        //clear 清空
        map.clear();
        System.out.println(map);


    }
}
