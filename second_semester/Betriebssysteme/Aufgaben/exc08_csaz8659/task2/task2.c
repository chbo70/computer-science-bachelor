#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <port>\n", argv[0]);
        return EXIT_FAILURE;
    }

    int port = atoi(argv[1]);
    if (port < 1024 || port > 65535) {
        printf("Port must be between 1024 and 65535\n");
        return EXIT_FAILURE;
    }

    int sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock < 0) {
        perror("socket");
        return EXIT_FAILURE;
    }

    struct sockaddr_in addr;
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    addr.sin_addr.s_addr = INADDR_ANY;

    // reuse address
    int optval = 1;
    setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &optval, sizeof(optval));

    if (bind(sock, (struct sockaddr *) &addr, sizeof(addr)) < 0) {
        perror("bind");
        return EXIT_FAILURE;
    }

    if (listen(sock, 5) < 0) {
        perror("listen");
        return EXIT_FAILURE;
    }

    printf("Listening on port %d\n", port);

    bool running = true;
    while (running) {
        struct sockaddr_in client_addr;
        socklen_t client_addr_len = sizeof(client_addr);
        int client_sock = accept(sock, (struct sockaddr *) &client_addr, &client_addr_len);
        if (client_sock < 0) {
            perror("accept");
            return EXIT_FAILURE;
        }

        char buf[1024];
        while (1) {
            int n = read(client_sock, buf, sizeof(buf));
            if (n < 0) {
                perror("read");
                return EXIT_FAILURE;
            }
            // append null terminator
            buf[n] = '\0';

            if (n == 0) {
                break;
            }


            if (strcmp(buf, "/shutdown\r\n") == 0) {
                printf("Shutting down.\n");
                running = false;
                break;
            } else {
                // send back an Echo message
                printf("Echo: %s\n", buf);

                // append Echo: to the front of the message and send it back
                char echo_buf[1030];
                snprintf(echo_buf, sizeof(echo_buf), "Echo: %s", buf);
                write(client_sock, echo_buf, strlen(echo_buf));
            }
        }
        close(client_sock);
    }

    close(sock);
    return EXIT_SUCCESS;
}
