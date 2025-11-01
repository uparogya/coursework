/**
 * A simple C program that prompts the user
 * for a line of text.
 *
 * @author [AROGYA]
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

const int LEN  = 256;

int vowels[] = {0,0,0,0,0};

void getPhrase(char phrase[])
{
	printf("Enter a phrase : ");

	fgets(phrase, LEN, stdin);
}

void checkCharacter(char character)
{
        if (character == 'a' || character == 'A')
	{
		vowels[0]++;
	}else if (character == 'e' || character == 'E')
	{
		vowels[1]++;
	}else if (character == 'i' || character == 'I')
	{
		vowels[2]++;
	}else if (character == 'o' || character == 'O')
	{
		vowels[3]++;
	}else if (character == 'u' || character == 'U')
	{
		vowels[4]++;
	}
}

void loopThroughPhrase(char phrase[])
{
	int i = 0;

	for(i = 0; i < strlen(phrase); i++)
	{
		checkCharacter(phrase[i]);
	}
}

void printStars(int numberOfStars)
{
	for(int i = 0; i < numberOfStars; ++i)
	{
		printf("* ");
	}
	printf("\n");
}

int main(int argc, char *argv[])
{
	char phrase[LEN];
	getPhrase(phrase);
	loopThroughPhrase(phrase);

	printf("a: ");	printStars(vowels[0]);
	printf("e: ");	printStars(vowels[1]);
	printf("i: ");	printStars(vowels[2]);
	printf("o: ");	printStars(vowels[3]);
	printf("u: ");	printStars(vowels[4]);
}
