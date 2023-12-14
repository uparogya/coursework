/*
 * threads.c
 *
 * A demonstration of multithreading using pthreads (POSIX threads).
 *
 * *Note*: to compile this you must link with the pthread library
 * (add -lpthread to the gcc command).
 */

#include <pthread.h>
#include <stdio.h>

// How many threads to make deposits in
const int NUM_THREADS = 4;

// How many deposits each thread should make and how
// much each deposit should be
const int NUM_DEPOSITS = 100000000;
const int DEPOSIT_AMOUNT = 250;

// The balance of our account
unsigned long balance = 0;

// A mutex ("MUTual EXclusion")
// (will get to this later in the activity)
pthread_mutex_t mutex;

// Make a deposit into the account (i.e., increases balance by amount)
void deposit(int amount);

// Function each thread executes
void *run(void *arg);

int main()
{
    pthread_t threads[NUM_THREADS];

    // Mutexes must be init()ed before use
    pthread_mutex_init(&mutex, NULL);

    // Launch NUM_THREADS threads
    for (int i = 0; i < NUM_THREADS; ++i)
    {
        pthread_create(
            &threads[i],    // pthread_t *thread (output parameter)
            NULL,           // attributes (NULL means defaults)
            &run,           // function for thread to run
            NULL            // argument to thread's function
        );
    }

    printf("Threads launched! Waiting for them to finish...\n");


    // Join each thread (that is, wait until all are done executing)
    for (int i = 0; i < NUM_THREADS; ++i)
        pthread_join(threads[i], NULL);

    printf("\n\nEnding balance = %lu\n", balance);
    printf("\n(We expected a final balance of %lu)\n",
        ((unsigned long)NUM_THREADS) * NUM_DEPOSITS * DEPOSIT_AMOUNT);

    // Mutexes should be destroy()ed when you're done (this is similar to
    // how you must free() memory that you malloc()ed).
    pthread_mutex_destroy(&mutex);

    return 0;
}

void deposit(int amount)
{
    if (amount > 0)
    {
        //pthread_mutex_lock(&mutex);
        balance += amount;
        //pthread_mutex_unlock(&mutex);
    }
}

// The function that each thread will run
void *run(void *arg)
{
    printf("Hello, I'm a thread!\n");
    for (int i = 0; i < NUM_DEPOSITS; ++i)
        deposit(DEPOSIT_AMOUNT);
    printf("All done making deposits =)\n");
    
    return NULL;
}
