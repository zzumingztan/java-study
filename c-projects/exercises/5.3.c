#include<stdio.h>
#include<math.h>
#define MAX_N 100
int main()
{
	int x, y;
	int a, b, c, i;
	int count = 0;
	int shuzu[MAX_N][3];
	for (x = 1; x <= 1000; x++)
	{
		for (y = 1; y < x; y++)
		{
			if (sqrt(x) == (int)sqrt(x) && sqrt(y) == (int)sqrt(y)) {
				a = x - y;
				b = 2 * sqrt(x * y);
				c = x + y;
				if (a * a + b * b == c * c)
				{
					shuzu[count][0] = a;
					shuzu[count][1] = b;
					shuzu[count][2] = c;
					count++;
					if (count >= MAX_N)
					{
						break;
					}
				}
			}
	}
		if (count >= MAX_N)
		{
			break;
		}
	}
	printf("结果如下\n");
	for(i=0;i<count;i++)
	{
		printf("(%d,%d,%d)\n", shuzu[i][0], shuzu[i][1], shuzu[i][2]);
	}
	return 0;
}
