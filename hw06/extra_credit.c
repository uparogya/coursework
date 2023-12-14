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
    *n = 5;
    int numberOfItems = *n;
    double *numbers = (double *) malloc(numberOfItems * sizeof(double));
    printf("\nEnter numbers separated by spaces. End with a letter to mark the end.: ", numberOfItems);
    int i = 0;
    while(1) {
        if(i % 5 == 0 && i != 0){
            numbers = (double *) realloc( numbers , (sizeof(double) * (5+i)) );
        }

        if(!scanf("%lf",&numbers[i])){
            break;
        }
        i++;
    }
    *n = i;
    return numbers;
}
