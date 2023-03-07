#include <stdio.h>
#include <stdlib.h>

int square_my_integer(int x){
    return x*x;
}

//When would you use dynamic linking?
//When you want to load a library at runtime.

//When would you use static linking?
//When you want to load a library at compile time.

int main(int argc, char *argv[]){
    if (argc != 2) {
        printf("Usage: %s <number>\n", argv[0]);
        return 1;
    }
    int number = atoi(argv[1]);
    printf("%d\n", square_my_integer(number));
    return 0;
}