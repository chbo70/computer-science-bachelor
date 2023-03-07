#include <stdio.h>

#include <stdlib.h>
#include <pthread.h>
#include "myqueue.h"

pthread_mutex_t queue_mutex;

void *consumer(void *arg) {
    myqueue *q = (myqueue *) arg;
    int sum = 0;
    int value;
    while (1) {
        pthread_mutex_lock(&queue_mutex);
        if (!myqueue_is_empty(q)) {
            value = myqueue_pop(q);
            pthread_mutex_unlock(&queue_mutex); // unlock before summing or returning
            if (value == 0) {
                printf("Consumer %lu sum: %d\n", pthread_self(), sum);
                return (void *) sum;
            }
            sum += value;
        } else {
            // queue is empty, so wait for producer to produce
            pthread_mutex_unlock(&queue_mutex);
        }
    }

}

int main() {
    myqueue q;
    myqueue_init(&q);
    int i;

    // init mutex
    if(pthread_mutex_init(&queue_mutex, NULL) != 0) {
        perror("pthread_mutex_init");
        return EXIT_FAILURE;
    }

    pthread_t *threads[5];
    for (i = 0; i < 5; i++) {
        if (pthread_create(threads + i, NULL, consumer, &q)) {
            perror("pthread_create");
            return EXIT_FAILURE;
        }
    }

    // producer: add 10000 ones to the queue, then add zeroes to signal the consumers
    for (i = 0; i < 10000; i++) {
        pthread_mutex_lock(&queue_mutex);
        myqueue_push(&q, 1);
        pthread_mutex_unlock(&queue_mutex);
    }
    for (i = 0; i < 5; i++) {
        pthread_mutex_lock(&queue_mutex);
        myqueue_push(&q, 0);
        pthread_mutex_unlock(&queue_mutex);
    }

    int sum = 0;
    void *result;
    for (i = 0; i < 5; i++) {
        pthread_join(*threads[i], &result);
        sum += (int) result;
    }
    printf("Final sum: %d\n", sum);

    pthread_mutex_destroy(&queue_mutex);

    // Instead of a mutex, could a semaphore be used in this situation?
    // The producer could use a semaphore to signal the consumer that there is work to be done.
    return EXIT_SUCCESS;
}

