#include <stdio.h>
#include <unistd.h>

int main()
{
    printf("[before] pid()  = %d \n", getpid());
    printf("[before] ppid() = %d \n", getppid());
    // Call fork() here and print out the result
    printf("fork() result = %d", fork());
    printf("[after] pid()  = %d \n", getpid());
    printf("[after] ppid() = %d \n", getppid());


    // Wait until the user hits Enter to exit

    char *line;
    size_t n;
    printf("\nHit Enter to exit!\n");
    getline(&line, &n, stdin);
    // */

    return 0;
}
