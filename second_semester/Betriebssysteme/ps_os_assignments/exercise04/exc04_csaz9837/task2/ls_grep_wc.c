#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

int main(void) {
	int pipefd1[2]; // for read(0) and write end(1) -- first pipe
	int pipefd2[2]; // -"- second pipe
	int pid;

	pipe(pipefd1);

	if((pid = fork()) == 0) {
        //child
		dup2(pipefd1[1], 1); // duplicates to STDOUT, closes it and opens it in the same file
		close(pipefd1[0]);   // close write end
		close(pipefd1[1]);   // close read end
		execlp("ls", "ls", NULL); // execlp is the same as execl, it just takes a path instead of a file
	}
    //parent
	pipe(pipefd2);
	if(pipe(pipefd2) == -1) {
		perror("bad pipe1");
		exit(1);
	}

	if((pid = fork()) == -1) {
		perror("bad fork1");
		exit(1);
	} else if(pid == 0) {
		dup2(pipefd1[0], 0);
		dup2(pipefd2[1], 1);
		close(pipefd1[0]);
		close(pipefd1[1]);
		close(pipefd2[0]);
		close(pipefd2[1]);
		execlp("grep", "grep", "-v", "lab", NULL);
	}

    //always close 
	if((pid = fork()) == -1) {
		perror("bad fork2");
		exit(1);
	} else if(pid == 0) {
		dup2(pipefd2[0], 0);
		close(pipefd2[0]);
		close(pipefd2[1]);
        close(pipefd1[0]); //warum musst man hier closen und nicht davor
        close(pipefd1[1]);
		execlp("wc", "wc", "-l", NULL);
	}
    wait(NULL);
}
