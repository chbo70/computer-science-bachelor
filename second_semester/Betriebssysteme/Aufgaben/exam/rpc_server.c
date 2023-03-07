#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
//#include <netinet/tcp.h>
#include <unistd.h>
#include <strings.h>
#include <string.h>
#include <dlfcn.h>

#define MAX_CLIENTS 20
#define MAX_LENGTH 128

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Usage: <port>");
        return EXIT_FAILURE;
    }
    int port = (int) strtol(argv[1], NULL, 10);

    //copied from server exercise 09
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) {
        fprintf(stderr, "failed socket assignment\n");
        return EXIT_FAILURE;
    }

    struct sockaddr_in addr;
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    addr.sin_addr.s_addr = INADDR_ANY;

    //copied from exercise 09
    int optval = 1;
    setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR, &optval, sizeof(optval));

    //bind -> listen
    if (bind(sockfd, (struct sockaddr *) &addr, sizeof(addr)) < 0) {
        fprintf(stderr, "failed bind\n");
        return EXIT_FAILURE;
    }

    //listen
    if (listen(sockfd, MAX_CLIENTS) < 0) {
        fprintf(stderr, "failed listen\n");
        return EXIT_FAILURE;
    }
    char buff[MAX_LENGTH];
    socklen_t length = sizeof(addr);
    int connfd = accept(sockfd, (struct sockaddr *) &addr, &length);
    if (connfd < 0) {
        fprintf(stderr, "failed accept\n");
        return EXIT_FAILURE;
    }

    //read the shared library
    if (read(connfd, buff, MAX_LENGTH) < 0) {
        fprintf(stderr, "failed read\n");
        return EXIT_FAILURE;
    }
    char *shared_library = buff;
    printf("shared_library: %s\n", shared_library);

    //read the function name
    if (read(connfd, buff, MAX_LENGTH) < 0) {
        fprintf(stderr, "failed read\n");
        return EXIT_FAILURE;
    }
    char *function_name = buff;
    printf("function_name: %s\n", function_name);
    //memset(buff, 0, MAX_LENGTH);
    //read the argument
    if (read(connfd, buff, MAX_LENGTH) < 0) {
        fprintf(stderr, "failed read\n");
        return EXIT_FAILURE;
    }
    char *argument = buff;

    printf("argument: %s\n", argument);


    //somehow it doesnt write me everything, i probably should have createt more sockets ore a listener thread
    //also it cant open the so file, cause of the path
    //cant manage it to read the argument correctly
    /*
    int number = 9;

    void *handle = dlopen("my_library", RTLD_LAZY);
    if (!handle) {
        printf("Cannot open %s: %s\n", "my_library", dlerror());
        return 1;
    }
    int (*plugin)(int) = dlsym(handle, "function1");
    if (!plugin) {
        printf("Cannot find symbol 'plugin' in %s\n", "my_library");
        return 1;
    }
    number = plugin(number);
    printf("%s: %d\n", "my_library", number);
    dlclose(handle);
    */

    close(sockfd);
    return EXIT_SUCCESS;
}