#include<stdio.h>
int main()
{
	float a,b,c,d,e,f,g;
	printf("请赋值变量");
	scanf("%f %f %f %f %f %f %f",&a,&b,&c,&d,&e,&f,&g);
	float result=(a+b*c)/(d*e-f*g);
	if(d*e-f*g!=0)
	{
		printf("分式的结果为%.2f",result);
	}
	else
	{
		printf("分母不能为0");
	}
	
	
	return 0;
}
