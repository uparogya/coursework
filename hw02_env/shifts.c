#include <stdio.h>

int shift_left_signed(int i, int bits);
int shift_right_signed(int i, int bits);
unsigned int shift_left_unsigned(unsigned int i, int bits);
unsigned int shift_right_unsigned(unsigned int i, int bits);
void print_int_bits(int i);

int main()
{
    int x;
    x = shift_left_signed(15, 2);
    printf("Shift Left Signed: %d \n", x); //60
    x = shift_right_signed(15, 2);
    printf("Shift Right Signed: %d \n", x); //3
    x = shift_left_unsigned(15, 2);
    printf("Shift Left Unsigned: %d \n", x); //60
    x = shift_right_unsigned(15, 2);
    printf("Shift Right Unsigned: %d \n", x); //3
    return 0;
}

int shift_left_signed(int i, int bits)
{
    return i << bits;
}

int shift_right_signed(int i, int bits)
{
    return i >> bits;
}

unsigned int shift_left_unsigned(unsigned int i, int bits)
{
    return i << bits;
}

unsigned int shift_right_unsigned(unsigned int i, int bits)
{
    return i >> bits;
}

void print_int_bits(int i)
{
    for (int bit = 31; bit >= 0; --bit)
    {
        printf("%c", (i & 0x80000000) ? '1' : '0');
        i = i << 1;
    }
}
