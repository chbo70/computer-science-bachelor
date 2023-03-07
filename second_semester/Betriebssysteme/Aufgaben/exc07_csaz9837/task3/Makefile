CFLAGS=-std=gnu11 -Wall -Wextra -Wpedantic -O2
LDFLAGS=-lpthread

.PHONY: all
all: task3

.PHONY: clean
clean:
	$(RM) task3

task3: task3.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)