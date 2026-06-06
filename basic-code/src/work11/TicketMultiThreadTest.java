/**
 * 实验十一：Java多线程程序设计（二）
 * 主测试类：分别使用Thread类和Runnable接口创建存票/售票线程，
 * 两个线程共享同一个票类对象
 * 姓名：李润豪
 * 学号：202415060613
 */
public class TicketMultiThreadTest {

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("  实验十一：Java多线程程序设计（二）");
        System.out.println("  多线程模拟存票与售票");
        System.out.println("============================================\n");

        // ===== 方式一：继承Thread类 =====
        System.out.println("===== 方式一：继承Thread类 =====\n");

        // 创建共享票对象（票仓最大容量为10张）
        Ticket ticket1 = new Ticket(10);

        // 创建存票线程：每次存3张，共存6次
        TicketSaver saver = new TicketSaver(ticket1, 3, 6, "存票线程-Thread");

        // 创建售票线程：每次买2张，共买9次
        TicketSeller seller = new TicketSeller(ticket1, 2, 9, "售票线程-Thread");

        // 启动两个线程
        saver.start();
        seller.start();

        // 等待两个线程执行完毕
        try {
            saver.join();
            seller.join();
        } catch (InterruptedException e) {
            System.err.println("主线程被中断：" + e.getMessage());
        }

        System.out.println("\n--- Thread类方式结果汇总 ---");
        System.out.println("最终剩余票数：" + ticket1.getTicketCount() + " 张");

        // 分隔线
        System.out.println("\n============================================\n");

        // ===== 方式二：实现Runnable接口 =====
        System.out.println("===== 方式二：实现Runnable接口 =====\n");

        // 创建新的共享票对象（票仓最大容量为10张）
        Ticket ticket2 = new Ticket(10);

        // 创建Runnable任务对象
        TicketSaverRunnable saverTask = new TicketSaverRunnable(ticket2, 3, 6);
        TicketSellerRunnable sellerTask = new TicketSellerRunnable(ticket2, 2, 9);

        // 用Thread包装Runnable对象
        Thread saverThread = new Thread(saverTask, "存票线程-Runnable");
        Thread sellerThread = new Thread(sellerTask, "售票线程-Runnable");

        // 启动线程
        saverThread.start();
        sellerThread.start();

        // 等待两个线程执行完毕
        try {
            saverThread.join();
            sellerThread.join();
        } catch (InterruptedException e) {
            System.err.println("主线程被中断：" + e.getMessage());
        }

        System.out.println("\n--- Runnable接口方式结果汇总 ---");
        System.out.println("最终剩余票数：" + ticket2.getTicketCount() + " 张");

        // ===== 对比两种方式 =====
        System.out.println("\n===== 两种创建线程方式的对比 =====");
        System.out.println("1. 继承Thread类：");
        System.out.println("   - 简单直接，存票/售票逻辑直接写在Thread子类中");
        System.out.println("   - 由于Java单继承限制，不能再继承其他类");
        System.out.println("   - 线程与任务耦合度高，代码复用性较差");
        System.out.println();
        System.out.println("2. 实现Runnable接口：");
        System.out.println("   - 更灵活，任务类可以同时继承其他类");
        System.out.println("   - 任务与线程分离，同一个Runnable可被多个线程共享");
        System.out.println("   - 符合面向对象设计原则，推荐使用");
        System.out.println();
        System.out.println("3. 线程同步机制：");
        System.out.println("   - 使用synchronized关键字保证存票/售票操作的原子性");
        System.out.println("   - 使用wait()/notifyAll()实现线程间的协调等待");
        System.out.println("   - 当票仓满时存票线程等待，票不足时售票线程等待");
        System.out.println("   - 有效避免了死锁问题");
        System.out.println();
        System.out.println("结论：多线程共享数据时必须使用同步机制保证数据一致性，");
        System.out.println("      推荐使用Runnable接口方式创建线程。");

        System.out.println("\n============================================");
        System.out.println("  实验完成");
        System.out.println("============================================");
    }
}
