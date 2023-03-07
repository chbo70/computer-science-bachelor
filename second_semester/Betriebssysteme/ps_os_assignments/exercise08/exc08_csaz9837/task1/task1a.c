#include <stdio.h>
#include <stdlib.h>
#include <stdatomic.h>
#include <pthread.h>


void *thread_func(void *args) {
    atomic_int *x = (atomic_int *) args;
    *x -= 1;
    return NULL;
}

int main() {
    atomic_int counter = 30000;
    pthread_t threads[30000];

    for (int i = 0; i < 30000; ++i) {
        if (pthread_create(&threads[i], NULL, &thread_func, (void *)&counter)) {
            perror("pthread creation");
            return EXIT_FAILURE;
        }
    }
    for (int i = 0; i < 30000; ++i) {
        if (pthread_join(threads[i], NULL)) {
            perror("pthread joining");
            return EXIT_FAILURE;
        }
    }
    printf("Counter: %d\n", counter);
    return EXIT_SUCCESS;
}