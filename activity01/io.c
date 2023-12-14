/*
 * io.c
 *
 * A demonstration of simple I/O in C (printf and scanf)
 */
#include <stdio.h>

int main()
{
	int age;
	printf("How old are you? ");
	scanf("%d", &age);

    int birthYear = 2023 - age;
    
	printf("You were born in %d. That means next year you'll be %d! Can you believe it?\n", birthYear, age + 1);

	return 0;
}

