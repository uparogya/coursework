/*
 * flow.c
 * Program demonstrating simple control flow
 */

#include <stdio.h>

int f(int x);
int g(int x, int y);

int main()
{

}

int f(int x)
{
    do
    {
        ++x;
    } while (x < 100);
    
    return x;
}

int g(int x, int y)
{
    if (x != y)
        return 0;
    else
        return 1;
}
