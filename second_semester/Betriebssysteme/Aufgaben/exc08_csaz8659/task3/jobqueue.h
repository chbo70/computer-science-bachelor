#ifndef MYQUEUE_H_
#define MYQUEUE_H_

#include <assert.h>
#include <stdbool.h>
#include <stdlib.h>
#include <sys/queue.h>
#include <semaphore.h>

typedef sem_t *job_id;

typedef void *(*job_function)(void *);

typedef void *job_arg;

typedef struct job {
    job_function start_routine;

    job_arg arg;
    job_id id;

    atomic_bool finished;
    pthread_cond_t *finished_cond;
    pthread_mutex_t *finished_mutex;
} job;

struct myqueue_entry {
    job *value;
    STAILQ_ENTRY(myqueue_entry) entries;
};

STAILQ_HEAD(myqueue_head, myqueue_entry);

typedef struct myqueue_head job_queue;

static void myqueue_init(job_queue *q) {
    STAILQ_INIT(q);
}

static bool myqueue_is_empty(job_queue *q) {
    return STAILQ_EMPTY(q);
}

static void myqueue_push(job_queue *q, job *value) {
    struct myqueue_entry *entry = malloc(sizeof(struct myqueue_entry));
    entry->value = value;
    STAILQ_INSERT_TAIL(q, entry, entries);
}

static job *myqueue_pop(job_queue *q) {
    assert(!myqueue_is_empty(q));
    struct myqueue_entry *entry = STAILQ_FIRST(q);
    job *value = entry->value;
    STAILQ_REMOVE_HEAD(q, entries);
    free(entry);
    return value;
}

#endif
