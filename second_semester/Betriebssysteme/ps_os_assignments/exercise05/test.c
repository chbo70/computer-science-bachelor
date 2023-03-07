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

int main (int argc, char* argv[]){

    char* endptr1 = NULL;
	int n = strtol(argv[1], &endptr1, 10);
	char* endptr2 = NULL;
	int b = strtol(argv[2], &endptr2, 10);

    int length = 4*b + sizeof(uint64_t);
    int* memory = mmap(NULL, length, PROT_READ | PROT_WRITE, MAP_SHARED, -1, 0);

    int producerID = fork();
    if (producerID == 0)
    {
        for (int i = 0; i < n; i++)
        {
            memory[i%b] = i+1;
        }
        return EXIT_SUCCESS;
    } 
    int consumerID = fork();
    if (consumerID == 0)
    {
        uint64_t* result = (uint64_t*)&memory[b];
        for (int i = 0; i < n; i++)
        {
            *result += memory[i % b];
        }
        return EXIT_SUCCESS;
    }
    wait(NULL);
    wait(NULL);
    printf("Result: %d\n", memory[b]);
    munmap(memory,length);
    return EXIT_SUCCESS;
}
