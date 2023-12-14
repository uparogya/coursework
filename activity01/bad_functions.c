/*
 * bad_functions.c
 * Demonstration of some simple functions in C - now with some fun errors!
 */

// In a C program, typically preprocessor directives (like #include) come first...
#include <stdio.h>


// ... then function prototypes...

// Prototypes consist of the function header (return type, name, parameter list)
// followed by a semicolon (similar to an abstract method in Java).
void doTheThing();
int add2(int x);


// ... then the main function...
int main()
{
    // Just being silly....
    char *title = "the bestest program in the entire universe";    
    printf("Buckle up as we run %s!\n", title);
    
    doTheThing();

    return 0;
}


// ...then function definitions.
void doTheThing()
{
    int fave, better;
    printf("What is your favorite number? (It better be an integer!) ");
    scanf("%d", &fave);

    // better = fave + 2; // Now the better variable is used uninitialized!
    printf("\nOh yeah? Well, I think %d is better! >:P\n", better);
}

int add2(int x)
{
    return x + 2;
}
