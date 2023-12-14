#include <stdio.h>
#include <stdlib.h> // needed for malloc() / free()


int main()
{
    //int numbers[4];
    int *numbers = (int *) malloc(5 * sizeof(int));

    numbers[0] = 5;
    numbers[1] = numbers[0] * 2 - 1;
    numbers[2] = numbers[1] * numbers[0] + 4;
    numbers[3] = numbers[2] - (numbers[1] * numbers[0]) - 8;

    for (int i = 0; i < 4; ++i)
    {
        printf("numbers[%d] = %d\n", i, numbers[i]);
    }

    free(numbers);
    return 0;
}
