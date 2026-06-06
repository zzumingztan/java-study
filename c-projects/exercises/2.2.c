#include<stdio.h>
int main()
{
	char ch;
	
		for (ch = 'a'; ch <= 'z'; ch++)
		{
			 if ( ch != 'z')
			{
				printf("%c的后继字母为%c\n", ch, ch + 1);
			}
			else
			{
				printf("%c的后继字母为a\n", ch);
				
			}
		}

		for (ch = 'A'; ch <= 'Z'; ch++)
		{
			 if (ch != 'Z') {
				printf("%c的后继字母为%c\n", ch, ch + 1);
			}
			else 
			{
				printf("%c的后继字母为A\n", ch);
				
			}
		}
	
	return 0;


}

