#include <fcntl.h>
#include <mqueue.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <time.h>
#include <unistd.h>

#define BUFFER_SIZE 100

int main(int argc, char* argv[]) {
	if(argc < 4) {
		printf("Usage: queuename priority $< test.txt");
		return EXIT_FAILURE;
	}
    unsigned int priority = strtol(argv[2], NULL, 10);

	struct mq_attr attr = {
		.mq_flags = 0,     /* Flags (ignored for mq_open()) */
		.mq_maxmsg = 10,   /* Max. # of messages on queue */
		.mq_msgsize = BUFFER_SIZE, /* Max. message size (bytes) */
		.mq_curmsgs = 0,   /* # of messages currently in queue (ignored for mq_open()) */
	};
    char buffer[BUFFER_SIZE];

    mqd_t queue = mq_open(argv[1], O_CREAT | O_WRONLY, 0660, &attr);
    if (queue == -1)
    {
        printf("failed to open message queue");
        mq_close(queue);
        unlink(argv[1]);
        return EXIT_FAILURE;
    }
    strcpy(buffer, argv[3]);

    int message = mq_send(queue, buffer, sizeof(buffer), priority);
    if (message == -1)
    {
        printf("failed to send message");
        mq_close(queue);
        unlink(argv[1]);
        return EXIT_FAILURE;
    }
    mq_close(queue);
    unlink(argv[1]);
	return EXIT_SUCCESS;
}