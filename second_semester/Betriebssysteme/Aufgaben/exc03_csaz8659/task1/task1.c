#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <wait.h>

double mc_pi(__int64_t S) {
    __int64_t in_count = 0;
    for (__int64_t i = 0; i < S; ++i) {
        const double x = rand() / (double) RAND_MAX;
        const double y = rand() / (double) RAND_MAX;
        if (x * x + y * y <= 1.f) {
            in_count++;
        }
    }
    return 4 * in_count / (double) S;
}

void child(int cpid, int child_index, int S) {
    srand(getpid()); // call this as a child, or the same random number generator is used for every child.
    double result = mc_pi(S);
    printf("Child %d PID = %d. mc_pi(%d) = %f.\n", child_index, cpid, S, result);
    // the order of these printfs is unpredictable. (context switch of CPU)
}
void create_child(int *child_pids, int child_index, int S) {
    int cpid = fork();
    if(cpid < 0) {
        perror("Couldn't fork.");
        exit(EXIT_FAILURE);
    }
    if (cpid == 0) {
        // we are a child!
        child(getpid(), child_index, S);
        exit(0); // finish.
    } else {
        child_pids[child_index] = cpid;
    }
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("usage: %s <number of threads (N)> <iterations per thread (S)>\n", argv[0]);
        return EXIT_FAILURE;
    }
    const int N = atoi(argv[1]);
    const int S = atoi(argv[2]);

    int child_pids[N];

    for (int i = 0; i < N; i++) {
        create_child(child_pids, i, S);
    }
    // wait only when all childs are initialized and running.
    for (int i = 0; i < N; i++) {
        wait(&child_pids[i]);
    }

    printf("Done.\n");
    return EXIT_SUCCESS;
}


