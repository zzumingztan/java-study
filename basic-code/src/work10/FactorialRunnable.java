/**
 * 使用Runnable接口实现阶乘计算的多线程程序
 * 实验十：Java多线程程序设计（一）
 * 姓名：李润豪
 * 学号：202415060613
 */
public class FactorialRunnable implements Runnable {
    private int number;      // 要计算阶乘的整数
    private long result;     // 存储计算结果

    /**
     * 构造方法
     * @param number 要计算阶乘的整数
     */
    public FactorialRunnable(int number) {
        this.number = number;
        this.result = 1;
    }

    /**
     * 线程执行体：计算阶乘并输出结果
     */
    @Override
    public void run() {
        System.out.println("【Runnable接口】线程 "
                + Thread.currentThread().getName()
                + " 开始计算 " + number + " 的阶乘...");
        long startTime = System.currentTimeMillis();

        result = factorialIterative(number);

        long endTime = System.currentTimeMillis();
        System.out.println("【Runnable接口】线程 "
                + Thread.currentThread().getName()
                + " 计算完成：" + number + "! = " + result);
        System.out.println("【Runnable接口】线程 "
                + Thread.currentThread().getName()
                + " 耗时：" + (endTime - startTime) + " 毫秒");
    }

    /**
     * 迭代方法计算阶乘
     * @param n 非负整数
     * @return n的阶乘
     */
    private long factorialIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("阶乘参数不能为负数");
        }
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    /**
     * 获取计算结果
     * @return 阶乘结果
     */
    public long getResult() {
        return result;
    }
}
