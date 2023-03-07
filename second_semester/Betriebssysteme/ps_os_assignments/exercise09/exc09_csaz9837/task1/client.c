#include <stdlib.h>
#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>

#define NAME_LEN 50

int main(int argc, char *argv[]) {
    if (argc > 3) {
        perror("to many arguments");
        return EXIT_FAILURE;
    }
    int port = (int) strtol(argv[1], NULL, 10);
    char buff[1024];
    int tcp_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (tcp_socket == -1) {
        perror("socket creation");
        return EXIT_FAILURE;
    }
    struct sockaddr_in addr;
    memset(&addr, 0, sizeof(struct sockaddr_in));

    //set ip, ports, ...
    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = htonl(INADDR_LOOPBACK);
    addr.sin_port = htons(port);

    //connect client to server
    if (connect(tcp_socket, (struct sockaddr *) &addr, sizeof(struct sockaddr_in)) != 0) {
        perror("connect to server");
        return EXIT_FAILURE;
    }
    write(tcp_socket, argv[2], sizeof(argv[2]));


    while (1) {
        memset(&buff, 0, sizeof(buff));
        int n = 0;
        while ((buff[n++] = getchar()) != '\n');
        write(tcp_socket, buff, sizeof(buff));
        if (strncmp(buff, "/shutdown", 9) == 0 || strncmp(buff, "/quit", 5) == 0) {
            printf("client disconnect\n");
            close(tcp_socket);
            return EXIT_SUCCESS;
        }
    }
}