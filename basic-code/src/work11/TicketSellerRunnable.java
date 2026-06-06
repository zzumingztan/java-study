/**
 * 实验十一：Java多线程程序设计（二）
 * 使用Runnable接口实现售票任务（消费者）
 * 姓名：李润豪
 * 学号：202415060613
 */
public class TicketSellerRunnable implements Runnable {
    private Ticket ticket;       // 共享的票对象
    private int sellAmount;      // 每次售出的票数
    private int sellTimes;       // 售票次数

    /**
     * 构造方法
     * @param ticket     共享的票对象
     * @param sellAmount 每次售出的票数
     * @param sellTimes  售票次数
     */
    public TicketSellerRunnable(Ticket ticket, int sellAmount, int sellTimes) {
        this.ticket = ticket;
        this.sellAmount = sellAmount;
        this.sellTimes = sellTimes;
    }

    /**
     * 线程执行体：循环售票
     */
    @Override
    public void run() {
        try {
            for (int i = 1; i <= sellTimes; i++) {
                System.out.println("<<< [Runnable] "
                        + Thread.currentThread().getName()
                        + " 第" + i + "次准备购票...");
                ticket.sell(sellAmount);
                // 模拟售票的时间间隔
                Thread.sleep((long) (Math.random() * 800 + 300));
            }
            System.out.println("<<< [Runnable] "
                    + Thread.currentThread().getName() + " 售票任务完成！");
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName()
                    + " 被中断：" + e.getMessage());
        }
    }
}
