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

    void *start = freeList;
    block *current = freeList->header; // the head of your free block list. current-> usable_size is the size of the block without the header size.
    while (current != NULL) {
        int percentage = (int) (((char *) current - (char *) start) / (double) freeList->size * 100);
        for (int i = 0; i < percentage; i++) {
            printf(" ");
        }
        int percentage_end = (int) (((char *) current + sizeof(block) + current->size - (char *) start) / (double) freeList->size *
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

//create free list
struct block *freeList;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void my_allocator_init(size_t size) {
    //create a memory pool
    void *mem = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    if (mem == MAP_FAILED) {
        perror("mmap");
        exit(1);
    }
    freeList = (block *) mem;
    freeList->next = NULL;
    freeList->size = size;
}

void* my_malloc(size_t size){
    struct block *curr, *prev;
    curr = freeList;
    prev = NULL;

    //size is wrong
    if (size == 0) {
        return NULL;
    }
    //size is too big
    if (size > BLOCK_SIZE) {
        return NULL;
    }

    pthread_mutex_lock(&mutex);

    while (curr != NULL) {
        if (curr->size >= size) {
            if (curr->size == size) {
                if (prev == NULL) {
                    freeList = curr->next;
                } else {
                    prev->next = curr->next;
                }
                return (void *) curr;
            } else {
                block *newBlock = (block *) ((char *) curr + sizeof(block) + size - sizeof(char));
                if (prev == NULL) {
                    freeList = newBlock;
                } else {
                    prev->next = newBlock;
                }
                printf("newBlock: %p\n", newBlock);
                newBlock->size = curr->size - size - sizeof(block);
                printf("newBlock size: %zu\n", newBlock->size);
                newBlock->next = curr->next;
                pthread_mutex_unlock(&mutex);
                return (void *) curr;
            }
        }
        prev = curr;
        curr = curr->next;
    }

    pthread_mutex_unlock(&mutex);
}

void my_free(void* ptr){
    block *curr, *prev;
    curr = freeList;
    prev = NULL;

    pthread_mutex_lock(&mutex);

    while (curr != NULL) {
        if ((char *) curr == (char *) ptr) {
            if (prev == NULL) {
                freeList = curr->next;
            } else {
                prev->next = curr->next;
            }
            pthread_mutex_unlock(&mutex);
            break;
        }
        prev = curr;
        curr = curr->next;
    }
    pthread_mutex_unlock(&mutex);
}

void my_allocator_destroy(void) {
    munmap(freeList, BLOCK_SIZE);
}
