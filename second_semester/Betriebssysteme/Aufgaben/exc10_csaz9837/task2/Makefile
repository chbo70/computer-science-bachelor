CFLAGS = -std=gnu11 -Wall -Wextra -Wpedantic -O3 -g 
LDFLAGS = -lpthread

.PHONY: all
all: task2

.PHONY: clean
clean:
	$(RM) task2

task2: task2.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)


