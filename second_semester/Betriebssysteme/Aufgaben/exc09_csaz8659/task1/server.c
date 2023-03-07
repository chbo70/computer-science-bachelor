#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string.h>
#include <pthread.h>
#include <stdbool.h>
#include <stdatomic.h>

#define MAX_CLIENTS 20

/**
 * A struct to hold the information of a client.
 */
typedef struct client_data {
    int clientfd;
    pthread_t *p_client_thread;

    char username[128];
    atomic_bool running;
} client_data;

/**
 * A struct to hold the information of the server. The server stores the data of all clients.
 */
typedef struct server_data {
    int sockfd;
    pthread_t listen_thread;

    client_data *client_data_arr[MAX_CLIENTS];
    atomic_int client_arr_idx;
    pthread_mutex_t client_arr_mutex;
} server_data;

/**
 * The arguments that are passed to the client thread.
 */
typedef struct client_args {
    server_data *p_server_data;
    client_data *p_client_data;
} client_args;


/**
 * @return Number of running clients connected to the server
 */
int count_active_clients(server_data *data) {
    int count = 0;
    pthread_mutex_lock(&data->client_arr_mutex);
    for (int i = 0; i < data->client_arr_idx; i++) {
        if (data->client_data_arr[i] != NULL && data->client_data_arr[i]->running) {
            count++;
        }
    }
    pthread_mutex_unlock(&data->client_arr_mutex);
    return count;
}

void *client_thread(void *arg) {
    client_args *_client_args = (client_args *) arg;
    pthread_t listen_thread = _client_args->p_server_data->listen_thread;

    client_data *_client_data = _client_args->p_client_data;
    int clientfd = _client_data->clientfd;

    char buffer[1024];

    // indicates if the server should shut down
    bool is_shutdown = false;
    // indicates if the client disconnects
    bool is_quit = false;
    // for the first message, the client sends the username
    bool username_set = false;

    while (!is_quit) {
        int n = read(clientfd, buffer, 1024);
        if (n < 0) {
            perror("read");
            exit(1);
        }
        buffer[n] = '\0';

        if (!username_set) {
            // the first message is the username
            // cut the newline character and copy to username
            strncpy(_client_data->username, buffer, n - 1);

            username_set = true;
            printf("%s connected.\n", _client_data->username);
            continue;
        }

        // also handle EOF
        if (n == 0 || strncmp(buffer, "/quit", 5) == 0) {
            is_quit = true;
            printf("%s disconnected.\n", _client_data->username);
        } else if (strncmp(buffer, "/shutdown", 9) == 0) {
            is_quit = true;
            is_shutdown = true;
        } else {
            printf("%s: %s\n", _client_data->username, buffer);
        }
    }

    _client_data->running = false;
    close(clientfd);

    // if /shutdown, cancel the listener thread
    if (is_shutdown) {
        printf("Server is shutting down. Waiting for %d clients to disconnect.\n",
               count_active_clients(_client_args->p_server_data));
        pthread_cancel(listen_thread);
    }

    free(arg);
    return NULL;
}

void *listener_thread(void *arg) {
    server_data *_server_data = (server_data *) arg;
    _server_data->listen_thread = pthread_self();
    int sockfd = _server_data->sockfd;

    if (listen(sockfd, 5) < 0) {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    struct sockaddr_in cli_addr;
    socklen_t clilen = sizeof(cli_addr);

    // thread termination is called in client thread
    while (1) {
        int clientfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
        if (clientfd < 0) {
            perror("accept");
            exit(EXIT_FAILURE);
        }

        if (_server_data->client_arr_idx < MAX_CLIENTS) {
            client_args *_client_args = malloc(sizeof(client_args));
            // is freed in client_thread at the end

            _client_args->p_server_data = _server_data;

            pthread_t *c_thread = malloc(sizeof(pthread_t));
            // is freed in the main method at the end

            client_data *_client_data = malloc(sizeof(client_data));
            // is freed in the main method at the end

            _client_data->running = true;
            _client_data->p_client_thread = c_thread;
            _client_data->clientfd = clientfd;

            pthread_mutex_lock(&_server_data->client_arr_mutex);
            _server_data->client_data_arr[_server_data->client_arr_idx++] = _client_data;
            pthread_mutex_unlock(&_server_data->client_arr_mutex);

            _client_args->p_client_data = _client_data;

            if (pthread_create(c_thread, NULL, client_thread, _client_args) != 0) {
                perror("pthread_create");
                exit(EXIT_FAILURE);
            }
        } else {
            // we could implement overwriting of non-active clients here.
            printf("Server is full.\n");
            break;
        }
    }
    return NULL;
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <port>\n", argv[0]);
        return EXIT_FAILURE;
    }

    int port = atoi(argv[1]);
    if (port < 1024 || port > 65535) {
        printf("Port must be between 1024 and 65535.\n");
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

    server_data _server_data;
    _server_data.sockfd = sockfd;
    _server_data.client_arr_idx = 0;
    if(pthread_mutex_init(&_server_data.client_arr_mutex, NULL) != 0) {
        perror("pthread_mutex_init");
        return EXIT_FAILURE;
    }

    // spawn a thread to listen for incoming connections
    pthread_t thread;
    if (pthread_create(&thread, NULL, listener_thread, &_server_data) != 0) {
        perror("pthread_create");
        return EXIT_FAILURE;
    }

    // wait for the listener thread to finish
    pthread_join(thread, NULL);

    // wait for all client threads to finish
    for (int i = 0; i < _server_data.client_arr_idx; i++) {
        if (_server_data.client_data_arr[i]->running) {
            pthread_join(*(_server_data.client_data_arr[i]->p_client_thread), NULL);
        }
        free(_server_data.client_data_arr[i]->p_client_thread);
        free(_server_data.client_data_arr[i]);
    }

    close(sockfd);
    pthread_mutex_destroy(&_server_data.client_arr_mutex);

    return EXIT_SUCCESS;
}