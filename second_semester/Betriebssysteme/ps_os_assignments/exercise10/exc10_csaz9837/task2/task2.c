#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <sys/mman.h>
#include <stdarg.h>

#include "allocator_tests.c"
#include "membench.c"



#define BLOCK_SIZE sizeof(struct block)

//create a struct to store the size and the next pointer
typedef struct block {
    size_t size;
    int free;
    struct block *next;
    struct block *previous;
    struct block *memHole;
} block;

//initial block declaration
block *memory_block;

#define DEBUG

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

    void *start = memory_block;
    block *current = memory_block; // the head of your free block list. current-> usable_size is the size of the block without the header size.
    while (current != NULL) {
        int percentage = (int) (((char *) current - (char *) start) / (double) memory_block->size * 100);
        for (int i = 0; i < percentage; i++) {
            printf(" ");
        }
        int percentage_end = (int) (((char *) current + sizeof(block) + current->size - (char *) start) / (double) memory_block->size *
                                    100);
        for (int i = percentage; i < percentage_end; i++) {
            printf("#");
        }
        // print until end reached
        for (int i = percentage_end; i < 100; i++) {
            printf(" ");
        }
        // print debug info
        printf(" %p to %p, size %zu\n", current, (char *) current + sizeof(block) + current->size,
               current->size);
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

void printMemory(void* ptr) {

    struct block *block = (struct block *)ptr;
    while (block != NULL) {
        printf("--------\n");
        printf("this: %p\n", block);
        printf("size: %ld\n", block->size);
        printf("next: %p\n", block->next);
        printf("--------\n");
        block = block->next;
    }

}

//initializing the block of memory_block with max size (freelist is the main block)
void my_allocator_init(size_t size) {
    memory_block = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    memory_block->size = size;
    memory_block->free = 1;
    memory_block->next = NULL;
    memory_block->previous = NULL;
}

//Making way for a new block allocation by splitting a free block i.e memory_block using First Fit
void split(struct block *fitting_slot, size_t size) {
    block *new = (block *) ((char *) fitting_slot + size + sizeof(struct block));
    new->size = (fitting_slot->size) - size - sizeof(struct block);
    new->free = 1;
    new->next = fitting_slot->next;
    fitting_slot->size = size;
    fitting_slot->free = 0;
    fitting_slot->next = new;
}

//basically splits needed memory_block from freelist and links nodes (kinda like a linked list)
void *my_malloc(size_t size) {
    block *curr, *prev;
    void *result;
    curr = memory_block;
    while (((curr->size < size) || (curr->free == 0)) && (curr->next != NULL)) {
        prev = curr;
        curr = curr->next;
        //printf("One block checked\n");
    }
    if ((curr->size) == size) {
        curr->free = 0;
        result = (void *) (++curr);
        printf("Exact fitting block allocated\n");
        return result;
    } else if ((curr->size) > (size + sizeof(struct block))) {
        split(curr, size);
        result = (void *) (++curr);
        printf("\tnext block: %p\n", result);
        //printf("Fitting block allocated with a split\n");
        return result;
    } else {
        result = NULL;
        printf("Sorry. No sufficient memory_block to allocate\n");
        return result;
    }
}

//helper function for freeing memory_block when deallocting
void merge() {
    block *curr, *prev;
    curr = memory_block;
    while ((curr->next) != NULL) {
        if ((curr->free) && (curr->next->free)) {
            curr->size += (curr->next->size) + sizeof(struct block);
            curr->next = curr->next->next;
        }
        prev = curr;
        curr = curr->next;
    }
}

//my_free returning memory_block to memory_block and deleting the block
void my_free(void *ptr) {
    if (ptr == NULL) {
        printf("No memory_block to free\n");
        return;
    }

    block *ptrBlock = (block *) ptr;
    block *curr = memory_block;
    if ((memory_block <= ptrBlock) && ((char *)ptrBlock <= ((char *)memory_block + BLOCK_SIZE))) {
        block *curr = ptr;
        --curr;
        curr->free = 1;
        //doesnt work on memory hole
        merge();
    } else {
        while (curr->next < ptrBlock) {
            curr = curr->next;
        }
        ptrBlock->next = curr->next;
        ptrBlock->free = 1;
        curr->next = ptrBlock;
        merge();
        printf("new block: %p\n", memory_block->next);
        printf("Please provide a valid pointer allocated by MyMalloc\n");
    }


    //printMemory(memory_block);
}

void my_allocator_destroy() {
    munmap(memory_block, BLOCK_SIZE);
}

int main() {
    test_best_fit_allocator();
    return 0;
}