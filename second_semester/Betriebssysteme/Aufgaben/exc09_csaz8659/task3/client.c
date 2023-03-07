#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>
#include <pthread.h>

void *read_thread_func(void *arg) {
    int sockfd = *(int *) arg;
    char buffer[1024];
    while (true) {
        int n = read(sockfd, buffer, sizeof(buffer));
        if (n == 0) {
            break;
        }
        buffer[n] = 0; // null terminate
        printf("%s\n", buffer);
    }
    return NULL;
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Usage: %s <port> <username>\n", argv[0]);
        return EXIT_FAILURE;
    }


    int port = atoi(argv[1]);
    if (port < 1024 || port > 65535) {
        printf("Port must be between 1024 and 65535\n");
        return EXIT_FAILURE;
    }

    char username[128];
    strncpy(username, argv[2], 128);

    // act as the client
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) {
        printf("Error creating socket\n");
        return EXIT_FAILURE;
    }

    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(port);
    server_addr.sin_addr.s_addr = INADDR_ANY;

    if (connect(sockfd, (struct sockaddr *) &server_addr, sizeof(server_addr)) < 0) {
        printf("Error connecting to server\n");
        return EXIT_FAILURE;
    }

    // send username
    if (write(sockfd, username, strlen(username) + 1) < 0) {
        printf("Error sending username\n");
        return EXIT_FAILURE;
    }

    // start the read thread
    pthread_t read_thread;
    if (pthread_create(&read_thread, NULL, read_thread_func, (void *) &sockfd) != 0) {
        printf("Error creating read thread\n");
        return EXIT_FAILURE;
    }

    char buffer[1024];
    int n;
    while (true) {
        fgets(buffer, sizeof(buffer), stdin);
        n = write(sockfd, buffer, strlen(buffer));
        if (n < 0) {
            printf("Error writing to socket\n");
            return EXIT_FAILURE;
        }
        // if message starts with /quit or /shutdown, exit
        if (strncmp(buffer, "/quit", 5) == 0 || strncmp(buffer, "/shutdown", 9) == 0) {
            break;
        }
    }

    close(sockfd);

    pthread_join(read_thread, NULL);

    return EXIT_SUCCESS;
}
