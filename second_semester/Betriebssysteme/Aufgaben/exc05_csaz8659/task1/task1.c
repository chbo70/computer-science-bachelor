#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <wait.h>
#include <unistd.h>

#define SMEM_NAME "/cons_prod2"

void producer(int N, int B, void *data) {
    int idx = 0;
    for (int num = 1; num <= N; ++num) {
        *(((int *) data) + (idx % B)) = num;
        idx++;
    }
}

void consumer(int N, int B, void *data) {
    long sum = 0;
    for (int idx = 0; idx < N; ++idx) {
        sum += *(((int *) data) + (idx % B));
    }
    printf("Result: %ld\n", sum);
}

void fork_run(void (*func)(int, int, void *), int N, int B, void *data) {
    int f = fork();
    if (f < 0) {
        perror("fork()");
        exit(EXIT_FAILURE);
    } else if (f == 0) {
        func(N, B, data);
        exit(EXIT_SUCCESS);
    }
}

int main(int argc, char **argv) {
    if (argc != 3) {
        fprintf(stderr, "Provide 2 integer parameters.\n");
        return EXIT_FAILURE;
    }
    int N = atoi(argv[1]);
    int B = atoi(argv[2]);

    if (N == 0 || B == 0) {
        fprintf(stderr, "N or B is zero or not an integer.\n");
        return EXIT_FAILURE;
    }

    int shm_fd = shm_open(SMEM_NAME, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    if (shm_fd < 0) {
        perror("shm_open()");
        return EXIT_FAILURE;
    }
    long smem_size = B * sizeof(int);
    if (ftruncate(shm_fd, smem_size) < 0) {
        perror("ftruncate()");
        return EXIT_FAILURE;
    }
    void *data = mmap(NULL, smem_size, PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd, 0);
    close(shm_fd); // can be closed.

    if (data == MAP_FAILED) {
        perror("mmap()");
        shm_unlink(SMEM_NAME);
        return EXIT_FAILURE;
    }
    // race conditions will occur by reading and writing at the same time.
    fork_run(producer, N, B, data);
    fork_run(consumer, N, B, data);

    wait(NULL);
    wait(NULL);

    munmap(data, smem_size);
    shm_unlink(SMEM_NAME);

    return EXIT_SUCCESS;
}

