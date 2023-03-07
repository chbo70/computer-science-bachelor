#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>
#include <poll.h>
#include <stdbool.h>

#define PIPE_BUFF 128

int calculate_and_print(float v1, char op, float v2);

void fifo_poll_loop(char *const *argv, int client_count, struct pollfd *pfd);


int main(int argc, char **argv) {
    if (argc < 2) {
        fprintf(stderr, "Specify the client names.\n");
        return EXIT_FAILURE;
    }
    int client_count = argc - 1;
    struct pollfd pfd[client_count];
    char *names[client_count];
    // fifo initialization
    for (int i = 0; i < client_count; ++i) {
        // name generation.
        int len = strlen("/tmp/pipe_") + 5;
        char name[len];
        snprintf(name, len + 1, "/tmp/pipe_%s", argv[i + 1]);
        names[i] = name;

        remove(name); // ignore "No such file or directory" errors.

        const mode_t permission = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH; // 644
        if (mkfifo(name, permission) != 0) {
            fprintf(stderr, "mkfifo failed: %s\n", strerror(errno));
            exit(EXIT_FAILURE);
        }
        const int fd = open(name, O_RDONLY);
        if (fd < 0) {
            fprintf(stderr, "open failed: %s\n", strerror(errno));
            exit(EXIT_FAILURE);
        }
        printf("%s connected.\n", argv[i + 1]);

        pfd[i].fd = fd;
        pfd[i].events = POLLIN;
    }
    fifo_poll_loop(argv, client_count, pfd);

    for (int i = 0; i < client_count; ++i) {
        close(pfd[i].fd);
        unlink(names[i]);
    }
    return 0;
}

bool all_disconnected(struct pollfd *pfd, int count) {
    for (int i = 0; i < count; ++i) {
        if (pfd[i].fd != -1)
            return false;
    }
    return true;
}

void fifo_poll_loop(char *const *argv, int client_count, struct pollfd *pfd) {
    char incoming_data[PIPE_BUFF + 1];
    printf("All clients connected. Starting poll loop.\n");

    while (!all_disconnected(pfd, client_count)) {
        int poll_res;
        poll_res = poll(pfd, client_count, -1);
        if (poll_res == -1) {
            printf("Error at polling: %s\n", strerror(errno));
            return;
        } else if (poll_res == 0) {
            printf("Poll timeout.\n");
            return;
        }
        for (int i = 0; i < client_count; ++i) {
            if (!(pfd[i].revents & POLLIN)) continue;

            printf("%s: ", argv[i + 1]);

            for (int j = 0; j < PIPE_BUFF; ++j)
                incoming_data[j] = 0; // clear old incoming data.

            int res = read(pfd[i].fd, &incoming_data, PIPE_BUFF);
            if (res == 0 || (res > 0 && strcmp(incoming_data, "exit\n") == 0)) {
                printf(" disconnected.\n");
                pfd[i].fd = -1; // let poll ignore the fd.
            } else if (res < 0) {
                printf(" error at reading. read returned %d: %s\n", res, strerror(errno));
            } else if (res == PIPE_BUFF) {
                printf(" message length exceeding PIPE_BUF.\n");
            } else {
                float v1;
                char op;
                float v2;

                if (sscanf(incoming_data, "%f %c %f", &v1, &op, &v2) < 3 || calculate_and_print(v1, op, v2) != 0) {
                    printf(" %s is malformed.\n", incoming_data);
                }
            }
        }
    }
    printf("All clients disconnected. Terminating.\n");
}


int calculate_and_print(float v1, char op, float v2) {
    float result;
    switch (op) {
        case '+':
            result = v1 + v2;
            break;
        case '-':
            result = v1 - v2;
            break;
        case '*':
            result = v1 * v2;
            break;
        case '/':
            result = v1 / v2;
            break;
        default:
            // return error, invalid operator.
            return -1;
    }
    printf("%g %c %g = %g\n", v1, op, v2, result);
    return 0;
}


