CFLAGS = -std=c11 -Wall -Wextra -Wpedantic

.PHONY: all
all: even

.PHONY: clean
clean:
	$(RM) even

even: even.c
	$(CC) $(CFLAGS) -o $@ $^

