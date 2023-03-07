#include <stdio.h>
#include <signal.h>
#include <string.h>

#pragma clang diagnostic push
#pragma ide diagnostic ignored "EndlessLoop"

static volatile int sig_rec = 0;

static void sig_handler(int sig_num) {
    // not safe to call printf here. using a status field instead.
    sig_rec = sig_num;
}

int main(void) {
    struct sigaction psa;
    memset(&psa, 0, sizeof psa);
    psa.sa_handler = sig_handler;
    psa.sa_flags = 0;

    sigaction(SIGINT, &psa, NULL);
    sigaction(SIGUSR1, &psa, NULL);
    sigaction(SIGTERM, &psa, NULL);
    sigaction(SIGKILL, &psa, NULL);

    while (1) {
        if(sig_rec != 0) {
            switch (sig_rec) {
                case SIGINT:
                    printf("INT\n");
                    break;
                case SIGUSR1:
                    printf("USR1\n");
                    break;
                case SIGTERM:
                    printf("TERM\n");
                    break;
                case SIGKILL:
                    printf("KILL\n");
                    break;
                default:
                    printf("Unknown SIG\n");
                    break;
            }
            sig_rec = 0;
        }
    }
    return 0;
}
#pragma clang diagnostic pop


// KILL kills, even though a handler is implemented.
// TERM doesn't.