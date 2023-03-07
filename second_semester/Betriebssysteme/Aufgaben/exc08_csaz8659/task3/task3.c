#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string.h>
#include <pthread.h>
#include "thread_pool.h"

typedef struct listener_args {
    int sockfd;
    thread_pool *pool;
} listener_args;

typedef struct client_handler_args {
    int clientfd;
    pthread_t thread;
} client_handler_args;

void *handle_client(void *arg) {
    usleep(100000);

    client_handler_args *args = (client_handler_args *) arg;
    int clientfd = args->clientfd;
    pthread_t thread = args->thread;

    char buffer[1024];

    int n = read(clientfd, buffer, 1024);
    if (n < 0) {
        perror("read");
        exit(1);
    }
    buffer[n] = '\0';

    // parse the http request
    char *method = strtok(buffer, " ");
    char *path = strtok(NULL, " ");
    char *version = strtok(NULL, "\r\n");
    printf("method: %s\n", method);
    printf("path: %s\n", path);
    printf("version: %s\n", version);
    bool is_shutdown = strcmp(path, "/shutdown") == 0;

    // send the response. The response is a simple html page. If the request is /shutdown, show a message that the server is shutting down.
    char response[1024];
    sprintf(response, "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1>%s</h1></body></html>",
            is_shutdown ? "Server is shutting down" : "Hello World");

    write(clientfd, response, strlen(response));
    close(clientfd);

    // if /shutdown, cancel the thread
    if (is_shutdown) {
        pthread_cancel(thread);
    }

    free(arg);
    return NULL;
}

void *listen_for_connections(void *arg) {
    listener_args *args = (listener_args *) arg;
    int sockfd = args->sockfd;

    if (listen(sockfd, 5) < 0) {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    struct sockaddr_in cli_addr;
    socklen_t clilen = sizeof(cli_addr);

    // thread termination is called in handle_client
    while (1) {
        int clientfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
        if (clientfd < 0) {
            perror("accept");
            exit(EXIT_FAILURE);
        }

        client_handler_args *handler_args = malloc(sizeof(client_handler_args));
        handler_args->clientfd = clientfd;
        handler_args->thread = pthread_self();

        pool_submit(args->pool, handle_client, handler_args);
    }
}

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

    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) {
        perror("socket");
        return EXIT_FAILURE;
    }

    struct sockaddr_in addr;
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    addr.sin_addr.s_addr = INADDR_ANY;

    // reuse address
    int optval = 1;
    setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR, &optval, sizeof(optval));

    if (bind(sockfd, (struct sockaddr *) &addr, sizeof(addr)) < 0) {
        perror("bind");
        return EXIT_FAILURE;
    }

    listener_args args;
    args.sockfd = sockfd;
    args.pool = malloc(sizeof(thread_pool));
    pool_create(args.pool, 2);

    // spawn a thread to listen for incoming connections
    pthread_t thread;
    if (pthread_create(&thread, NULL, listen_for_connections, &args) != 0) {
        perror("pthread_create");
        return EXIT_FAILURE;
    }

    // wait for the thread to finish
    pthread_join(thread, NULL);

    pool_destroy(args.pool);
    close(sockfd);

    return EXIT_SUCCESS;
}
