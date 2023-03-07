#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <stdatomic.h>

#ifdef USE_MUTEX
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
int X = 100000;
#else
atomic_int X = 100000;
#endif


void *thread_function(void *arg) {
    int i;
    for (i = 0; i < 10000; i++) {
#ifdef USE_MUTEX
        pthread_mutex_lock(&mutex);
#endif
        X--;
#ifdef USE_MUTEX
        pthread_mutex_unlock(&mutex);
#endif
    }
    return NULL;
}

int main() {
    struct timespec start, end;
    clock_gettime(CLOCK_MONOTONIC, &start);

    pthread_t thread_id[1000];
    for (int i = 0; i < 1000; i++) {
        if (pthread_create(thread_id + i, NULL, thread_function, NULL) != 0) {
            perror("pthread_create");
            return EXIT_FAILURE;
        }
    }

    for (int i = 0; i < 1000; i++) {
        if (pthread_join(thread_id[i], NULL) != 0) {
            perror("pthread_join");
            return EXIT_FAILURE;
        }
    }

    printf("%d\n", X);

    clock_gettime(CLOCK_MONOTONIC, &end);
    double time_spent = (end.tv_sec - start.tv_sec) + (end.tv_nsec - start.tv_nsec) / 1000000000.0;
    printf("time spent: %f\n", time_spent);

    return EXIT_SUCCESS;
}
