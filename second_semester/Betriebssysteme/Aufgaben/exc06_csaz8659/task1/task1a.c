#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>
#include <pthread.h>

int my_global = 1;


void *thread_func(void *arg) {
    my_global++;
    return NULL;
}

int main() {

    printf("%d\n", my_global);
    pid_t pid = fork();
    if (pid < 0) {
        perror("fork");
        exit(EXIT_FAILURE);
    } else if (pid == 0) {
        // child, increment my_global
        my_global++;
        exit(EXIT_SUCCESS);
    } else {
        // parent, wait for child to finish
        wait(NULL);
        printf("%d\n", my_global);
    }

    pthread_t thread;
    if (pthread_create(&thread, NULL, thread_func, NULL) != 0) {
        perror("pthread_create");
        exit(EXIT_FAILURE);
    }
    pthread_join(thread, NULL);
    printf("%d\n", my_global);

    // forking does not increase the global variable, because the child process copies the parent's memory space.
    // the thread has access to the same memory space as the parent, and therefore the thread's global variable is affected.

    return EXIT_SUCCESS;
}
