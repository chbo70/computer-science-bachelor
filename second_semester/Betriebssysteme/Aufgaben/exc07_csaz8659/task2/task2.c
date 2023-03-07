#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <stdatomic.h>
#include "myqueue.h"


#ifdef USE_MY_MUTEX
atomic_int mutex;
#define MUTEX_LOCK my_mutex_lock()
#define MUTEX_UNLOCK my_mutex_unlock()

void my_mutex_lock(void) {
    while (atomic_flag_test_and_set(&mutex)) {
        sched_yield();
        // sched_yield() is a function that makes the current thread yield its time slice to another thread.
    }

    // this is necessary because the atomic_flag_test_and_set function returns 1 if the flag was set, and 0 if it was not set.
    // if the flag was set, then the mutex is locked, and we need to wait until it is unlocked.
}

void my_mutex_unlock(void) {
    atomic_flag_clear(&mutex);
    // here we clear the flag, so that other threads can use it.
}

#else
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
#define MUTEX_LOCK pthread_mutex_lock(&mutex)
#define MUTEX_UNLOCK pthread_mutex_unlock(&mutex)
#endif

void *thread1_func(void *arg) {
    myqueue *q = (myqueue *) arg;
    int i;
    for (i = 0; i < 10000000; i++) {
        MUTEX_LOCK;
        myqueue_push(q, 1);
        MUTEX_UNLOCK;
    }
    MUTEX_LOCK;
    myqueue_push(q, 0);
    MUTEX_UNLOCK;
    pthread_exit(NULL);
}

void *thread2_func(void *arg) {
    myqueue *q = (myqueue *) arg;
    int sum = 0;
    while (1) {
        MUTEX_LOCK;
        if (myqueue_is_empty(q)) {
            MUTEX_UNLOCK;
            continue;
        }
        int val = myqueue_pop(q);
        MUTEX_UNLOCK;
        if (val == 0) break;
        sum += val;
    }
    printf("%d\n", sum);
    pthread_exit(NULL);
}

int main() {
    // measure time
    struct timespec start, end;
    clock_gettime(CLOCK_MONOTONIC, &start);

    myqueue q;
    myqueue_init(&q);

    pthread_t thread1, thread2;
    if (pthread_create(&thread1, NULL, thread1_func, &q) != 0) {
        perror("pthread_create");
        return EXIT_FAILURE;
    }
    if (pthread_create(&thread2, NULL, thread2_func, &q) != 0) {
        perror("pthread_create");
        return EXIT_FAILURE;
    }

    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);

    // measure time
    clock_gettime(CLOCK_MONOTONIC, &end);
    double time_used = (end.tv_sec - start.tv_sec) * 1000000 + (end.tv_nsec - start.tv_nsec) / 1000;
    printf("time used: %f\n", time_used);

    // my own mutex implementation using atomics and sched_yield is faster than pthread_mutex_lock and pthread_mutex_unlock.
    // i cannot figure out why.
    return EXIT_SUCCESS;
}