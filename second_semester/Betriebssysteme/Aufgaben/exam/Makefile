CFLAGS = -std=c11 -Wall -Wextra -Wpedantic -g
CFLAGS1 = -shared -fPIC -Wall -Werror
LDFLAGS = -ldl
#-c tells compiler to compile but not to link
#-shared tells compiler to create a shared library

.PHONY: all
all:  rpc_server rpc_client my_library.so my_library2.so

.PHONY: clean
clean:
	$(RM) rpc_server rpc_client my_library.so my_library2.so

rpc_server: rpc_server.c
	$(CC) $(CFLAGS) -o $@ $^

rpc_client: rpc_client.c
	$(CC) $(CFLAGS) -o $@ $^

my_library.so: my_library.c
	$(CC) $(CFLAGS1) -o $@ $^ $(LDFLAGS)

my_library2.so: my_library2.c
	$(CC) $(CFLAGS1) -o $@ $^ $(LDFLAGS)
