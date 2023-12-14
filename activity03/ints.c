#include <stdio.h>

void print_int_bits(int n);

int main()
{

    return 0;
}


void print_int_bits(int n)
{
    for (int bit = 31; bit >= 0; --bit)
    {
        printf("%c", (n & 0x80000000) ? '1' : '0');
        n = n << 1;
    }
}
