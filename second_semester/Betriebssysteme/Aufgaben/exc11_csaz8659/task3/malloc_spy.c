#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

// we need to define __USE_GNU to get RTLD_NEXT
#define __USE_GNU
#include <dlfcn.h>


void print_number(size_t number) {
    if (number > 9) {
        print_number(number / 10);
    }
    const char digit = '0' + number % 10;
    write(STDOUT_FILENO, &digit, 1);
}

void *malloc(size_t size) {
    void *(*original_malloc)(size_t);
    original_malloc = dlsym(RTLD_NEXT, "malloc");
    void *result = original_malloc(size);
    write(STDOUT_FILENO, "Allocating ", 11);
    print_number(size);
    write(STDOUT_FILENO, " bytes\n", 7);
    return result;
}