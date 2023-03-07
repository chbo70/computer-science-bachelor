#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/mman.h>
#include <pthread.h>

#include "allocator_tests.c"
#include "membench.c"

# define HEADER_SIZE sizeof(struct MemoryBlock)

struct MemoryBlock {
    size_t size;
    struct MemoryBlock *previous;
    struct MemoryBlock* next;
    void *data;
};

struct MemoryBlock *head;
pthread_mutex_t mutex;
size_t memoryPoolSize;
void *memoryPoolAdress;

void printMemory(void* ptr) {
    pthread_mutex_lock(&mutex);
    struct MemoryBlock *block = (struct MemoryBlock *)ptr;
    while (block != NULL) {
        printf("--------\n");
        printf("this: %p\n", block);
        printf("size: %ld\n", block->size);
        printf("previous: %p\n", block->previous);
        printf("next: %p\n", block->next);
        printf("--------\n");
        block = block->next;
    }

    pthread_mutex_unlock(&mutex);
}

void* my_malloc(size_t size) {
    //printMemory(head);

    if (head == NULL) {
        return NULL;
    }

    pthread_mutex_lock(&mutex);
    struct MemoryBlock *mallocBlock = head;

    if (mallocBlock->next != NULL) {
        struct MemoryBlock *block = head->next;
        while (block != NULL) {
            if (block->size - HEADER_SIZE >= size && block->size < mallocBlock->size) {
                mallocBlock = block;
            }
            block = block->next;
        }
    }

    if (mallocBlock->size - HEADER_SIZE < size) {
        pthread_mutex_unlock(&mutex);
        return NULL;
    }

    struct MemoryBlock *previousBlock = mallocBlock->previous;
    struct MemoryBlock *nextBlock = mallocBlock->next;

    if (mallocBlock->size - HEADER_SIZE == size) {
        //printf("mallocBlock->size <= size\n");
        if (previousBlock == NULL && nextBlock == NULL) {
            //printf("previousBlock == NULL && nextBlock == NULL\n");
            head = NULL;
            pthread_mutex_unlock(&mutex);
            return (void *)mallocBlock->data;
        }
        if (previousBlock == NULL) {
            //printf("previousBlock == NULL\n");
            head = nextBlock;
            nextBlock->previous = previousBlock;
            pthread_mutex_unlock(&mutex);
            return (void *)mallocBlock->data;
        }
        if (nextBlock == NULL) {
            //printf("nextBlock == NULL\n");
            previousBlock->next = nextBlock;
            pthread_mutex_unlock(&mutex);
            return (void *)mallocBlock->data;
        }

        previousBlock->next = nextBlock;
        nextBlock->previous = previousBlock;
        pthread_mutex_unlock(&mutex);
        return (void *)mallocBlock->data;
    }

    struct MemoryBlock *new = (struct MemoryBlock *)((u_int8_t *)mallocBlock + HEADER_SIZE + size);
    new->size = mallocBlock->size - (size + HEADER_SIZE);
    new->previous = previousBlock;
    new->next = nextBlock;
    new->data = (u_int8_t *)new + HEADER_SIZE;

    if (previousBlock == NULL && nextBlock == NULL) {
        //printf("previousBlock == NULL && nextBlock == NULL\n");
        head = new;
        mallocBlock->size = size + HEADER_SIZE;
        pthread_mutex_unlock(&mutex);
        return (void *)mallocBlock->data;
    }
    if (previousBlock == NULL) {
        //printf("previousBlock == NULL\n");
        head = new;
        nextBlock->previous = new;
        mallocBlock->size = size + HEADER_SIZE;
        pthread_mutex_unlock(&mutex);
        return (void *)mallocBlock->data;
    }
    if (nextBlock == NULL) {
        //printf("nextBlock == NULL\n");
        previousBlock->next = new;
        mallocBlock->size = size + HEADER_SIZE;
        pthread_mutex_unlock(&mutex);
        return (void *)mallocBlock->data;
    }

    previousBlock->next = new;
    nextBlock->previous = new;
    mallocBlock->size = size + HEADER_SIZE;

    pthread_mutex_unlock(&mutex);
    return (void *)mallocBlock->data;
}

