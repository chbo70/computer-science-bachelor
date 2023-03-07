CFLAGS = -std=c11 -Wall -Wextra -Wpedantic -pthread 

.PHONY: all
all: task2

.PHONY: clean
clean:
	$(RM) task2

task2: task2.c
	$(CC) $(CFLAGS) -o $@ $^

