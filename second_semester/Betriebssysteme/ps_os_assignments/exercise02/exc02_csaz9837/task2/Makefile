CFLAGS = -std=c11 -Wall -Wextra -Wpedantic -D_POSIX_C_SOURCE -D_BSD_SOURCE -D_DEFAULT_SOURCE

.PHONY: all
all: signal_handler

.PHONY: clean
clean:
	$(RM) signal_handler

signal_handler: signal_handler.c
	$(CC) $(CFLAGS) -o $@ $^

