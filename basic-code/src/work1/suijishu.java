package work1;

//生成100个0-99的随机整数，找出最大的和最小的，并统计大于50的个数
public class suijishu
{
    public static void main(String[] args)
    {
        int max = 0;
        int min = 100;
        int count = 0;
        System.out.println("生成数据");
        for(int i=1;i<=100;i++)
        {
            int n = (int)(Math.random()*100);
            System.out.print(n+"\t");
            if(i%10 == 0)
                System.out.println();
            if(n > max)
                max = n;
            if(n < min)
                min = n;
            if(n > 50)
                count++;
        }
        System.out.println("最大值："+max);
        System.out.println("最小值："+min);
        System.out.println("大于50的个数："+count);
    }
}
