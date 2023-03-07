#define _GNU_SOURCE
#include <dlfcn.h>
#include <stdio.h>
#include <unistd.h>


void print_number(size_t number) {
    if(number > 9) {
        print_number(number / 10);
    }
    const char digit = '0' + number % 10;
    write(STDOUT_FILENO, &digit, 1);
}

void *malloc(size_t size) {
    //wrap malloc into this function
    void *(*real_malloc)(size_t);
    real_malloc = dlsym(RTLD_NEXT, "malloc");

    write(STDOUT_FILENO, "allocating ", 11);
    print_number(size);
    write(STDOUT_FILENO, " bytes\n", 7);

    void *ptr = real_malloc(size);
    return ptr;
}
//LD_PRELOAD=/home/bonti/OneDrive/Betriebssysteme/PS/ps_os_2022/exercise11/exc11_csaz9837/task3/malloc_spy.so ls