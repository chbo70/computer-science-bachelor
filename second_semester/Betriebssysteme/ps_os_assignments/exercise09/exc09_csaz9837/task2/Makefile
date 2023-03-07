CFLAGS = -std=c11 -Wall -Wextra -Wpedantic -O2
LDFLAGS = -lpthread

.PHONY: all
all: server client

.PHONY: clean
clean:
	$(RM) server client

server: server.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)

client: client.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)
