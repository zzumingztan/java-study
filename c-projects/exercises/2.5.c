#include<stdio.h>
int main()
{
	float g;
	printf("请输入成绩");
	scanf("%f", &g);
	 if (g > 100||g<0)
	{
		printf("请输入正确成绩");
	}
	else if (g>=90&&g<=100)
	{
		printf("成绩为A");
	}
	else if (g >= 80 && g <= 90)
	{
		printf("成绩为B");
	}
	else if (g >= 70 && g <= 80)
	{
		printf("成绩为C");
	}
	else if (g >= 60 && g <= 70)
	{
		printf("成绩为D");
	}
	else 
	{
		printf("成绩为F");
	}
	return 0;
}
