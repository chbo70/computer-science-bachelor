#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

typedef struct {
    int numberOfArguments;
    int currentSum;
    int *number;
} arguments_struct;

void *thread_func(void *arg) {
    //reinitialize the struct in order to access the same values
    arguments_struct *args = (arguments_struct *) arg;
    args->currentSum = 0;
    for (int i = 0; i <= args->numberOfArguments; i++) {
        args->currentSum += args->number[i];
        if (args->numberOfArguments == 1) {
        }
    }
    return NULL;
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        return EXIT_FAILURE;
    }
    int n = argc - 1;

    pthread_t threads[n];
    arguments_struct args[n];
    //creating array of size of the arguments passed for copying into the struct
    int numbers[argc];

    //parsing the input arguments to integers
    for (int i = 0; i < n; i++) {
        numbers[i] = (int) strtol(argv[i + 1], NULL, 0);
        if (numbers[i] == -1) {
            perror("parsing to int failed");
            return EXIT_FAILURE;
        }
        args[i].number = numbers;
        args[i].numberOfArguments = i;
    }
    for (int i = 0; i < n; i++) {
        if (pthread_create(&threads[i], NULL, &thread_func, (void *) &args[i]) != 0) {
            perror("help");
            return EXIT_FAILURE;
        }
    }

    for (int i = 0; i < n; i++) {
        //joins with terminated thread, if pthread_join is successful we can be sure that the thread has been terminated
        pthread_join(threads[i], NULL);
        printf("sum%d = %d\n", i, args[i].currentSum);
    }

    return EXIT_SUCCESS;
}