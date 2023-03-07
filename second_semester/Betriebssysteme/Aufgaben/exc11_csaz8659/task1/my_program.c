#include <stdio.h>
#include <stdlib.h>

int square_my_integer(int x) {
    return x * x;
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <number>\n", argv[0]);
        return 1;
    }
    int y = square_my_integer(atoi(argv[1]));
    printf("%d\n", y);
    return 0;
}
