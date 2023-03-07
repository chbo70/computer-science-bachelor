#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <wait.h>
#include <pthread.h>
#include <fcntl.h>

int my_global = 1;

void *thread_func() {
    my_global++;
    printf("%d\n", my_global);
    return NULL;
}

int main() {
    printf("%d\n", my_global);
    int pid = fork();
    if (pid == 0) {
        my_global++;
        return EXIT_SUCCESS;
    }
    waitpid(pid, NULL, 0);
    printf("%d\n", my_global);

    pthread_t thread_id;
    int rc = pthread_create(&thread_id, NULL, thread_func, NULL);
    if(rc != 0){
        perror("thread create");
        return EXIT_FAILURE;
    }
    pthread_join(thread_id, NULL);
    return EXIT_SUCCESS;
}