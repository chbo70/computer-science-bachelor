#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <fcntl.h>
#include <unistd.h>
#include <errno.h>

int main(int argc, char **argv) {
    if (argc != 2) {
        fprintf(stderr, "Specify the client name.\n");
        return EXIT_FAILURE;
    }
    int len = strlen("/tmp/pipe_") + 5;
    char name[len];
    snprintf(name, len + 1, "/tmp/pipe_%s", argv[1]);

    const int fd = open(name, O_WRONLY);
    if (fd < 0) {
        fprintf(stderr, "open failed: %s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    while (1) {
        printf("Expression: ");
        char *line = NULL;
        size_t len_0 = 0;
        ssize_t line_len = getline(&line, &len_0, stdin);
        if (line_len == 0)
            continue;
        write(fd, line, line_len);
        if (strcmp(line, "exit\n") == 0) {
            // send exit and break.
            break;
        }
    }
    close(fd);
}
