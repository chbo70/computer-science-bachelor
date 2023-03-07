#include<stdio.h>
#include<stddef.h>
#include <sys/mman.h>
#include <stdlib.h>
#include <pthread.h>
#include <stdarg.h>

#include "allocator_tests.c"
#include "membench.c"

#define BLOCK_SIZE 1024

typedef struct block {
    size_t size;            // size of the block in bytes
    struct block *next;     // pointer to the next block
} block;

pthread_mutex_t mutex;
block *memory;

//variables to save the address of Pool and Size
size_t sizeOfMemoryPool = 0;
void* saveMemPool;

void my_allocator_init(size_t size){
    memory = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    if (memory == MAP_FAILED) {
        perror("mmap");
        exit(1);
    }
    pthread_mutex_init(&mutex, NULL);
    saveMemPool = memory;
    //loop through the memory and create a free list of blocks
    for (size_t i = 0; i < size; i += BLOCK_SIZE) {
        block *curr = (block *) ((char *) memory + i);
        curr->size = BLOCK_SIZE;
        curr->next = (block *) ((char *) curr + BLOCK_SIZE);
    }
    sizeOfMemoryPool = size;
}


void* my_malloc(size_t size){
    //size is wrong
    if (size == 0) {
        return NULL;
    }
    //size is too big
    //make sure that memory can't access next if it is NULL
    if (size > BLOCK_SIZE || memory == NULL) {
        return NULL;
    }
    pthread_mutex_lock(&mutex);
    block *mallocedBlock = memory;
    //printf("next: %p\n", mallocedBlock->next);
    if (memory->next == NULL){
        memory = NULL;
        pthread_mutex_unlock(&mutex);
        return memory;
    } else {
        memory = memory->next;
    }
    pthread_mutex_unlock(&mutex);
    return mallocedBlock;
}

void my_free(void* ptr){
    //check if the pointer is null
    if (ptr == NULL) {
        return;
    }
    //free the block
    pthread_mutex_lock(&mutex);
    block *freeBlock = (block *) ptr;
    freeBlock->next = memory;
    memory = freeBlock;
    pthread_mutex_unlock(&mutex);
}

void my_allocator_destroy(){
    pthread_mutex_destroy(&mutex);
    munmap(saveMemPool, sizeOfMemoryPool);
}

int main() {
    test_free_list_allocator();
    run_membench_global(my_allocator_init, my_allocator_destroy, my_malloc, my_free);
    return EXIT_SUCCESS;
}