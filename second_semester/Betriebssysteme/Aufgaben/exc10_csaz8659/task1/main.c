#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <pthread.h>
#include "allocator_tests.c"

#define BLOCK_SIZE 1024
#define USABLE_SIZE (BLOCK_SIZE - sizeof(block_header))

void *memory;
size_t memory_size;

typedef struct block_header {
    struct block_header *next;
} block_header;

/**
 * Linked list of free blocks.
 */
block_header *head;

pthread_mutex_t memory_mutex;

void *my_malloc(size_t size) {
    if (size > USABLE_SIZE) {
        // size exceeds the size of a single block
        return NULL;
    }
    pthread_mutex_lock(&memory_mutex);
    block_header *current = head;
    if (current == NULL) {
        // no free blocks, out of memory
        pthread_mutex_unlock(&memory_mutex);
        return NULL;
    }

    // update the head so that it points to the next free block
    head = current->next;
    pthread_mutex_unlock(&memory_mutex);

    return (char *) current + sizeof(block_header);
}

void my_free(void *ptr) {
    pthread_mutex_lock(&memory_mutex);
    // insert the block at the head of the list.
    block_header *current = (block_header *) ((char *) ptr - sizeof(block_header));
    current->next = head;
    head = current;
    pthread_mutex_unlock(&memory_mutex);
}


void my_allocator_init(size_t size) {
    memory_size = size;
    memory = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    if (memory == MAP_FAILED) {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    // we could memset the memory to 0, but we don't need to.

    head = (block_header *) memory;

    // initialize the linked list
    block_header *current = head;
    while ((char *) current + BLOCK_SIZE < (char *) memory + size) {
        current->next = (block_header *) ((char *) current + BLOCK_SIZE);
        current = current->next;
    }

    // set the last block to NULL
    current->next = NULL;

    // initialize the mutex
    if (pthread_mutex_init(&memory_mutex, NULL) != 0) {
        perror("pthread_mutex_init");
        exit(EXIT_FAILURE);
    }
}

void my_allocator_destroy(void) {
    if (munmap(memory, memory_size) == -1) {
        perror("munmap");
        exit(EXIT_FAILURE);
    }

    if (pthread_mutex_destroy(&memory_mutex) != 0) {
        perror("pthread_mutex_destroy");
        exit(EXIT_FAILURE);
    }
}

int main() {
    test_free_list_allocator();
}