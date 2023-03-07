# A makefile to compile some_library.c as a shared library
all: some_library my_program

some_library:
	$(CC) -shared -o some_library.so some_library.c

# compile my_program.c as a fully static executable
my_program:
	$(CC) -static -o my_program my_program.c

clean:
	$(RM) some_library.so
	$(RM) my_program
