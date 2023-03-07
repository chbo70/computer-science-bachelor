CFLAGS = -std=c11 -Wall -Wextra -Wpedantic -g
CFLAGS1 = -shared -fPIC -Wall -Werror
LDFLAGS = -ldl

#-c tells compiler to compile but not to link
#-shared tells compiler to create a shared library

.PHONY: exec
exec: map_number

.PHONY: all
all: square.so doubling.so negate.so leftshift.so rightshift.so reset.so map_number

.PHONY: clean
clean:
	$(RM) map_number

.PHONY: cleanAll
cleanAll:
	$(RM) square.so doubling.so negate.so leftshift.so rightshift.so reset.so map_number

square.so: square.c
	$(CC) $(CFLAGS1) -o $@ $^ $(LDFLAGS)

doubling.so: doubling.c
	$(CC) $(CFLAGS1) -o $@ $^ $(LDFLAGS)

negate.so: negate.c
	$(CC) $(CFLAGS1) -o $@ $^ $(LDFLAGS)

leftshift.so: leftshift.c
	$(CC) $(CFLAGS1) -o $@ $^ $(LDFLAGS)

rightshift.so: rightshift.c
	$(CC) $(CFLAGS1) -o $@ $^ $(LDFLAGS)

reset.so: reset.c
	$(CC) $(CFLAGS1) -o $@ $^ $(LDFLAGS)

map_number: map_number.c
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)
