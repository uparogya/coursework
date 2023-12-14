#include <stdio.h>
#include <stdlib.h>
#include <time.h>

float convert(float value);
void rollDice(int m, int n);

int main()
{
    printf("\n---------------FIRST QUESTION---------------\n");
    printf("Mass Converter! \n");
    float value;
    printf("Enter your weight in lbs: ");
    scanf("%f",&value);
    printf("\nYour weight in kgs is: %f \n", convert(value));
    printf("\n---------------SECOND QUESTION---------------\n");
    printf("Dice Roller Game! \n");
    int m; int n;
    printf("How many dice? ");
    scanf("%d",&m);
    printf("\nHow many sides? ");
    scanf("%d",&n);
    rollDice(m,n);
    return 0;
}


float convert(float value)
{
    return value/2.205;
}

void rollDice(int m, int n)
{
    srand (time(NULL));
    printf("--DICE ARE ROLLING--\n");
    int sumOfFaces = 0;
    for(int i = 0; i < m; i++) {
        int randomFaceValue = rand() % n + 1;
        printf("Die Value = %d\n",randomFaceValue);
        sumOfFaces += randomFaceValue;
    }
    printf("Total Sum Of Your Rolled Values = %d \n", sumOfFaces);
}
