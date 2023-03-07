#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

#define WRITE 1
#define READ 0

void wc_caller(const int pipefd[2]) {
    close(pipefd[WRITE]); // Close write-end

    dup2(pipefd[READ], STDIN_FILENO); // let stdin point to the read end.
    close(pipefd[READ]); // Close read-end too, as it can't be afterwards.
    execlp("wc", "wc", "-l", NULL); // execute wc using stdin that receives data from ls output
}

void ls_caller(const int pipefd[2]) {
    close(pipefd[READ]); // close read-end

    dup2(pipefd[WRITE], STDOUT_FILENO); // redirect stdout to write end. when ls is executed, the stdout output is written.
    close(pipefd[WRITE]); // close before execl, as the flow doesn't continue afterwards (except failure).
    execlp("ls", "ls", NULL);
}

int fork_and_exec(void (*child)(const int *), int pipefd[2]) {
    const pid_t cpid = fork();
    if (cpid == -1) {
        perror("Fork failed");
        exit(EXIT_FAILURE);
    }
    if (cpid == 0) {
        // we are the world, we are the children
        child(pipefd);
        // if the flow comes back, the rest of the program was not replaced by exec
        perror("Command not executed correctly");
        exit(EXIT_FAILURE);
    }
    return cpid;
}

int main(void) {
    int pipefd[2]; //0: read end, 1: write end

    if (pipe(pipefd) != 0) {
        perror("Pipe failed");
        return EXIT_FAILURE;
    }

    const pid_t cpid = fork_and_exec(wc_caller, pipefd);
    const pid_t cpid_2 = fork_and_exec(ls_caller, pipefd);

    // close to make sure children are not blocking
    close(pipefd[READ]);
    close(pipefd[WRITE]);

    waitpid(cpid, NULL, 0);
    waitpid(cpid_2, NULL, 0);
}