void my_free(void* ptr) {
    //printf("my_free\n");

    if (ptr == NULL) {
        fprintf(stderr, "NULL pointer exeption.\n");
        return;
    }

    pthread_mutex_lock(&mutex);
    struct MemoryBlock *freeBlock = (struct MemoryBlock *)((u_int8_t *)ptr- HEADER_SIZE) ;
    struct MemoryBlock *currentBlock = head;

    if (currentBlock == NULL) {
        freeBlock->previous = NULL;
        freeBlock->next = NULL;
        head = freeBlock;
        pthread_mutex_unlock(&mutex);
        return;
    }

    /*
    while (currentBlock < freeBlock && currentBlock->next != NULL) {
        currentBlock = currentBlock->next;
    }
*/
    //printf("currentBlock %p\n", currentBlock);

    struct MemoryBlock *previousBlock = currentBlock->previous;
    struct MemoryBlock *nextBlock = currentBlock->next;

    if (previousBlock == NULL) {
        //printf("previousBlock == NULL\n");
        freeBlock->previous = previousBlock;
        freeBlock->next = currentBlock;
        currentBlock->previous = freeBlock;
        head = freeBlock;
    }
    if (nextBlock == NULL && currentBlock < freeBlock) {
        //printf("nextBlock == NULL && currentBlock < freeBlock\n");
        if ((u_int8_t *)currentBlock + currentBlock->size == (u_int8_t *)freeBlock) {
            currentBlock->size = currentBlock->size + freeBlock->size;
            pthread_mutex_unlock(&mutex);
            //printMemory(head);
            return;
        }

        freeBlock->previous = currentBlock;
        freeBlock->next = nextBlock;
        currentBlock->next = freeBlock;
    }
    if (previousBlock != NULL) {
        //printf("previousBlock != NULL\n");
        freeBlock->previous = previousBlock;
        freeBlock->next = currentBlock;
        previousBlock->next = freeBlock;
        currentBlock->previous = freeBlock;
    }


    if((u_int8_t *)freeBlock + freeBlock->size == (u_int8_t *)currentBlock) {
        //printf("freeBlock + freeBlock->size == currentBlock\n");
        freeBlock->size = freeBlock->size + currentBlock->size;

        freeBlock->previous = previousBlock;
        freeBlock->next = nextBlock;

        if (previousBlock != NULL) {
            previousBlock->next = freeBlock;
        }
        if (nextBlock != NULL) {
            nextBlock->previous = freeBlock;
        }

        if (previousBlock != NULL && (u_int8_t *)previousBlock + previousBlock->size == (u_int8_t *)freeBlock) {
            //printf("previousBlock != NULL && previousBlock + previousBlock->size == freeBlock [inside]\n");
            size_t temp = previousBlock->size;
            temp += freeBlock->size;
            previousBlock->size = temp;

            previousBlock->next = nextBlock;
            if (nextBlock != NULL) {
                nextBlock->previous = previousBlock;
            }
            pthread_mutex_unlock(&mutex);
            //printMemory(head);
            return;
        }
    }

    if(previousBlock != NULL && (u_int8_t *)previousBlock + previousBlock->size == (u_int8_t *)freeBlock) {
        //printf("previousBlock != NULL && previousBlock + previousBlock->size == freeBlock\n");
        previousBlock->size = previousBlock->size + freeBlock->size;

        previousBlock->next = currentBlock;
        currentBlock->previous = previousBlock;
    }

    pthread_mutex_unlock(&mutex);
    //printMemory(head);
}


void my_allocator_init(size_t size) {
    head = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    if (head == MAP_FAILED) {
        fprintf(stderr, "Mapping the memory_block block failed.\n");
        exit(EXIT_FAILURE);
    }

    pthread_mutex_init(&mutex, NULL);
    memoryPoolSize = size;
    memoryPoolAdress = head;

    head->size = size;
    head->previous = NULL;
    head->next = NULL;
    head->data = (u_int8_t *)head + HEADER_SIZE;
}

void my_allocator_destroy(void) {
    munmap(memoryPoolAdress, memoryPoolSize);
    pthread_mutex_destroy(&mutex);
}

int main(void) {
    printf("size of struct: %ld\n", HEADER_SIZE);
    printf("%d\n", 0 < 0);
    test_best_fit_allocator();
    run_membench_global(my_allocator_init, my_allocator_destroy, my_malloc, my_free);

/*     my_allocator_init(1024 * 3);

    void *malloc1 = my_malloc(1024);
    printf("malloc1 = %p\n", malloc1);
    void *malloc2 = my_malloc(1024);
    printf("malloc2 = %p\n", malloc2);
    my_free(malloc1);
    void *malloc3 = my_malloc(1024);
    printf("malloc3 = %p\n", malloc3);
    printMemory(head); */


    return EXIT_SUCCESS;
}