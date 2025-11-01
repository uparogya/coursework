#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <string.h>

int main(int argc, char *argv[])
{
	if (argc != 3) {
                printf("Usage: ./lab2 [value] [shared memory segment name]\n");
                return -1;
        }

	const int SIZE = 4096;
        int value;
        value = atoi(argv[1]);

        pid_t pid;

        pid = fork();

        if (pid < 0) { /* error occurred */
                fprintf(stderr, "Fork Failed\n");
                return 1;
        }
        else if (pid == 0) { /* child process */
 		int shm_fd;
        	void *ptr;

		shm_fd = shm_open(argv[2], O_CREAT | O_RDWR, 0666);
		ftruncate(shm_fd,SIZE);
		ptr = mmap(0,SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd, 0);

		const int STR_SIZE = 128;
		char str[STR_SIZE];

		while (value != 1)
                {
                        if (value%2 == 0)
                        {
                                value = value / 2;
                        } else {
                                value = (3*value) + 1;
                        }
			sprintf(str,"%d,",value);
			sprintf(ptr, "%s", str);
			ptr += strlen(str);
                }
        }
        else { /* parent process */
                /* parent will wait for the child to complete */
                wait(NULL);

		int shm_fd;
        	void *ptr;

		shm_fd = shm_open(argv[2], O_RDONLY, 0666);
		ptr = mmap(0,SIZE, PROT_READ, MAP_SHARED, shm_fd, 0);
		printf("%s",(char *)ptr);
		if (shm_unlink(argv[2]) == -1) {
                	printf("Error removing %s\n",argv[2]);
                	exit(-1);
        	}
        }

        printf("\n");
        return 0;
}
