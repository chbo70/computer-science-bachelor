CFLAGS = -std=c11 -Wall -Wextra -Wpedantic -pthread

.PHONY: all
all: task1

.PHONY: clean
clean:
	$(RM) task1

task1: task1.c
	$(CC) $(CFLAGS) -o $@ $^

