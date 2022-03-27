#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int value = 39; // TODO: Choose a value
    
    if((value % 13) == 0){
        printf("The given value %d is divisible by thirteen!\n", value);
    }
    else {
        printf("The given value %d is not divisible by thirteen!\n", value);
    }
    return EXIT_SUCCESS;
}
