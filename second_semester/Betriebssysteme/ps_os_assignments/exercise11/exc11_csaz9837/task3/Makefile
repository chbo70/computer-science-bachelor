CFLAGS = -shared -fPIC -Wall -Wextra -Wpedantic
LDFLAGS = -ldl

.PHONY: all
all: malloc_spy.so

.PHONY: clean
clean:
	rm malloc_spy.so

malloc_spy.so: malloc_spy.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)