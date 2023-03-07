#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <pthread.h>
#include <stdarg.h>
#include "allocator_tests.c"
#include "membench.c"

void *memory;
size_t memory_size;

typedef struct block_header {
    struct block_header *next;
    /**
     * Usable size of the block, without the header.
     */
    size_t usable_size;
} block_header;

/**
 * Linked list of free blocks.
 */
block_header *head;

pthread_mutex_t memory_mutex;

#ifdef DEBUG
#define PR_DEBUG(fmt, ...) print_debug(fmt, ##__VA_ARGS__)

void print_debug(char *message, ...) {
    for (int i = 0; i < 100; i++) {
        printf("-");
    }
    printf(" ");

    va_list args;
    va_start(args, message);
    vprintf(message, args);
    va_end(args);

    // print a visual representation of the memory

    // for each free block calculate percentage of memory position
    // and print it where it starts and ends

    void *start = memory;
    block_header *current = head;
    while (current != NULL) {
        int percentage = (int) (((char *) current - (char *) start) / (double) memory_size * 100);
        for (int i = 0; i < percentage; i++) {
            printf(" ");
        }
        int percentage_end = (int) (((char *) current + sizeof(block_header) + current->usable_size - (char *) start) / (double) memory_size *
                                    100);
        for (int i = percentage; i < percentage_end; i++) {
            printf("#");
        }
        // print until end reached
        for (int i = percentage_end; i < 100; i++) {
            printf(" ");
        }
        // print debug info
        printf(" %p to %p, size %zu\n", current, (char *) current + sizeof(block_header) + current->usable_size,
               current->usable_size);
        current = current->next;
    }

    for (int i = 0; i < 100; i++) {
        printf("-");
    }
    printf("\n");
}

#else
#define PR_DEBUG(fmt, ...) do { } while (0)
#endif

void *my_malloc(size_t size) {
    if (size > memory_size) {
        return NULL;
    }
    pthread_mutex_lock(&memory_mutex);

    // find the best fit
    block_header *current = head;
    block_header *last = NULL;

    size_t best_size = SIZE_MAX;
    block_header *best = NULL;
    block_header *best_prev = NULL;

    while (current != NULL) {
        if (current->usable_size >= size && current->usable_size < best_size) {
            best_size = current->usable_size;
            best_prev = last;
            best = current;

            if (current->usable_size == size) {
                // perfect fit
                break;
            }
        }
        last = current;
        current = current->next;
    }

    if (best == NULL) {
        // no free blocks, out of memory
        pthread_mutex_unlock(&memory_mutex);
        return NULL;
    }

    // split the block into two if the block size is bigger than the requested size:
    // one with the requested size, one with the rest that is put back in the free list

    if (best->usable_size > size) {

        // create a new block with the rest of the usable size
        size_t left_size = best->usable_size - size;

        // our block needs to fit the header
        if (left_size > sizeof(block_header)) {
            // skip the header and the requested size
            block_header *new_block = (block_header *) ((char *) best + sizeof(block_header) + size);
            new_block->next = best->next;
            // the first part of the new block will again be the header, therefore subtract the header size
            new_block->usable_size = left_size - sizeof(block_header);

            best->next = new_block;
            // reduce the usable size of the best block
            best->usable_size = size;
        }
    }

    // remove the block from the free list
    if (best == head) {
        head = best->next;
    } else {
        best_prev->next = best->next;
    }

    PR_DEBUG("Allocated %zu bytes at %p\n", size, best);

    pthread_mutex_unlock(&memory_mutex);

    return (char *) best + sizeof(block_header);
}

void merge_adjacent_blocks() {
    // if the previous block ends exactly at the start of the current block, merge them

    block_header *current = head;
    block_header *last = NULL;

    while (current != NULL) {
        if (last != NULL) {
            char *end_address = (char *) last + sizeof(block_header) + last->usable_size;
            if (end_address == (char *) current) {
                // merge the blocks
                last->next = current->next;
                last->usable_size += current->usable_size + sizeof(block_header);

                current = last;
            }
        }
        last = current;
        current = current->next;
    }
}


void my_free(void *ptr) {
    pthread_mutex_lock(&memory_mutex);
    block_header *block = (block_header *) ((char *) ptr - sizeof(block_header));


    // insert the block at its correct position depending on the address to ensure we can merge blocks later
    block_header *current = head;
    block_header *last = NULL;

    while (current != NULL) {
        char *end_address = (char *) current + current->usable_size;
        if (end_address > (char *) block) {
            break;
        }
        last = current;
        current = current->next;
    }

    if (current == NULL) {
        // we are at the end of the list
        block->next = NULL;
    } else {
        block->next = current;
    }

    if (last == NULL) {
        // we are at the beginning of the list
        head = block;
    } else {
        last->next = block;
    }


    merge_adjacent_blocks();
    PR_DEBUG("After freeing %p with size %zu and merging adjacent blocks\n", block, block->usable_size);

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
    head->next = NULL;
    head->usable_size = size - sizeof(block_header);

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
    test_best_fit_allocator();
#ifndef DEBUG
    // only run the following tests if we are not in debug mode
    run_membench_global(my_allocator_init, my_allocator_destroy, my_malloc, my_free);
#endif
    return 0;
}