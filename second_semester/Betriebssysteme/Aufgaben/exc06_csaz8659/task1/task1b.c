#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>

typedef struct thread_data thread_data;
struct thread_data {
    int thread_id;
    char **argv;
};

void *thread_function(void *arg) {
    thread_data *data = (thread_data *) arg;
    // compute sum until thread_id
    int sum = 0;
    for (int i = 0; i < data->thread_id; i++) {
        sum += atoi(data->argv[i]);
    }

    return (void *) sum; // return the value as a pointer.
}

int main(int argc, char *argv[]) {
    int N = argc - 1;
    if (N < 1) {
        printf("No arguments provided.\n");
        return EXIT_FAILURE;
    }
    pthread_t threads[N];
    thread_data *data_array[N];
    for (int i = 0; i < N; i++) {
        // pass all arguments to thread_function
        thread_data *data = malloc(sizeof(thread_data));
        data->thread_id = i + 1; // thread_id starts from 1
        data->argv = argv + 1; // skip argv[0]
        data_array[i] = data;
        
        if (pthread_create(&threads[i], NULL, thread_function, data) != 0) {
            perror("pthread_create");
            exit(EXIT_FAILURE);
        }
    }
    for (int i = 0; i < N; i++) {
        int *sum; // the pointer is the value.
        if (pthread_join(threads[i], (void **) &sum) != 0) {
            perror("pthread_join");
            exit(EXIT_FAILURE);
        }
        printf("sum%d = %d\n", i + 1, (int) sum);
    }

    // free data_array
    for (int i = 0; i < N; i++) {
        free(data_array[i]);
    }
    return EXIT_SUCCESS;
}