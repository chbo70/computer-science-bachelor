CFLAGS = -std=c11 -Wall -Wextra -Wpedantic -lrt -pthread -D_POSIX_C_SOURCE -D_BSD_SOURCE -D_DEFAULT_SOURCE

.PHONY: all
all: task3_printer_server task3_printer

.PHONY: clean
clean: 
	$(RM) task3_printer_server task3_printer 

task3_printer_server: task3_printer_server.c
	$(CC) $(CFLAGS) -o $@ $^

task3_printer: task3_printer.c
	$(CC) $(CFLAGS) -o $@ $^


