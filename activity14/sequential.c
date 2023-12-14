/*
 * sequential.c
 * An illustration of sequential control flow.
 */

#include <stdio.h>

void f();

int main()
{
    f();
    return 0;
}

void f()
{
    int x = 25;
    int y = 17;
    x = y * 3 - 1;
    y += x - 4;

    printf("x = %d\ny = %d\n", x, y);
}
