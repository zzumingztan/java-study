#include<stdio.h>
int main()
{
	double a, b, c;
	printf("输入三者的值");
	scanf("%lf %lf %lf", &a, &b, &c);
	printf("三者的和为%.3lf", a + b + c);


	return 0;
}
