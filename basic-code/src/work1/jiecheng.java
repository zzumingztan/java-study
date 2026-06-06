package work1;

import java.util.Scanner;

//计算2，4，6，10的阶乘
public class jiecheng
{
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入4个数字，计算2，4，6，10的阶乘");
        for (int i = 1; i <= 4; i++) {
            int n = scanner.nextInt();
            int result = factorial(n);
            System.out.println(n + "!" + "=" + result);
        }
    }
    public static int factorial(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
