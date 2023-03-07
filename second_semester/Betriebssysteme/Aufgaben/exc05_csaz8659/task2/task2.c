#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <wait.h>
#include <unistd.h>
#include <semaphore.h>
#include <stdbool.h>

#define SMEM_NAME "/cons_prod2"

void producer_consumer(int N, int B, void *data, bool producer) {
    // semaphores take longer but eliminate race conditions.

    sem_t *read_signal = data;
    sem_t *write_signal = read_signal + 1;

    data = (void *) (read_signal + 2); // skip both semaphore data segments.

    if (producer) {
        int idx = 0;
        for (int num = 1; num <= N; ++num) {
            sem_wait(write_signal);
            *(((int *) data) + (idx % B)) = num;
            sem_post(read_signal);

            idx++;
        }
    } else {
        // we are the consumer.
        long sum = 0;
        for (int idx = 0; idx < N; ++idx) {
            sem_wait(read_signal);
            sum += *(((int *) data) + (idx % B));
            sem_post(write_signal);
        }
        printf("Result: %ld\n", sum);
    }
}


void fork_run(bool prod, int N, int B, void *data) {
    int f = fork();
    if (f < 0) {
        perror("fork()");
        exit(EXIT_FAILURE);
    } else if (f == 0) {
        producer_consumer(N, B, data, prod);
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
    long smem_size = B * sizeof(int) + sizeof(sem_t) * 2;
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

    // using the first two shared mem regions.
    sem_t *read_signal = data;
    sem_t *write_signal = read_signal + 1;

    // writing should be started instantly. (val = B to make write be able to write a full iteration circle ahead)
    if (sem_init(read_signal, true, 0) < 0 || sem_init(write_signal, true, B) < 0) {
        perror("sem_init()");
        shm_unlink(SMEM_NAME);
        return EXIT_FAILURE;
    }

    // race conditions will occur by reading and writing at the same time.
    fork_run(true, N, B, data);
    fork_run(false, N, B, data);

    wait(NULL);
    wait(NULL);

    munmap(data, smem_size);
    shm_unlink(SMEM_NAME);

    sem_destroy(read_signal);
    sem_destroy(write_signal);

    return EXIT_SUCCESS;
}

