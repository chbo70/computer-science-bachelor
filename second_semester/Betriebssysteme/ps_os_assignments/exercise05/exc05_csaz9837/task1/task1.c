#include <fcntl.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char* argv[]) {
	if(argc > 3) {
		return EXIT_FAILURE;
	}
	int n = strtol(argv[1], NULL, 10);
	int b = strtol(argv[2], NULL, 10);

	int length = 4 * b + sizeof(uint64_t);
	const char* name = "/sharedmemory";

	// creates shared memory object
	int shm = shm_open(name, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
	// S_IRUSR permission bits of USER for READ same for S_IWUSR
	//  perror("shm_op");

	// set size of shm object
	ftruncate(shm, length);

	// memory map shm
	int* ptr = mmap(NULL, length, PROT_WRITE | PROT_READ, MAP_SHARED, shm, 0);

	// producer
	int producerID = fork();
	if(producerID == 0) {
		for(int i = 0; i < n; i++) {
			ptr[i % b] = i + 1;
			// perror("prod: ");
		}
		return EXIT_SUCCESS;
	}
	// the problem with the output is that either the consumer or the producer finsihes first which
	// means a racing condition is invoked

	// consumer
	int consumerID = fork();
	if((consumerID == 0)) {
		uint64_t* sum = (u_int64_t*)&ptr[b];
		for(int i = 0; i < n; i++) {
			*sum += ptr[i % b];
			// perror("consumer: ");
		}
		return EXIT_SUCCESS;
	}
	
	wait(NULL);
	wait(NULL);
	printf("Result: %d\n", ptr[b]);
	shm_unlink(name);
	munmap(ptr, length);
	return EXIT_SUCCESS;
}
