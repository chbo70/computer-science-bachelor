CFLAGS = -std=c11 -Wall -Wextra -Wpedantic -O2
LDFLAGS = -lpthread

.PHONY: all
all: task1a

.PHONY: clean
clean:
	$(RM) task1a

task1a: task1a.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)
