/*
 * letters.c
 *
 * Prints capital letters forever.
 */

#include <stdio.h>
#include <unistd.h>     // for sleep()

int main()
{
    char c = 'A';
    while (1)
    {
        printf("%c\n", c);

        if (c == 'Z')
            c = 'A';
        else
            ++c;

        sleep(1);
    }

    return 0;
}
