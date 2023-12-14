/*
 * favenum.c
 *
 * A program to ask and then repeat back your favorite number (an integer)
 */
#include <stdio.h>

int main()
{
	int n;
	printf("Let's talk about numbers!\n");
	printf("What is your favorite number? ");
	scanf("%d", &n);
	printf("Your favorite number is " + n);
	printf("\n");

	return 0;
}

