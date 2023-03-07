#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>

#define MAX_LENGTH 512

int main (int argc, char* argv[]){
    if (argc != 5){
        fprintf(stderr, "Usage: <port> <shared library> <function name> <argument>\n");
        return EXIT_FAILURE;
    }
    int port = (int) strtol(argv[1], NULL, 10);
    char shared_library[MAX_LENGTH];
    strncpy(shared_library, argv[2], MAX_LENGTH);

    char function_name[MAX_LENGTH];
    strncpy(function_name, argv[3], MAX_LENGTH);

    char argument[MAX_LENGTH];
    strncpy(argument, argv[4], MAX_LENGTH);

    // act as the client
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) {
        fprintf(stderr, "Error creating socket\n");
        return EXIT_FAILURE;
    }

    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(port);
    server_addr.sin_addr.s_addr = INADDR_ANY;


    //send the arguments to the server
    if (connect(sockfd, (struct sockaddr *) &server_addr, sizeof(server_addr)) < 0) {
        fprintf(stderr, "Error connecting to server\n");
        return EXIT_FAILURE;
    }

    if (write(sockfd, shared_library, strlen(shared_library) + 1) < 0) {
        fprintf(stderr, "Error sending shared library\n");
        return EXIT_FAILURE;
    }


    if (write(sockfd, function_name, strlen(function_name) + 1) < 0) {
        fprintf(stderr, "Error sending function name\n");
        return EXIT_FAILURE;
    }
    //printf("%s %lu\n", function_name, strlen(function_name));

    if (write(sockfd, argument, strlen(argument) + 1) < 0) {
        fprintf(stderr, "Error sending argument\n");
        return EXIT_FAILURE;
    }

    char buffer[MAX_LENGTH];
    int n;

    //read the result from the server

    close(sockfd);

    return EXIT_SUCCESS;
}