/*
 * A "Hello, world" program
 */

// Standard I/O header (includes things like the printf function)
#include <stdio.h>

// C does not have classes - functions (similar to methods in Java) are placed
// at global scope
int main()
{
    // printf() is the standard function for printing, but it doesn't add a
    // newline unless told to (so it's similar to Java System.out.print())
    printf("Hello, everybody!\n");

    // By convention, returning 0 from main means that the program succeeded
    // (a nonzero return value represents an error)
    return 0;
}

