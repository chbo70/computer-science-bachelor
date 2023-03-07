#include <fcntl.h>
#include <semaphore.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define SHARED_MEM_NAME "/sharedmemory"
#define SEM_PROD_NAME "/producer"
#define SEM_CONS_NAME "/consumer"

int main(int argc, char* argv[]) {
	if(argc > 3) {
		printf("Not enough Arguments");
		return EXIT_FAILURE;
	}
	char* endptr1 = NULL;
	uint64_t n = strtol(argv[1], &endptr1, 10);
	char* endptr2 = NULL;
	uint64_t b = strtol(argv[2], &endptr2, 10);

	int length = 4 + b * sizeof(uint64_t);

	// creates shared memory segment
	// S_IRUSR permission bits of USER for READ same for S_IWUSR
	uint64_t shm = shm_open(SHARED_MEM_NAME, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);

	// set size of shm object
	ftruncate(shm, length);

	// memory map shm
	uint64_t* ptr = mmap(NULL, length, PROT_WRITE | PROT_READ, MAP_SHARED, shm, 0);

	// setup semaphores
	sem_t* sem_prod = sem_open(SEM_PROD_NAME, O_CREAT, 0660, 0);
	if(sem_prod == SEM_FAILED) {
		perror("sem_open/producer");
		exit(EXIT_FAILURE);
	}

	sem_t* sem_cons = sem_open(SEM_CONS_NAME, O_CREAT, 0660, b);
	if(sem_cons == SEM_FAILED) {
		perror("sem_open/producer");
		exit(EXIT_FAILURE);
	}

	// producer
	u_int64_t producerID = fork();
	if(producerID == 0) {

		for(uint64_t i = 0; i < n; i++) {
			sem_wait(sem_cons);
			ptr[i % b] = i + 1;
			sem_post(sem_prod);
			// perror("prod: ");
		}
		return EXIT_SUCCESS;
	}

	// consumer
	uint64_t consumerID = fork();
	if((consumerID == 0)) {
		uint64_t* sum = &ptr[b];

		for(uint64_t i = 0; i < n; i++) {
			sem_wait(sem_prod);
			*sum += ptr[i % b];
			sem_post(sem_cons);
			// perror("consumer: ");
		}

		return EXIT_SUCCESS;
	}

	wait(NULL);
	wait(NULL);
	printf("Result: %ld\n", ptr[b]);

	sem_close(sem_prod);
	sem_close(sem_cons);
	shm_unlink(SHARED_MEM_NAME);
	sem_unlink(SEM_CONS_NAME);
	sem_unlink(SEM_PROD_NAME);
	return EXIT_SUCCESS;
}