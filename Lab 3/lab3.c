#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

struct element {
    int data;
    struct element *next;
};
struct element *head;
struct element *temp;

void *collatz(void *param);

int main(int argc, char *argv[])
{

	if (argc != 2) {
        	fprintf(stderr,"usage: ./lab3 <integer value>\n");
        	/*exit(1);*/
        	return -1;
	}

	pthread_t tid;
	pthread_attr_t attr;

	pthread_attr_init(&attr);
	pthread_create(&tid,&attr,collatz,argv[1]);
	pthread_join(tid,NULL);

	while (head->next != NULL)
    	{
        	printf("%d\n",head->data);

	        temp = head;
        	head = head->next;
        	free(temp);
    	}

}

void *collatz(void *param)
{

	int value = atoi(param);


	struct element *node;
	node = (struct element *) malloc(sizeof(struct element));
	head = node;

        while (value != 1)
        {
                if (value%2 == 0)
                {
                        value = value / 2;
                } else {
                        value = (3*value) + 1;
                }

		node->data = value;
		node->next = (struct element *) malloc(sizeof(struct element));
		node = node->next;

        }

	node->next = NULL;

	pthread_exit(0);

}
