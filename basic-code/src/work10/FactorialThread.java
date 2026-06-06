/**
 * 使用Thread类实现阶乘计算的多线程程序
 * 实验十：Java多线程程序设计（一）
 * 姓名：李润豪
 * 学号：202415060613
 */
public class FactorialThread extends Thread {
    private int number;      // 要计算阶乘的整数
    private long result;     // 存储计算结果

    /**
     * 构造方法
     * @param number 要计算阶乘的整数
     */
    public FactorialThread(int number) {
        this.number = number;
        this.result = 1;
    }

    /**
     * 线程执行体：计算阶乘并输出结果
     */
    @Override
    public void run() {
        System.out.println("【Thread类】线程 " + getName()
                + " 开始计算 " + number + " 的阶乘...");
        long startTime = System.currentTimeMillis();

        result = factorial(number);

        long endTime = System.currentTimeMillis();
        System.out.println("【Thread类】线程 " + getName()
                + " 计算完成：" + number + "! = " + result);
        System.out.println("【Thread类】线程 " + getName()
                + " 耗时：" + (endTime - startTime) + " 毫秒");
    }

    /**
     * 递归方法计算阶乘
     * @param n 非负整数
     * @return n的阶乘
     */
    private long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("阶乘参数不能为负数");
        }
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * 获取计算结果
     * @return 阶乘结果
     */
    public long getResult() {
        return result;
    }
}
