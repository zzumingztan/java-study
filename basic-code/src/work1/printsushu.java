package work1;

import static java.lang.Math.sqrt;

//打印 100 以内的素数，5 个一行的打印出来
public class printsushu
{
    public static void main(String[] args)
    {
        int count = 0;
        for(int i=2;i<=100;i++)
            if(isSushu(i))
            {
                System.out.printf("%3d", i);
                count++;
                if(count % 5 == 0)
                {
                    System.out.println();
                }
            }
    }
    public static boolean isSushu(int n)
    {
        if(n<=1)
        {
            return false;
        }
        for(int i=2;i<=sqrt(n);i++)
        {
            if(n%i==0)
            {
                return false;
            }
        }
        return true;
    }
}
