#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

#define WRITE 1
#define READ 0

void close_all(const int *pfd) {
    close(pfd[READ]);
    close(pfd[WRITE]);
}

void wc_caller(const int unused[2], const int grep_to_wc_pfd[2]) {
    close(grep_to_wc_pfd[WRITE]); // Close write-end

    dup2(grep_to_wc_pfd[READ], STDIN_FILENO); // let stdin point to the read end.
    close(grep_to_wc_pfd[READ]); // Close read-end too, as it can't be afterwards.

    close_all(unused); // we don't use the unused one, so close it, or it will block.
    execlp("wc", "wc", "-l", NULL); // execute wc using stdin that receives data from grep output
}

void grep_caller(const int ls_to_grep_pfd[2], const int grep_to_wc_pfd[2]) {
    close(ls_to_grep_pfd[WRITE]); // Close write-end

    dup2(ls_to_grep_pfd[READ], STDIN_FILENO); // let stdin point to the read end.
    close(ls_to_grep_pfd[READ]); // Close read-end too, as it can't be afterwards.


    close(grep_to_wc_pfd[READ]); // close read-end

    dup2(grep_to_wc_pfd[WRITE], STDOUT_FILENO); // redirect stdout to write end. when ls is executed, the stdout output is written.
    close(grep_to_wc_pfd[WRITE]); // close before execl, as the flow doesn't continue afterwards (except failure).

    execlp("grep", "grep", "-v", "lab", NULL); // execute grep using stdin that receives data from ls output
}

void ls_caller(const int ls_to_grep_pfd[2], const int unused[2]) {
    close(ls_to_grep_pfd[READ]); // close read-end

    dup2(ls_to_grep_pfd[WRITE], STDOUT_FILENO); // redirect stdout to write end. when ls is executed, the stdout output is written.
    close(ls_to_grep_pfd[WRITE]); // close before execl, as the flow doesn't continue afterwards (except failure).

    close_all(unused); // we don't use the unused one, so close it, or it will block.
    execlp("ls", "ls", NULL);
}

int fork_and_exec(void (*child)(const int *, const int *), const int ls_to_grep_pfd[2], const int grep_to_wc_pfd[2]) {
    const pid_t cpid = fork();
    if (cpid == -1) {
        perror("Fork failed");
        exit(EXIT_FAILURE);
    }
    if (cpid == 0) {
        // we are the world, we are the children
        child(ls_to_grep_pfd, grep_to_wc_pfd);
        // if the flow comes back, the rest of the program was not replaced by exec
        perror("Command not executed correctly");
        exit(EXIT_FAILURE);
    }
    return cpid;
}

int main(void) {
    int ls_to_grep_pfd[2]; //0: read end, 1: write end
    int grep_to_wc_pfd[2]; //0: read end, 1: write end

    if (pipe(ls_to_grep_pfd) != 0) {
        perror("Pipe 0 failed");
        return EXIT_FAILURE;
    }
    if (pipe(grep_to_wc_pfd) != 0) {
        perror("Pipe 1 failed");
        return EXIT_FAILURE;
    }
    const pid_t cpid = fork_and_exec(wc_caller, ls_to_grep_pfd, grep_to_wc_pfd);
    const pid_t cpid_2 = fork_and_exec(grep_caller, ls_to_grep_pfd, grep_to_wc_pfd);
    const pid_t cpid_3 = fork_and_exec(ls_caller, ls_to_grep_pfd, grep_to_wc_pfd);

    // close to make sure children are not blocking
    close(ls_to_grep_pfd[READ]);
    close(ls_to_grep_pfd[WRITE]);

    close(grep_to_wc_pfd[READ]);
    close(grep_to_wc_pfd[WRITE]);

    waitpid(cpid, NULL, 0);
    waitpid(cpid_2, NULL, 0);
    waitpid(cpid_3, NULL, 0);
}
