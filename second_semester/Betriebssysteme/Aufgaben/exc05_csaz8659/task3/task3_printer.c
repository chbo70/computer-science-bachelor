#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>           /* For O_* constants */
#include <sys/stat.h>        /* For mode constants */
#include <mqueue.h>


#define MAX_MSG_SIZE 1024 - sizeof (u_int16_t)

// aligned to 1024
typedef struct message {
    char data[MAX_MSG_SIZE];
    u_int16_t length;
} message;

int main(int argc, char **argv) {
    if (argc != 3) {
        fprintf(stderr,
                "Please provide a message queue file name and print priority (uint). Also input a file via stdin.\n");
        return EXIT_FAILURE;
    }

    unsigned int priority = atoi(argv[2]);

    const char *mq_name = argv[1];
    if (*mq_name != '/') {
        fprintf(stderr, "Name has to start with /.");
        return EXIT_FAILURE;
    }

    // read input into buffer.
    message msg = {0};
    unsigned long len = fread(&msg, sizeof(char), MAX_MSG_SIZE, stdin);
    msg.length = (u_int16_t) len;

    const struct mq_attr attr = {.mq_maxmsg = 5, .mq_msgsize = sizeof(message)};
    mqd_t mq = mq_open(mq_name, O_WRONLY, S_IRUSR | S_IWUSR, &attr);
    if (mq < 0) {
        perror("mq_open()");
        return EXIT_FAILURE;
    }

    if (mq_send(mq, (const char *) &msg, sizeof(msg), priority) != 0) {
        perror("mq_send()");
        return EXIT_FAILURE;
    }
    mq_close(mq);
    // don't unlink. there can be more clients.

    return EXIT_SUCCESS;
}
