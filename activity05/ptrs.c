/*
 * ptrs.c
 *
 * Experimentation with pointers and dereferencing.
 */

#include <stdio.h>

void f(long *lp, double *dp);

int main()
{
    char *cp = "Am Biting";
    printf("cp as a character = %c\n",*cp);
    
    int i = 42;
    int *p = &i;

    long longValue = 0;
    double doubleValue = 0;

    f(&longValue,&doubleValue);

    printf("Value of initially 0 long value = %ld\n",longValue);
    printf("Value of initially 0 double value = %lf\n",doubleValue);

    printf("Value of i = %d\n",i);
    printf("Address of i = %p\n",&i);
    printf("Value of p = %p\n",p);
    printf("Address of p = %p\n",&p);

    printf("Value of p as int = %d\n",*p);

    *p = 54;
    printf("Value of i = %d\n",i);
    printf("Value of p = %d\n",*p);

    return 0;
}

void f(long *lp, double *dp)
{
    *lp = 1034;
    *dp = 2.718;
}
