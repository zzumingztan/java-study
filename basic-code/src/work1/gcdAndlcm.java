package work1;
//键盘录入两个整数，求最大公约数和最小公倍数
import java.util.Scanner;
public class gcdAndlcm {
    public static void main(String[] args)
    {
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入两个整数：");
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    System.out.println("最大公约数是：" + GCD(a, b));
    System.out.println("最小公倍数是：" + LCM(a, b));
    scanner.close();
    }
    public static int GCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return GCD(b, a % b);
    }
    public static int LCM(int a, int b) {
        return a * b / GCD(a,b);
    }
}
