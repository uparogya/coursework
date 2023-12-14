#include <stdio.h>
#include <unistd.h>

int main()
{
    // Print out the results of getpid() and getppid() here
    // (getpid() and getpid() both return a "pid_t," which you can treat as an int)

    printf("getpid() = %d \n",getpid());
    printf("getppid() = %d \n",getppid());

    // Wait until the user hits Enter to exit
    char *line;
    size_t n;
    printf("\nHit Enter to exit!\n");
    getline(&line, &n, stdin);

    return 0;
}
