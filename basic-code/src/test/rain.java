package test;

public class rain
{
    public static void main(String[] args)
    {
        int arr[]={0,1,0,2,1,0,1,3,2,1,2,1};
        //从左看
        int leftMax[]=new int[arr.length];
        int temp=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(temp>arr[i])
                leftMax[i]=temp;
                else {
                leftMax[i]=arr[i];
                temp=arr[i];
            }
        }
        //从右看
        int rightMax[]=new int[arr.length];
        temp=arr[arr.length-1];
        for (int i = arr.length-1; i >=0; i--) {
            if(temp>arr[i])
                rightMax[i]=temp;
            else {
                rightMax[i]=arr[i];
                temp=arr[i];
            }
        }
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            sum+=Math.min(leftMax[i],rightMax[i])-arr[i];
        }
        System.out.println(sum);
    }
}
