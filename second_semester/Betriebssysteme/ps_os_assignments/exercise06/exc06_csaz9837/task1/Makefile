CFLAGS = -Wall -Wextra -Wpedantic -std=c11
LDFLAGS = -lpthread

.PHONY: all
all: task1a task1b

.PHONY: clean
clean:
	$(RM) task1a task1b

task1a: task1a.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)

task1b: task1b.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)