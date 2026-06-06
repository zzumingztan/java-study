/**
 * 实验十一：Java多线程程序设计（二）
 * 票类 —— 存票和售票线程共享的数据对象
 * 姓名：李润豪
 * 学号：202415060613
 */
public class Ticket {
    private int ticketCount;            // 当前票数
    private final int MAX_TICKETS;      // 最大票数（票仓容量）

    /**
     * 构造方法
     * @param maxTickets 票仓最大容量
     */
    public Ticket(int maxTickets) {
        this.ticketCount = 0;
        this.MAX_TICKETS = maxTickets;
    }

    /**
     * 存票方法（供生产者线程调用）
     * 如果票已满，则当前线程等待
     * @param amount 存入的票数
     * @throws InterruptedException 线程被中断时抛出
     */
    public synchronized void save(int amount) throws InterruptedException {
        while (ticketCount + amount > MAX_TICKETS) {
            System.out.println("【存票】票仓已满（当前" + ticketCount
                    + "张），" + Thread.currentThread().getName()
                    + " 等待售票...");
            wait();  // 票仓满，等待消费者买票
        }

        ticketCount += amount;
        System.out.println("【存票】" + Thread.currentThread().getName()
                + " 存入 " + amount + " 张票，当前票数：" + ticketCount);

        notifyAll();  // 通知等待的售票线程
    }

    /**
     * 售票方法（供消费者线程调用）
     * 如果票不足，则当前线程等待
     * @param amount 购买的票数
     * @throws InterruptedException 线程被中断时抛出
     */
    public synchronized void sell(int amount) throws InterruptedException {
        while (ticketCount < amount) {
            System.out.println("【售票】票数不足（当前" + ticketCount
                    + "张），" + Thread.currentThread().getName()
                    + " 等待存票...");
            wait();  // 票不足，等待生产者存票
        }

        ticketCount -= amount;
        System.out.println("【售票】" + Thread.currentThread().getName()
                + " 售出 " + amount + " 张票，当前票数：" + ticketCount);

        notifyAll();  // 通知等待的存票线程
    }

    /**
     * 获取当前票数
     * @return 当前剩余票数
     */
    public synchronized int getTicketCount() {
        return ticketCount;
    }
}
