// COMPILATION: gcc -o hw4 hw4.c -lpthread -lm

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

// int within_circle[] = {0,0,0,0};
// int next_point = 0;

// manually creating a race condition:
int point_accumulation = 0;

pthread_mutex_t mutex;

void *approximator(void *param);

int main(int argc, char *argv[])
{

	if (argc != 2) {
                fprintf(stderr,"usage: ./hw4 <integer value>\n");
                return -1;
        }

	if (pthread_mutex_init(&mutex,NULL) != 0)
        	fprintf(stderr,"Error\n");

	int number_of_points_for_each_threads = atoi(argv[1])/4;

	pthread_t tid[4];
	pthread_attr_t attr;

	pthread_attr_init(&attr);

	for (int i = 0; i < 4; i++) {
		pthread_create(&tid[i],&attr,approximator,argv[1]);
	}

	for (int i = 0; i < 4; i++) {
		pthread_join(tid[i],NULL);
        }

	int total_points_within_circle = point_accumulation;

	if (pthread_mutex_destroy(&mutex) != 0)
        	fprintf(stderr,"Error\n");

	// for (int i = 0; i < 4; i++){
	//	total_points_within_circle += within_circle[i];
	// }


	double pi_value = ((double)total_points_within_circle/atoi(argv[1]))*4;
	printf("PI APPROXIMATION = %f \n", pi_value);

	return 0;

}

void *approximator(void *param)
{
	int points_count = atoi(param)/4;

	int points_within_circle = 0;

	for (int i = 0; i < points_count; i++) {
		double x, y;
		x = random()/ ((double)RAND_MAX + 1) * 2.0 - 1.0;
		y = random()/ ((double)RAND_MAX + 1) * 2.0 - 1.0;
		double range = sqrt((x*x)+(y*y));
		if (range <= 1.0) {
			points_within_circle++;
		}
	}


	if (pthread_mutex_lock(&mutex) != 0)
        	fprintf(stderr,"Error\n");

	point_accumulation += points_within_circle;

	if (pthread_mutex_unlock(&mutex) != 0)
         	fprintf(stderr,"Error\n");

	// within_circle[next_point] = points_within_circle;
	// next_point++;

	pthread_exit(0);
}
