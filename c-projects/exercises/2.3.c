#include<stdio.h>

int main()
{
	double a, b, c;
	printf("输入三角形三边长");
	scanf("%lf %lf %lf",&a,&b,&c);
	 if (a* a + b * b == c * c || b * b + c * c == a * a || c * c + a * a == b * b)
	{
		printf("该三角形是直角三角形");
		
	}
	else
	{
		printf("该三角形不是直角三角形");
	}


	return 0;
}
