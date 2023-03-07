#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <stdatomic.h>

//uncomment the atomic_int and comment the mutexes for task1.2

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
atomic_int x = 100000;
//int x = 100000;
int numberOfIterations = 10000;

void *thread_decrement_func (){
    for (int i = 0; i < numberOfIterations; i++) {
       // pthread_mutex_lock(&mutex);
        x--;
        //pthread_mutex_unlock(&mutex);
    }
    return EXIT_SUCCESS;
}

int main (){
    int numberOfThreads = 1000;
    pthread_t threads[numberOfThreads];
    for (int i = 0; i < numberOfThreads; i++) {
        if (pthread_create(&threads[i], NULL, &thread_decrement_func, NULL)){
            perror("threads creation");
            return EXIT_FAILURE;
        }
    }
    for (int i = 0; i < numberOfThreads; i++) {
        //perror("joining");
        pthread_join(threads[i], NULL);
    }
    printf("The Value of X = %d\n", x);
    return EXIT_SUCCESS;
}