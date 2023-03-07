#include <stdlib.h>
#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>
#include <stdatomic.h>
#include <pthread.h>

#define NAME_LEN 50
#define MAX_CLIENTS 100
#define BUFFER_SIZE 1024
#define USERS 128

atomic_int counter = 0;

typedef struct {
    pthread_t *thread;
    int tcp_socket;
    socklen_t length;
    pthread_t *clientArr;
    int *counter2;
} listenerInfo;

typedef struct {
    char buffer[BUFFER_SIZE];
    pthread_t *thread;
    int tcp_socket;
    int accepted;
    socklen_t length;
    char name[NAME_LEN];
} clientInfo;


void *client_function(void *args) {
    clientInfo *clientArguments = (clientInfo *) args;
    pthread_t *clientThread = clientArguments->thread;
    char buff[BUFFER_SIZE];
    for (int i = 0; i < BUFFER_SIZE; ++i) {
        buff[i] = clientArguments->buffer[i];
    }
    int accepted = clientArguments->accepted;
    char name[NAME_LEN];
    for (int i = 0; i < NAME_LEN; ++i) {
        name[i] = clientArguments->name[i];
    }
    while (1) {
        memset(&buff, 0, sizeof(buff));

        if (!read(accepted, buff, sizeof(buff))) {
            break;
        } else {
            printf("%s: %s", name, buff);
        }
        if (strncmp(buff, "/quit", 5) == 0){
            printf("%s disconnected\n", name);
            break;
        }
        if (strncmp(buff, "/shutdown", 9) == 0 ) {
            printf("%s disconnected\n", name);
            counter -= 1;
            pthread_cancel(*clientThread);
            break;
        }
    }
    return NULL;
}

void *listen_function(void *args) {
    listenerInfo *listenArguments = (listenerInfo *) args;
    int tcp_socket = listenArguments->tcp_socket;
    socklen_t length = listenArguments->length;
    pthread_t *thread = listenArguments->thread;
    char name[NAME_LEN];
    int accepted;
    char buff[BUFFER_SIZE];

    while (1) {
        accepted = accept(tcp_socket, (struct sockaddr *) NULL, &length);
        if (accepted == -1) {
            perror("failed to accept");
            exit(0);
        }

        if (!read(accepted, buff, sizeof(buff))){
            break;
        }
        for (int i = 0; i < (int) sizeof(buff); ++i) {
            if (i < NAME_LEN) {
                name[i] = buff[i];
            } else {
                break;
            }
        }
        memset(buff, 0, sizeof(buff));
        printf("%s connected\n", name);

        clientInfo *clientArguments = malloc(sizeof(clientInfo));
        for (int i = 0; i < NAME_LEN; ++i) {
            clientArguments->name[i] = name[i];
        }
        clientArguments->accepted = accepted;
        clientArguments->tcp_socket = tcp_socket;
        clientArguments->thread = thread;
        if (pthread_create(&listenArguments->clientArr[*listenArguments->counter2], NULL, client_function,
                           (void *) clientArguments) != 0) {
            perror("client thread creation");
            exit(0);
        }
        *listenArguments->counter2 += 1;
        counter += 1;
    }
    return NULL;
}

int main(int argc, char *argv[]) {
    if (argc > 3) {
        printf("<Usage>: Port");
        return EXIT_FAILURE;
    }
    int port = atoi(argv[1]);
    //create -> bind -> listen
    //create
    int tcp_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (tcp_socket == -1) {
        printf("socket creation failed...\n");
        exit(0);
    }
    perror("socket creation");

    struct sockaddr_in server_addr;
    struct sockaddr_in client_addr;
    memset(&server_addr, 0, sizeof(struct sockaddr_in));
    memset(&client_addr, 0, sizeof(struct sockaddr_in));

    //set Ports/IP
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    server_addr.sin_port = htons(port);

    //set the options of a socket, SO_REUSEADDR is lets the socket know to use the adress anyway
    int x = 1;
    if (setsockopt(tcp_socket, SOL_SOCKET, SO_REUSEADDR, &x, sizeof(int)) < 0) {
        perror("setsockopt(SO_REUSEADDR) failed");
    }

    //bind
    if (bind(tcp_socket, (struct sockaddr *) &server_addr, sizeof(struct sockaddr_in)) == -1) {
        perror("failed to bind");
        return EXIT_FAILURE;
    }
    perror("binding");

    //listen
    if (listen(tcp_socket, 1024) == -1) {
        perror("failed to listen");
        return EXIT_FAILURE;
    }
    printf("listening on port: %d\n", port);

    int counter2 = 0;
    socklen_t length = sizeof(server_addr);
    listenerInfo *listener = (listenerInfo *) malloc(sizeof(listenerInfo));
    listener->tcp_socket = tcp_socket;
    listener->length = length;

    pthread_t clients[USERS];
    listener->clientArr = clients;
    pthread_t listenerThread;
    listener->thread = &listenerThread;
    listener->counter2 = &counter2;

    if (pthread_create(&listenerThread, NULL, &listen_function, (void *) listener) != 0) {
        perror("listener thread creation");
        return EXIT_FAILURE;
    }

    pthread_join(listenerThread, NULL);

    if (counter != 0) {
        printf("Server shutting down, waiting for %d clients to disconnect\n", counter);
    } else {
        printf("all clients disconnected!\n");
    }


    for (int i = 0; i < counter2; i++) {
        pthread_join(clients[i], NULL);
    }
}
