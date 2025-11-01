/**
 * Simple shell interface program.
 *
 * Operating System Concepts - Tenth Edition
 * Copyright John Wiley & Sons - 2018
 */

#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <stdlib.h>

int parse_command(char command[], char *args[]);

#define MAX_LINE 80

int main(void)
{
	char *args[MAX_LINE/2 + 1];
   	int should_run = 1;
	int command_length;

	char command[MAX_LINE];

	char *previous_command[MAX_LINE/2 + 1];
	int previous_command_length = 0;

    	while (should_run)
    	{
		printf("osh>");
        	fflush(stdout);

	    	fgets(command, MAX_LINE, stdin);

	    	command_length = parse_command(command, args);

		if (strcmp(args[0], "exit") == 0)
			return 0;

		if (strcmp(args[0], "!!") == 0)
		{
			if (previous_command[0] == NULL)
			{
				printf("No command history found\n");
				continue;
			} else {
				for (int i = 0; i < previous_command_length; i++)
                        	{
                                	args[i] = previous_command[i];
                        	}
				command_length = previous_command_length;

			}
		}

		args[command_length] = NULL;


		pid_t pid;
		pid = fork();

		if (pid < 0) {
                	printf("Error Occured!\n");
                	return 1;
        	}
		else if (pid == 0) {
			int status = execvp(args[0], args);
			if (status == -1)
			{
				printf("Command not found\n");
				return 1;
			}
			return 0;
        	}
		else {
                	wait(NULL);
			for (int i = 0; i < command_length; i++)
                        {
                                previous_command[i] = strdup(args[i]);
                        }
			previous_command_length = command_length;
        	}
    	}

	return 0;
}

int parse_command(char command[], char *args[])
{
	char *spart = strtok(command, " \n");
	int length = 0;

	while (spart != NULL)
	{
		args[length] = spart;
		length++;
		spart = strtok(NULL, " \n");
	}

	return length;
}
