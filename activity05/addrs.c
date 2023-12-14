/*
 * addrs.c
 *
 * Demonstration of the & operator and areas of memory
 */

#include <stdio.h>

// These are global variables: they can be accessed from anywhere in the program.
int gi = 1000;
double gd;
char *gs = "Global string";

void f(int pi, char pc, char *ps);

int main()
{
    // These are local variables
    int i;
    long l;
    char *s = "Local string";

    // Print the address of variable i
    printf("Address of i: %ld\n", &i);
    // Print the address of variable i as a pointer (in hexadecimal)
    printf("Address of gi as a pointer: %p\n", &gi);
    printf("Address of gd as a pointer: %p\n", &gd);
    printf("Address of gs as a pointer: %p\n", gs);
    printf("Address of i as a pointer: %p\n", &i);
    printf("Address of l as a pointer: %p\n", &l);
    printf("Address of s as a pointer: %p\n", s);

    f(i, 'X', s);

    return 0;
}

void f(int pi, char pc, char *ps)
{
    printf("Address of pi as a pointer: %p\n", &pi);
    printf("Address of pc as a pointer: %p\n", &pc);
    printf("Address of ps as a pointer: %p\n", ps);

    // pi, pc, and ps are parameters
    
}
