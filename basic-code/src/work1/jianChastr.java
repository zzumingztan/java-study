package work1;

import java.util.Scanner;
import java.util.Vector;

public class jianChastr
{
        public static void main (String[]args)
        {

            Scanner sc = new Scanner(System.in);
            System.out.println("请输入一段待检测的字符串：");
            String str = sc.nextLine();
            char[] s = str.toCharArray(); // 转换为字符数组，方便操作

            // 使用 Vector 存储所有找到的起始索引位置
            Vector<Integer> positions = new Vector<>();
            for (int i = 0; i <= s.length - 3; i++)
            {
            // 核心逻辑：直接判断 i, i+1, i+2 位
            if (s[i] == 'a' && s[i + 1] == 'b' && s[i + 2] == 'c')
            {
                positions.add(i); // 记录匹配成功的起始位置
            }
            }

            // 输出结果
            if (positions.isEmpty())
            {
                System.out.println("未发现目标子串 'abc'");
            }
            else
            {
                System.out.println("发现 'abc' 的位置共有 " + positions.size() + " 处：");
                for (Integer pos : positions)
                {
                    System.out.println("起始索引: " + pos);
                }
            }
        }
}
