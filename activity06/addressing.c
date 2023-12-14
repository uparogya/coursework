/*
 * addressing.c
 *
 * A program to demonstrate a few addressing modes.
 *
 * Each of the four functions - f, g, h, and i - returns a long value derived
 * from the parameter p in some way. Look at the assembly code generated for
 * this program to see what each one does!
 *
 * Recall that when the return value is a long, the assembly code will place
 * it in register rax; so you just need to look at what eventually goes into
 * that register to see what the function does.
 */
 
#include <stdio.h>

long f(long *p);
long g(long *p);
long h(long *p);
long i(long *p);

int main()
{
    long w = 3141;
    long x = 99;
    long y = 2718;

    printf("f(x) = %ld\n", f(&x));
    printf("g(x) = %ld\n", g(&x));
    printf("h(x) = %ld\n", h(&x));
    printf("i(x) = %ld\n", i(&x));

    return 0;
}

// Returns the value of the parameter (the memory address stored in pointer p)
long f(long *p)
{
    return (long)p;
}

// Returns the result of dereferencing the pointer p (the value that it points to)
long g(long *p)
{
    return *p;
}

// What does this do? (Look at the assembly)
long h(long *p)
{
    return *p + 1;
}

// What about this one?
long i(long *p)
{
    return *(p + 1);
}
