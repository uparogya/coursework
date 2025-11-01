#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
        int value;
        value = atoi(argv[1]);


        while (value != 1)
        {
                if (value%2 == 0)
                {
                        value = value / 2;
                } else {
                        value = (3*value) + 1;
                }
                printf("%d,",value);
        }

        return 0;
}
