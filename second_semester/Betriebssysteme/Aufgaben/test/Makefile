CFLAGS = -std=gnu11 -Wall -Wextra -Wpedantic -g
LDFLAGS = -lpthread

.PHONY: all
all: simulate-exam

.PHONY: clean
clean:
	$(RM) simulate-exam

simulate-exam: simulate-exam.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)
