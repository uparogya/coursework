/*
 * types.c
 * A demonstration of some of C's basic data types
 */
#include <stdio.h>

int main()
{
    // Variables of some basic types
    int i =         3272616;
    float f =       0.25;
    double d =      3.1415926535;
    short s =       20000;              // "short" is short for "short int"
    long l =        28671800000000;     // "long" is short for "long int"
    long long ll =  l;                  // "long long" is short for "long long int"
    // Sorry, no "long long long" in C! =(
    char c =        'W';

    // Believe it or not, C does not have a string type. The closest thing it has
    // is "char *" (pointer-to-char). We will talk much more about pointers later on!
    char *str =     "CMPT 251";

    printf("The population of Utah is about %d.\n", i);
    printf("A quarter is worth $%f.\n", f);
    printf("The ratio of a circle's circumference to its diameter is approximately %lf.\n", d);
    printf("Jules Verne wrote a book called %d Leagues Under the Seas.\n", s);
    printf("The current national debt of the USA is currently about $%ld.\n", l);
    printf("Again, the current national debt of the USA is about $%lld.\n", ll);
    printf("You are a student at %cestminster College.\n", c);
    printf("You are taking %s.\n", str);

    printf("\nOn this system, an int is %d bytes.\n", sizeof(int));

    printf("\n Int= %d bytes. \n", sizeof(int));
    printf("\n float= %d bytes. \n", sizeof(float));
    printf("\n double= %d bytes. \n", sizeof(double));
    printf("\n short= %d bytes. \n", sizeof(short));
    printf("\n long= %d bytes. \n", sizeof(long));
    printf("\n long long= %d bytes. \n", sizeof(long long));
    printf("\n char= %d bytes. \n", sizeof(char));
    printf("\n char*= %d bytes. \n", sizeof(char*));

    printf("-------------------------\n");

    printf("Enter a number: "); 
    int number; 
    scanf("%d",&number);
    printf("\nEnter a float: ");
    float floatVal;
    scanf("%f",&floatVal);
    printf("\nEnter a double: ");
    double doub;
    scanf("%lf",&doub);
    
    printf("\nThe float you entered is: %f. The double you entered is: %lf. The number you entered is: %d. The product of three numbers you entered is: %lf",floatVal,doub,number,number*doub*floatVal); 

    printf("\n");
    return 0;
}
