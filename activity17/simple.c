/*
 * simple.c
 *
 * A very simple program.
 */

#include <stdio.h>

const char * const ADJECTIVE = "happy";

// Prints a greeting message
void greet();

int main()
{
    // Call greet
    greet();

    return 0;
}

void greet()
{
    printf("Hello there! I am %s to see you.\n", ADJECTIVE);
}
