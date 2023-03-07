CFLAGS = -std=gnu11 -Wall -Wextra -Wpedantic -g
LDFLAGS = -lpthread

.PHONY: all
all: restaurant

.PHONY: clean
clean:
	$(RM) restaurant

restaurant: restaurant.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)
