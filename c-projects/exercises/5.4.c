#include<stdio.h>

int main()
{
	//最多情况应该为从逆（正）序变成正（逆）序
	
	int a[] = { 98,93,92,45,43,41,23,12,11,1};
	
	int temp;
	int count = 0;
	int i,j;
	for (i = 1; i < 10; i ++ )
	{//冒泡排序
		for (j = 0; j < 10 - i; j++)
		{
			if (a[j] > a[j + 1])
			{
				temp = a[j];
				a[j] = a[j + 1];
					a[j + 1] = temp;
					count++;
			}
		}

	}
	for (i = 0; i < 10; i++)
	{
		printf("%d\n",a[i]);
		
	}
	printf("\n%d", count);



	return 0;
}
