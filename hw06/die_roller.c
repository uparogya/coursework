#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int rollDice(int *r1, int *r2, int *r3);

int main()
{
    srand(time(NULL));

    //rolls must be int
    int roll_1; int roll_2; int roll_3;
    printf("\nDice roll! I am rolling four dice and dropping the lowest.");

    //sending the addresses so that the pointers can be modified with the rolled values
    int sum = rollDice(&roll_1, &roll_2, &roll_3);
    printf("\n\n%d!\n%d!\n%d!\n\n",roll_1,roll_2,roll_3);
    printf("%d + %d + %d = %d\n\n",roll_1,roll_2,roll_3,sum);

    return 0;
}

int rollDice(int *r1, int *r2, int *r3)
{
    //rollng four dice
    int first_roll = rand() % 7;
    int second_roll = rand() % 7;
    int third_roll = rand() % 7;
    int fourth_roll = rand() % 7;

    //eliminating the lowest
    int lowest = first_roll;
    if (second_roll < lowest){
        *r1 = lowest;
        lowest = second_roll;
    }else{
        *r1 = second_roll;
    }
    if (third_roll < lowest){
        *r2 = lowest;
        lowest = third_roll;
    }else{
        *r2 = third_roll;
    }
    if (fourth_roll < lowest){
        *r3 = lowest;
        lowest = fourth_roll;
    }else{
        *r3 = fourth_roll;
    }

    return (*r1)+(*r2)+(*r3);
}
