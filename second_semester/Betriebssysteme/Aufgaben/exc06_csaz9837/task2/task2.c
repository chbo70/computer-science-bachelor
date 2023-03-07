#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include "myqueue.h"

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;


struct arguments_struct {
    myqueue *queue;
    int numberOfConsumer;
    int sum;
};

void *thread_cons_func(void *args) {
    struct arguments_struct *arguments = args;
    while (1) {
        int entryValue = 1;
        pthread_mutex_lock(&mutex);
        if (!myqueue_is_empty(arguments->queue)) {
            entryValue = myqueue_pop(arguments->queue);
            arguments->sum += entryValue;
        }
        pthread_mutex_unlock(&mutex);
        if (entryValue == 0) {
            printf("Consumer %d sum: %d\n", arguments->numberOfConsumer, arguments->sum);
            pthread_exit(EXIT_SUCCESS);
        }
    }
}

int main() {
    const int numberOfThreads = 500;
    const int numberOfEntries = 100000;
    //create thread Array in order to spawn multiple threads
    pthread_t threads[numberOfThreads];
    myqueue *myQueue = malloc(sizeof(myqueue));
    myqueue_init(myQueue);

    //initialize pthread struct for the arguments to pass
    struct arguments_struct argumentsStr[numberOfThreads];
    for (int i = 0; i < numberOfThreads; ++i) {
        argumentsStr[i].queue = myQueue;
        argumentsStr[i].sum = 0;
        argumentsStr[i].numberOfConsumer = i;
        if (pthread_create(&threads[i], NULL, &thread_cons_func, &argumentsStr[i]) != 0) {
            perror("pthread_create");
            return EXIT_FAILURE;
        }
    }
    //feeding the entries of value 1
    for (int i = 0; i < numberOfEntries; ++i) {
        pthread_mutex_lock(&mutex);
        myqueue_push(myQueue, 1);
        pthread_mutex_unlock(&mutex);
    }
    //pushing value 0 numberOfThreads time on the queue
    for (int i = 0; i < numberOfThreads; ++i) {
        pthread_mutex_lock(&mutex);
        myqueue_push(myQueue, 0);
        pthread_mutex_unlock(&mutex);
    }
    //initializing final sum
    int finalSum = 0;
    //assure all threads are closed
    for (int i = 0; i < numberOfThreads; ++i) {
        pthread_join(threads[i], NULL);
        finalSum += argumentsStr[i].sum;
    }
    printf("Final sum: %d\n", finalSum);
    free(myQueue);
    pthread_mutex_destroy(&mutex);
    return EXIT_SUCCESS;
}
