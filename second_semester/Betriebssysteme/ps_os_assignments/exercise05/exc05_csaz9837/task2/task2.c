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

int main(int argc, char* argv[]) {
	if(argc > 3) {
		printf("Not enough Arguments");
		return EXIT_FAILURE;
	}
	//turn main arguments to integers
	uint64_t n = strtol(argv[1],NULL, 10);
	uint64_t b = strtol(argv[2], NULL, 10);

	uint64_t length = 4 * b * sizeof(uint64_t) + 2 * sizeof(sem_t);

	// creates shared memory segment
	// S_IRUSR permission bits of USER for READ same for S_IWUSR
	uint64_t shm = shm_open(SHARED_MEM_NAME, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);

	// set size of shm object
	ftruncate(shm, length);

	// memory map shm
	uint64_t* ptr = mmap(NULL, length, PROT_WRITE | PROT_READ, MAP_SHARED, shm, 0);

	// setup semaphores
	sem_t* sem_prod = (sem_t*)&ptr[b + sizeof(u_int64_t) + sizeof(sem_t)]; //sem_open(SEM_PROD_NAME, O_CREAT, 0660, 0);
	if(sem_prod == SEM_FAILED) {
		perror("sem_open/producer");
		return EXIT_FAILURE;
	}

	sem_t* sem_cons = (sem_t*)&ptr[b + sizeof(uint64_t)];//sem_open(SEM_CONS_NAME, O_CREAT, 0660, b);
	if(sem_cons == SEM_FAILED) {
		perror("sem_open/producer");
		return EXIT_FAILURE;
	}
	//initializes unnamed semaphores
	sem_init(sem_prod, 1,0);
	sem_init(sem_cons, 1,1);

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
	return EXIT_SUCCESS;
}
