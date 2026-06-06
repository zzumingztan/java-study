#include<stdio.h>
int main()
{
	float num1;
	float num2;
	printf("请输入两个数字");
	scanf("%f %f",&num1,&num2);
	if(num2!=0)
	{
	printf("两个数的和是：%.2f\n",num1+num2);
	printf("两个数的差是：%.2f\n",num1-num2);
	printf("两个数的积是：%.2f\n",num1*num2);
	printf("两个数的商是：%.2f\n",num1/num2);}
	else
	{
    printf("两个数的和是：%.2f\n",num1+num2);
	printf("两个数的差是：%.2f\n",num1-num2);
	printf("两个数的积是：%.2f\n",num1*num2);
	printf("除数不能为0");
		
	}
	return 0;
}
