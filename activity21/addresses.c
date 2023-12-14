#include <stdio.h>
#include <stdlib.h>

int globalVar = 1;

int main()
{
    int localVar = 2;
    char *string = "Hello, World!";
    int *dynamic = (int *) malloc(5 * sizeof(int));

    //int *testPointer = &main;
    //*testPointer = 10;

    printf("Global Var = %p\n",&globalVar);
    printf("Local Var  = %p\n",&localVar);
    printf("String     = %p\n",string);
    printf("Malloc     = %p\n",dynamic);
    printf("Main       = %p\n",&main);
    printf("printf()   = %p\n",&printf);

    int something;
    scanf("%d",&something);
}
