#include <errno.h>
#include <fcntl.h>
#include <mqueue.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define BUFFER_SIZE 100

mqd_t queue;

struct mq_attr attr = {
        .mq_flags = 0,             /* Flags (ignored for mq_open()) */
        .mq_maxmsg = 10,           /* Max. # of messages on queue */
        .mq_msgsize = BUFFER_SIZE, /* Max. message size (bytes) */
        .mq_curmsgs = 0,           /* # of messages currently in queue (ignored for mq_open()) */
};

void handle_SIGINT() {
    write(STDOUT_FILENO, "\nServer shutting down!\n", 23);
    mq_close(queue);
    exit(0);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("missing queue name!");
        return EXIT_FAILURE;
    }

    char buffer[BUFFER_SIZE] = "\0";

    // create message queue
    queue = mq_open(argv[1], O_CREAT | O_RDONLY, 0660, &attr);

    //  check if queue couldn't be created
    if (queue == -1) {
        printf("failed to open mq\n");
        mq_close(queue);
        unlink(argv[1]);
        return EXIT_FAILURE;
    }
    unsigned int priority = 0;
    // signal hadnling
    struct sigaction sa;
    sa.sa_handler = &handle_SIGINT;
    sa.sa_flags = SA_RESTART;
    sigaction(SIGINT, &sa, NULL);
    while (1) {
        // receive message
        mq_receive(queue, buffer, sizeof(buffer) + 1, &priority);

        pid_t pid = fork();
        if (pid == 0) {
            // child
            if (buffer[0] != '\0') {
                printf("\nServing print job with priority %d:\n", priority);
                for (int i = 0; i < BUFFER_SIZE; i++) {
                    putc(buffer[i], stdout);
                    fflush(stdout);
                    usleep(200000);
                    if (buffer[i] == '\0') {
                        break;
                    }
                }
            }
            return EXIT_SUCCESS;
        } else {
            waitpid(pid, NULL, 0);
        }
    }
    mq_close(queue);
    mq_unlink(argv[1]);
    return EXIT_SUCCESS;
}