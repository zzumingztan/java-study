#include<stdio.h>
int main()
{
	int a,b,c,d; 
	printf("请输入多项式的系数\n");
	scanf("%d %d %d %d",&a,&b,&c,&d) ;
	float x;
	printf("请输入未知数x的值\n");
	scanf("%f",&x); 
	float result=a*x*x*x*x+b*x*x*x+c*x+d;
	printf("多项式的值是%.1f",result);
	return 0;
}
