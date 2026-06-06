/**
 * 实验十：Java多线程程序设计（一）
 * 主测试类：分别使用Thread类和Runnable接口创建线程计算阶乘
 * 姓名：李润豪
 * 学号：202415060613
 */
public class FactorialMultiThreadTest {

    public static void main(String[] args) {
        // 定义要计算阶乘的整数数组
        int[] numbers = {5, 8, 10, 12, 15};

        System.out.println("============================================");
        System.out.println("  实验十：Java多线程程序设计（一）");
        System.out.println("  多线程计算阶乘");
        System.out.println("============================================\n");

        // ===== 方式一：继承Thread类 =====
        System.out.println("===== 方式一：继承Thread类 =====\n");

        FactorialThread[] threadArray = new FactorialThread[numbers.length];

        // 创建并启动线程
        for (int i = 0; i < numbers.length; i++) {
            threadArray[i] = new FactorialThread(numbers[i]);
            threadArray[i].setName("Thread-" + (i + 1));
            threadArray[i].start();
        }

        // 等待所有线程执行完毕
        for (FactorialThread ft : threadArray) {
            try {
                ft.join();
            } catch (InterruptedException e) {
                System.err.println("线程被中断：" + e.getMessage());
            }
        }

        // 输出Thread类方式的所有结果汇总
        System.out.println("\n--- Thread类方式结果汇总 ---");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i] + "! = " + threadArray[i].getResult());
        }

        // ===== 方式二：实现Runnable接口 =====
        System.out.println("\n===== 方式二：实现Runnable接口 =====\n");

        FactorialRunnable[] runnableArray = new FactorialRunnable[numbers.length];
        Thread[] threads = new Thread[numbers.length];

        // 创建Runnable对象，并用Thread包装启动
        for (int i = 0; i < numbers.length; i++) {
            runnableArray[i] = new FactorialRunnable(numbers[i]);
            threads[i] = new Thread(runnableArray[i], "Runnable-Thread-" + (i + 1));
            threads[i].start();
        }

        // 等待所有线程执行完毕
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println("线程被中断：" + e.getMessage());
            }
        }

        // 输出Runnable接口方式的所有结果汇总
        System.out.println("\n--- Runnable接口方式结果汇总 ---");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i] + "! = " + runnableArray[i].getResult());
        }

        // ===== 对比两种方式 =====
        System.out.println("\n===== 两种创建线程方式的对比 =====");
        System.out.println("1. 继承Thread类：");
        System.out.println("   - 简单直接，适合简单的线程任务");
        System.out.println("   - 由于Java单继承限制，不能再继承其他类");
        System.out.println("   - 线程与任务耦合度高");
        System.out.println();
        System.out.println("2. 实现Runnable接口：");
        System.out.println("   - 更灵活，可以同时继承其他类");
        System.out.println("   - 实现资源共享更方便");
        System.out.println("   - 符合面向对象设计原则，推荐使用");
        System.out.println();
        System.out.println("结论：推荐使用Runnable接口方式创建线程。");

        System.out.println("\n============================================");
        System.out.println("  实验完成");
        System.out.println("============================================");
    }
}
