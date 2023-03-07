#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    if (argc == 2) { // including the executable path.
        if (argv[1]) {
            char *p;
            long arg = strtol(argv[1], &p, 10); // convert arg 1 to long.
            if (*p != '\0') {
                // char* didn't scan fully (error at some point).
                return 3;
            }
            return (int) (arg % 2);
        }
    }
    return 2;
}
// exit status is retrievable with echo "$?"

// with ; multiple commands can be executed at the same time.
// && is similar, but only executes the right side if the left side has exit code 0.
// || is the same as &&, but only executes if the left side fails.

// true executable returns exit code 0
// false returns exit code 1
