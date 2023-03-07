//#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(void) {
	int pipefd[2]; // for read(0) and write end(1)
	int pid;
	pipe(pipefd);
	pid = fork();

	if(pid == 0) {
		//close(pipefd[1]); 
		dup2(pipefd[1],STDOUT_FILENO); 	// duplicates to STDOUT, closes it and opens it in the same file
		close(pipefd[0]);    			// close write end
		close(pipefd[1]);    			// close read end
		execlp("/bin/ls", "ls",NULL); 	// execlp is the same as execl, it just takes a path instead of a file
	} else {
		dup2(pipefd[0],STDIN_FILENO); 	// reads the output and duplicates it to the new filedescriptor
		close(pipefd[0]);   			// closes pipe write
		close(pipefd[1]);   			// now reads in for the execlp to use
		execlp("/bin/wc", "wc", "-l", NULL);
		wait(NULL);
	}
}
