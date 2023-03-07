#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <stdatomic.h>
#include <semaphore.h>
#include <unistd.h>
#include "jobqueue.h"

typedef struct thread_pool {
    size_t size;
    pthread_t *threads;

    /*
     * We need to protect the queue from concurrent access by multiple threads.
     * We use a mutex to do this. The condition variable is used to signal that a job has been added to the queue.
     */
    pthread_mutex_t queue_mutex;
    pthread_cond_t queue_cond;

    /*
     * We use a job queue to store the jobs that need to be executed.
     */
    job_queue *queue;

    /*
    * A flag to indicate that the thread pool is shutting down.
    */
    atomic_bool shutdown;

} thread_pool;

void *pool_thread_func(void *arg) {
    thread_pool *pool = (thread_pool *) arg;

    // Loop until the thread pool is shutting down.
    while (!pool->shutdown) {
        pthread_mutex_lock(&pool->queue_mutex);

        while (myqueue_is_empty(pool->queue) && !pool->shutdown) {
            // If the queue is empty, we need to wait for a job to be added to the queue.
            pthread_cond_wait(&pool->queue_cond, &pool->queue_mutex);
        }
        if (pool->shutdown) {
            pthread_mutex_unlock(&pool->queue_mutex);
            return NULL;
        }

        job *job = myqueue_pop(pool->queue);

        // Unlock the mutex so that other threads can access the queue while the current thread is executing the job.
        pthread_mutex_unlock(&pool->queue_mutex);

        // Execute the job
        job->start_routine(job->arg);

        sem_post(job->id);
        // Free the job.
        free(job);
    }
    return NULL;
}

void pool_create(thread_pool *pool, size_t size) {
    pool->shutdown = 0;
    pool->size = size;
    pool->threads = malloc(sizeof(pthread_t) * size);

    pool->queue = malloc(sizeof(job_queue));
    myqueue_init(pool->queue);

    if (pthread_mutex_init(&pool->queue_mutex, NULL) != 0) {
        perror("mutex");
        exit(1);
    }
    if (pthread_cond_init(&pool->queue_cond, NULL) != 0) {
        perror("cond");
        exit(1);
    }

    // Create the threads. Make sure this is called after the mutex and condition variables are initialized.
    for (size_t i = 0; i < size; i++) {
        if (pthread_create(&pool->threads[i], NULL, pool_thread_func, pool)) {
            perror("pthread_create");
            exit(1);
        }
    }
}


job_id pool_submit(thread_pool *pool, job_function start_routine, job_arg arg) {
    job_id id = malloc(sizeof(sem_t));
    sem_init(id, 0, 0);

    // create a job and add it to the queue
    job *job_new = malloc(sizeof(job));
    job_new->id = id;
    job_new->start_routine = start_routine;
    job_new->arg = arg;


    pthread_mutex_lock(&pool->queue_mutex);
    myqueue_push(pool->queue, job_new);
    pthread_mutex_unlock(&pool->queue_mutex);

    // signal that a job has been added to the queue. queue_size has been incremented.
    pthread_cond_signal(&pool->queue_cond);

    return id;
}

void pool_await(job_id id) {
    // job_id is a pointer to a semaphore.
    // Wait for the job to finish.
    sem_wait(id);

    // Destroy the semaphore if it is no longer needed.
    sem_destroy(id);
    free(id);
}

void pool_destroy(thread_pool *pool) {
    pool->shutdown = 1;

    // signal one more time to make sure all threads are not blocked on the condition variable.
    // the threads will exit when they see the shutdown flag is set.
    pthread_cond_broadcast(&pool->queue_cond);

    // wait for all threads to finish
    for (size_t i = 0; i < pool->size; i++) {
        pthread_join(pool->threads[i], NULL);
    }

    // destroy the queue
    free(pool->queue);

    // destroy the mutex and condition variable of the queue.
    pthread_mutex_destroy(&pool->queue_mutex);
    pthread_cond_destroy(&pool->queue_cond);

    // free the threads
    free(pool->threads);

    // free the pool
    free(pool);
}
