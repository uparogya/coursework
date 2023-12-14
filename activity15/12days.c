/*
 * 12days.c
 *
 * Taking advantage of switch statement fallthrough to
 * print the "Twelve Days of Christmas" carol
 */

#include <stdio.h>

// Returns the ordinal numeral for n (e.g. "first", "second",
// "third")
const char *ordinal_numeral(int n);

// Prints the nth verse of the Twelve Days of Christmas song
void print_verse(int n);

int main()
{
    for (int i = 1; i <= 12; ++i)
        print_verse(i);

    return 0;
}

const char *ordinal_numeral(int n)
{
    // Use a switch statement to return the correct string
    // for 1 <= n <= 12
    switch (n) {
        case 1:
            return "first";
            break;
        case 2:
            return "second";
            break;
        case 3:
            return "third";
            break;
        case 4:
            return "fourth";
            break;
        case 5:
            return "fifth";
            break;
        case 6:
            return "sixth";
            break;
        case 7:
            return "seventh";
            break;
        case 8:
            return "eighth";
            break;
        case 9:
            return "ninth";
            break;
        case 10:
            return "tenth";
            break;
        case 11:
            return "eleventh";
            break;
        case 12:
            return "twelveth";
            break;
        default:
            return "Invalid Number!";
            break;
    }
}

void print_verse(int n)
{
    // Use ordinal_numeral() to get the ordinal numeral
    // from the first line ("first", "second", etc.)
    const char *ordinal = ordinal_numeral(n);
    printf("\nOn the %s day of Christmas my true love sent to me\n",ordinal);

    // Then use a switch statement with fallthrough to
    // print the lines of the verse
    switch (n) {
        case 12:
            printf("Guitar\n");
        case 11:
            printf("Drumset\n");
        case 10:
            printf("Drumsticks\n");
        case 9:
            printf("Cymbal\n");
        case 8:
            printf("Saxophone\n");
        case 7:
            printf("Trumpet\n");
        case 6:
            printf("Piano\n");
        case 5:
            printf("Bass Guitar\n");
        case 4:
            printf("Human Being\n");
        case 3:
            printf("Three French hens\n");
        case 2:
            printf("Two turtle doves\n");
        case 1:
            printf("A partridge in a pear tree\n");
    }
}
