#include <stdio.h>

void reduceFraction(int *divident, int *divisor);
int gcd(int m, int n);

int main()
{
    int divident;
    int divisor;

    //taking the input
    printf("Enter a fraction: ");
    scanf("%d/%d",&divident,&divisor);
    if(divident == 0 || divisor == 0){
        printf("0 cannot be divident or divisor.\n");
        return 1;
    }
    //sending the address to the int pointer
    reduceFraction(&divident,&divisor);
    printf("In lowest terms, that fraction is %d/%d.\n",divident,divisor);

    return 0;
}

void reduceFraction(int *divident, int *divisor)
{
    //common factor
    int factor = gcd(*divident,*divisor);

    //changing the values the pointers point to
    *divident = *divident/factor;
    *divisor = *divisor/factor;
}

int gcd(int m, int n)
{
    if (m < 0)
        return gcd(-m, n);
    else if (n < 0)
        return gcd(m, -n);
    else if (m < n)
        return gcd(n, m);
    else if (m % n != 0)
        return gcd(n, m % n);
    else
        return n;
}
