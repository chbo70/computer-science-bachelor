#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <stdatomic.h>
#include "myqueue.h"


#if USE_MY_MUTEX
    #define MUTEX_LOCK my_mutex_lock(&a_flag)
    #define MUTEX_UNLOCK my_mutex_unlock(&a_flag)
#else
    #define MUTEX_LOCK pthread_mutex_lock(&mutex)
    #define MUTEX_UNLOCK pthread_mutex_unlock(&mutex)
#endif

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
atomic_flag a_flag = ATOMIC_FLAG_INIT;

void my_mutex_lock(volatile void *atomic_mutex) {
    assert(atomic_flag_test_and_set(atomic_mutex) == true);
}

void my_mutex_unlock(volatile void *atomic_mutex) {
    atomic_flag_clear(atomic_mutex);
}

void *popFromQueue(void *arg) {
    myqueue *queue = arg;
    int x = 0;
    while (1) {
        int i = 1;
        MUTEX_LOCK;

        if (!myqueue_is_empty(queue)) {
            i = myqueue_pop(queue);
            x += i;
        }
        MUTEX_UNLOCK;

        if (i == 0) {
            printf("%d\n", x);
            pthread_exit(NULL);
        }
    }
}

void *pushOnQueue(void *arg) {
    myqueue *queue = arg;
    for (int i = 0; i < 10000000; i++) {
        MUTEX_LOCK;
        myqueue_push(queue, 1);
        MUTEX_UNLOCK;
    }
    MUTEX_LOCK;
    myqueue_push(queue, 0);
    MUTEX_UNLOCK;
    pthread_exit(NULL);
}

int main() {
    pthread_t producer;
    pthread_t consumer;

    //allocate memory for queue
    myqueue *queue = malloc(sizeof(myqueue));
    myqueue_init(queue);

    //pushing thread
    if (pthread_create(&producer, NULL, &pushOnQueue, (void *) queue)){
        perror("pushing thread creation");
        return EXIT_FAILURE;
    }
    //poping thread
    if (pthread_create(&consumer, NULL, &popFromQueue, (void *) queue)){
        perror("poping thread creation");
        return EXIT_FAILURE;
    }

    pthread_join(producer, NULL);
    pthread_join(consumer, NULL);

    pthread_mutex_destroy(&mutex);
    free(queue);

    return EXIT_SUCCESS;
}
