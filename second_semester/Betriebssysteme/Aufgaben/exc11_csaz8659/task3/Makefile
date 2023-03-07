all: malloc_spy

malloc_spy:
	$(CC) -fPIC -shared -o malloc_spy.so malloc_spy.c

clean:
	rm -f malloc_spy.so