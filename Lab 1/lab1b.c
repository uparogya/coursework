#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{

        int value;
        value = atoi(argv[1]);

        pid_t pid;

        pid = fork();

        if (pid < 0) { /* error occurred */
                fprintf(stderr, "Fork Failed\n");
                return 1;
        }
        else if (pid == 0) { /* child process */

		execlp("./collatz", "collatz", argv[1], NULL);

        }
        else { /* parent process */
                /* parent will wait for the child to complete */
                wait(NULL);
        }

        printf("\n");
        return 0;
}
