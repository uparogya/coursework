#include <stdio.h>

void f();
void g();
void h();

int main()
{

    printf("--- f() ---\n");
    f();

    printf("--- g() ---\n");
    g();

    printf("--- h() ---\n");
    h();
    
    return 0;
}

void f()
{
    // Declare an array of ints
    // Differences from Java:
    //  - [] come after the variable *name*, not type
    //  - no "new" required
    int numbers[4] = { 1, 2, 3, 4 };

    for(int i = 0; i < sizeof(numbers)/sizeof(numbers[i]); i++){
        printf("%d\n", numbers[i]);
    }
    printf("Size of numbers = %d\n", sizeof(numbers));
}

void g()
{
    int moreNumbers[4];
    moreNumbers[0] = 1;
    moreNumbers[1] = 2;
    moreNumbers[2] = 3;
    moreNumbers[3] = 4;
    printf("%d\n",moreNumbers[100]);

    for(int i = 0; i < 4; i++){
        printf("%d \n",moreNumbers[i]);
    }

}

void h()
{
    long age;
    char family_name[16];
    char given_name[16];

    // Write code to input age, given name, and family name, then print
    // them out
    printf("Enter Your Age: ");
    scanf("%ld",&age);
    printf("\n");
    printf("Enter Your Family Name: ");
    scanf("%s",&family_name);
    printf("\n");
    printf("Enter Your Given Name: ");
    scanf("%s",&given_name);
    printf("\n");

    printf("You are %ld years old. Your name is %s %s.\n",age,given_name,family_name);
}

