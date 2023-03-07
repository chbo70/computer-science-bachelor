#include <stdlib.h>
#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>

int main() {
    //create -> bind -> listen
    //create
    int tcp_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (tcp_socket == -1) {
        printf("socket creation failed...\n");
        exit(0);
    }

    struct sockaddr_in addr;
    memset(&addr, 0, sizeof(struct sockaddr_in));
    //set Ports/IP
    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = htonl(INADDR_ANY);
    addr.sin_port = htons(1025);
    //set the options of a socket, SO_REUSEADDR is lets the socket know to use the adress anyway
    int x = 1;
    if (setsockopt(tcp_socket, SOL_SOCKET, SO_REUSEADDR, &x, sizeof(int)) < 0) {
        perror("setsockopt(SO_REUSEADDR) failed");
    }
    //bind
    if (bind(tcp_socket, (struct sockaddr *) &addr, sizeof(struct sockaddr_in)) == -1) {
        perror("failed to bind");
        return EXIT_FAILURE;
    }
    //listen
    if (listen(tcp_socket, 1024) == -1) {
        perror("failed to listen");
        return EXIT_FAILURE;
    }

    //accept
    int accepted;
    char buff[1024];
    socklen_t length = sizeof(addr);

    while (1) {
        accepted = accept(tcp_socket, (struct sockaddr *) NULL, &length);
        if (accepted == -1) {
            perror("failed to accept");
            return EXIT_FAILURE;
        }
        printf("Listening on Port: 1025\n");
        while (1) {
            memset(&buff, 0, sizeof(buff));
            if (!read(accepted, buff, sizeof(buff))) {
                break;
            }

            if (strcmp(buff, "shutdown\r\n") == 0) {
                printf("Server Shutdown\n");
                close(tcp_socket);
                return EXIT_SUCCESS;
            }
            printf("echo: %s", buff);
        }

    }
}