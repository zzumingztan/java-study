/**
 * 实验十一：Java多线程程序设计（二）
 * 使用Runnable接口实现存票任务（生产者）
 * 姓名：李润豪
 * 学号：202415060613
 */
public class TicketSaverRunnable implements Runnable {
    private Ticket ticket;       // 共享的票对象
    private int saveAmount;      // 每次存入的票数
    private int saveTimes;       // 存票次数

    /**
     * 构造方法
     * @param ticket     共享的票对象
     * @param saveAmount 每次存入的票数
     * @param saveTimes  存票次数
     */
    public TicketSaverRunnable(Ticket ticket, int saveAmount, int saveTimes) {
        this.ticket = ticket;
        this.saveAmount = saveAmount;
        this.saveTimes = saveTimes;
    }

    /**
     * 线程执行体：循环存票
     */
    @Override
    public void run() {
        try {
            for (int i = 1; i <= saveTimes; i++) {
                System.out.println(">>> [Runnable] "
                        + Thread.currentThread().getName()
                        + " 第" + i + "次准备存票...");
                ticket.save(saveAmount);
                // 模拟存票的时间间隔
                Thread.sleep((long) (Math.random() * 500 + 200));
            }
            System.out.println(">>> [Runnable] "
                    + Thread.currentThread().getName() + " 存票任务完成！");
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName()
                    + " 被中断：" + e.getMessage());
        }
    }
}
