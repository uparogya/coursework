#include <stdio.h>
#include <stdlib.h>

double *inputDoubleArray(int *n);

int main()
{
    int totalNumbers;
    double *array = inputDoubleArray(&totalNumbers);

    double sum = 0;
    for(int i = 0; i < totalNumbers; i++){
        sum += array[i];
    }
    printf("The sum is %lf\n",sum);
    printf("The average is %lf\n",sum/totalNumbers);
    free(array);
    return 0;
}

double *inputDoubleArray(int *n)
{
    printf("How many numbers do you have? ");
    scanf("%d",n);
    int numberOfItems = *n;
    double *numbers = (double *) malloc(numberOfItems * sizeof(double));
    printf("\nEnter %d numbers separated by spaces: ", numberOfItems);
    for(int i = 0; i < numberOfItems; i++){
        scanf("%lf",&numbers[i]);
    }
    return numbers;
}
