#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
void termination_handler(int signum) {
	switch (signum)
    {
    case (SIGINT):
        write(STDOUT_FILENO, "received: SIGINT\n", 18);
        break;
    case (SIGUSR1):
        write(STDOUT_FILENO, "received: SIGUSR1\n", 19);
        break;
    case (SIGTERM):
        write(STDOUT_FILENO, "received: SIGTERM\n", 19);
	break;
    case (SIGKILL):
	write(STDOUT_FILENO, "received: SIGKILL\n", 19);
	break;
    default:
        break;
    }
}

int main(void) {
    //signals are listed in man 7 signal
    //pid can be determined with ps ax | grep processname
    struct sigaction new_action = {0};
    new_action.sa_handler = termination_handler;
    new_action.sa_flags = SA_RESTART;
    //kill -2 <pid>
    sigaction(SIGINT, &new_action, NULL);
    //kill -10 <pid>
    sigaction(SIGUSR1, &new_action, NULL);
    //kill -9 <pid>, note that SIGKILL cannot be caught or ignored
    sigaction(SIGKILL, &new_action, NULL);
    //kill -15 <pid>
    sigaction(SIGTERM, &new_action, NULL);

    while(1) {
	printf("in the loop! pid: %d\n", getpid());
	sleep(1);
    }

     return EXIT_SUCCESS;
}
