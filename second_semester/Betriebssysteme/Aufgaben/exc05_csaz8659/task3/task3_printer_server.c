#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>           /* For O_* constants */
#include <sys/stat.h>        /* For mode constants */
#include <mqueue.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include <errno.h>

#define MAX_MSG_SIZE 1024 - sizeof (u_int16_t)

// aligned to 1024
typedef struct message {
    char data[MAX_MSG_SIZE];
    u_int16_t length;
} message;


static sig_atomic_t received_signal = 0;

static void sig_handler(int sig_num) {
    // not safe to call printf here. using a status field instead.
    received_signal = sig_num;
}

void init_sig_handler() {
    struct sigaction sa;
    sigemptyset(&sa.sa_mask);
    sa.sa_handler = sig_handler;
    sa.sa_flags = 0;
    sigaction(SIGINT, &sa, NULL);
    sigaction(SIGTERM, &sa, NULL);
}

int main(int argc, char **argv) {
    if (argc != 2) {
        fprintf(stderr, "Please provide a message queue file name.");
        return EXIT_FAILURE;
    }
    const char *mq_name = argv[1];
    if (*mq_name != '/') {
        fprintf(stderr, "Name has to start with /.");
        return EXIT_FAILURE;
    }

    const struct mq_attr attr = {.mq_maxmsg = 5, .mq_msgsize = sizeof(message)};
    mqd_t mq = mq_open(mq_name, O_CREAT | O_RDONLY, S_IRUSR | S_IWUSR, &attr);
    if (mq < 0) {
        perror("mq_open()");
        return EXIT_FAILURE;
    }

    init_sig_handler();

    while (received_signal == 0) {
        message msg = {0};
        unsigned int priority = 0;
        if (mq_receive(mq, (char *) &msg, sizeof(msg), &priority) == -1) {
            // mq_receive interrupted. end program.
            if (errno != EINTR) // only log errors that are not interruption.
                perror("mq_receive()");
            break;
        }
        printf("\nServing print job with priority %d:\n", priority);
        if (msg.length == 0) {
            // quit on empty message.
            break;
        }
        for (int i = 0; i < msg.length; ++i) {
            char c = msg.data[i];
            fputc(c, stdout);
            fflush(stdout);
            usleep(200000);

            if (received_signal != 0)
                break;  // interrupted.
        }
    }

    printf("\nShutting down.\n");
    mq_close(mq);
    mq_unlink(mq_name);

    return EXIT_SUCCESS;
}


