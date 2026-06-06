package work1;

import java.util.Scanner;

public class shuZu
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        // 1. 定义行数
        System.out.print("请输入数组的总行数: ");
        int rows = sc.nextInt();
        int[][] a = new int[rows][];
        // 2. 为每一行单独分配列空间并赋值
        for (int i = 0; i < rows; i++)
        {
            System.out.printf("请输入第 %d 行的列数: ", i);
            int cols = sc.nextInt();
            a[i] = new int[cols];
            for (int j = 0; j < cols; j++) {
                System.out.printf("请输入第 %d 行的列 %d 的值: ", i, j);
                a[i][j] = sc.nextInt();
            }
        }
        // 3. 打印
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
