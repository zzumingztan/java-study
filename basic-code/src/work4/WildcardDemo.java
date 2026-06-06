package work4;

import java.util.ArrayList;
import java.util.List;

/**
 * 通配符泛型示例
 * 演示 <?>, <? extends T>, <? super T> 的使用场景
 */
public class WildcardDemo {

    // ==================== 1. 无界通配符 <?> ====================
    
    /**
     * 打印任意类型的 GeneralType
     * 使用 <?> 表示可以接受任何类型参数
     */
    public static void printGenericType(GeneralType<?> type) {
        System.out.println("值: " + type.getObject() + 
                          ", 类型: " + type.getObject().getClass().getSimpleName());
    }

    // ==================== 2. 上界通配符 <? extends T> ====================
    
    /**
     * 计算数值的平方
     * 使用 <? extends Number> 表示接受 Number 及其所有子类
     * (Integer, Double, Float, Long 等)
     */
    public static double calculateSquare(GeneralType<? extends Number> number) {
        double value = number.getObject().doubleValue();
        return value * value;
    }

    /**
     * 从列表中求和（生产者场景）
     * List<? extends Number> 表示可以从列表中读取 Number 类型的数据
     */
    public static double sumList(List<? extends Number> numbers) {
        double sum = 0;
        for (Number num : numbers) {
            sum += num.doubleValue();  // ✅ 可以读取为 Number
        }
        return sum;
    }

    // ==================== 3. 下界通配符 <? super T> ====================
    
    /**
     * 向容器中存储整数
     * 使用 <? super Integer> 表示容器可以是 Integer、Number 或 Object 类型
     */
    public static void storeIntegers(GeneralType<? super Integer> container, int... values) {
        // 这里只是示意，实际 GeneralType 只能存一个值
        System.out.print("准备存储整数: ");
        for (int val : values) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    /**
     * 向列表中添加整数（消费者场景）
     * List<? super Integer> 表示可以向列表中添加 Integer 类型的数据
     */
    public static void addIntegersToList(List<? super Integer> list, int... values) {
        for (int val : values) {
            list.add(val);  // ✅ 可以安全地添加 Integer
        }
    }

    // ==================== 主方法测试 ====================
    
    public static void main(String[] args) {
        
        System.out.println("========== 1. 无界通配符 <?> ==========");
        GeneralType<String> strType = new GeneralType<>("Hello Java");
        GeneralType<Integer> intType = new GeneralType<>(100);
        GeneralType<Double> doubleType = new GeneralType<>(3.14);
        
        printGenericType(strType);    // ✅ 接受 String
        printGenericType(intType);    // ✅ 接受 Integer
        printGenericType(doubleType); // ✅ 接受 Double
        
        System.out.println("\n========== 2. 上界通配符 <? extends Number> ==========");
        
        // 测试单个数值计算
        System.out.println("10 的平方: " + calculateSquare(new GeneralType<Integer>(10)));
        System.out.println("3.5 的平方: " + calculateSquare(new GeneralType<Double>(3.5)));
        System.out.println("2.5f 的平方: " + calculateSquare(new GeneralType<Float>(2.5f)));
        
        // 测试列表求和
        List<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(20);
        intList.add(30);
        System.out.println("Integer 列表求和: " + sumList(intList));
        
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.5);
        doubleList.add(2.5);
        doubleList.add(3.0);
        System.out.println("Double 列表求和: " + sumList(doubleList));
        
        System.out.println("\n========== 3. 下界通配符 <? super Integer> ==========");
        
        // 测试向不同父类类型的列表中添加数据
        List<Integer> integerList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        
        addIntegersToList(integerList, 1, 2, 3);
        addIntegersToList(numberList, 4, 5, 6);
        addIntegersToList(objectList, 7, 8, 9);
        
        System.out.println("Integer 列表: " + integerList);
        System.out.println("Number 列表: " + numberList);
        System.out.println("Object 列表: " + objectList);
        
        System.out.println("\n========== 4. PECS 原则总结 ==========");
        System.out.println("Producer Extends: 从集合读取数据用 <? extends T>");
        System.out.println("Consumer Super: 向集合写入数据用 <? super T>");
        System.out.println("如果既要读又要写，直接使用具体类型 <T>");
    }
}
