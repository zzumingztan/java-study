#include<stdio.h>
int main()
{
	int a[9] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	int b[3][3];
	int k = 0;
	int i, j;
	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			b[i][j] = a[k++];
	}

	}
	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			printf("%d  ", b[i][j]);
		}
		printf("\n");
	}
	return 0;
}
