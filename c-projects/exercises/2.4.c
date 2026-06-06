#include<stdio.h>
#include<math.h>
int main()
{
	double a, b, c;
	printf("请输入方程系数\n");
	scanf("%lf %lf %lf", &a, &b, &c);
	double pan = sqrt (b * b - 4 * a * c);
	 if (a == 0)
	{
		 if (b != 0)
		{
			printf("方程的根为%.2lf\n", -c / b);
		}
		else if (b == 0)
		{
			printf("方程无限解\n");
		}
	}
	else {
		 if (b * b - 4 * a * c < 0)
		{
			printf("方程无实数根\n");
		}
		else if (b * b - 4 * a * c ==0)
		{
			printf("方程有一个根为%.2lf\n", -b / 2 * a);
		}
		else if(b * b - 4 * a * c>0)
		{
			printf("方程有两个根，分别为%.2lf,%.2lf", (-b + pan) / 2 * a, (-b - pan) / 2 * a);
		}



	}






	return 0;
}

