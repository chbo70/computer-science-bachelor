#include <stdio.h>

#include <stdlib.h>
#include <pthread.h>
#include "myqueue.h"

pthread_cond_t queue_cond;
pthread_mutex_t queue_mutex;

void *consumer(void *arg) {
    myqueue *q = (myqueue *) arg;
    int sum = 0;
    int value;
    while (1) {
        pthread_mutex_lock(&queue_mutex);

        while (myqueue_is_empty(q)) {
            pthread_cond_wait(&queue_cond, &queue_mutex);
        }
        // using the conditional variable the program is much faster than busy waiting

        value = myqueue_pop(q);

        pthread_mutex_unlock(&queue_mutex); // unlock before summing or returning

        if (value == 0) {
            //printf("Consumer %lu sum: %d\n", pthread_self(), sum);
            return (void *) sum;
        }
        sum += value;
    }

}

int main() {
    myqueue q;
    myqueue_init(&q);
    int i;

    // init condition variable and mutex
    if (pthread_mutex_init(&queue_mutex, NULL) != 0) {
        perror("pthead_mutex_init");
        return EXIT_FAILURE;
    }
    if (pthread_cond_init(&queue_cond, NULL) != 0) {
        perror("pthread_cond_init");
        return EXIT_FAILURE;
    }

    pthread_t *threads[500];
    for (i = 0; i < 500; i++) {
        if (pthread_create(threads + i, NULL, consumer, &q)) {
            perror("pthread_create");
            return EXIT_FAILURE;
        }
    }

    for (i = 0; i < 100000; i++) {
        pthread_mutex_lock(&queue_mutex);
        myqueue_push(&q, 1);
        pthread_cond_signal(&queue_cond);
        pthread_mutex_unlock(&queue_mutex);
    }
    for (i = 0; i < 500; i++) {
        pthread_mutex_lock(&queue_mutex);
        myqueue_push(&q, 0);
        pthread_cond_signal(&queue_cond);
        pthread_mutex_unlock(&queue_mutex);
    }

    int sum = 0;
    void *result;
    for (i = 0; i < 500; i++) {
        pthread_join(*threads[i], &result);
        sum += (int) result;
    }
    printf("Final sum: %d\n", sum);

    pthread_cond_destroy(&queue_cond);
    pthread_mutex_destroy(&queue_mutex);

    return EXIT_SUCCESS;
}

