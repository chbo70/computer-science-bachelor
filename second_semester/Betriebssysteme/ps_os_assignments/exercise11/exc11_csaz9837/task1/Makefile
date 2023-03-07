CFLAGS = -std=c11 -Wall -Wextra -Wpedantic -static
CFLAGS1 = -shared -fPIC -Wall -Werror
LDFLAGS = -ldl
#-c tells compiler to compile but not to link
#-shared tells compiler to create a shared library

.PHONY: all
all: some_library.so my_program

.PHONY: clean
clean:
	$(RM) some_library.so my_program

some_library.so: some_library.c
	$(CC) $(CFLAGS1) -o $@ $^ $(LDFLAGS)

my_program: my_program.c
	$(CC) $(CFLAGS) -o $@ $^
