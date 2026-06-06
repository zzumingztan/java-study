#include<stdio.h>
int main()
{

	int A[20];int i;
	A[0] = 1; A[2] = 1;
	A[1] = 2; A[3] = 3;
	for (i = 2; i < 10; i++)
	{
		A[2*i] = A[2*i - 2] + A[2*i - 4];
		A[2 * i+1] = A[2 * i - 1] + A[2 * i - 3];
	}
	for (i = 0; i < 20; i++)
	{
		printf("%d  ", A[i]);
		if (i % 10== 0)
			printf("\n");
	}
	return 0;
}
