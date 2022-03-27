#include <inttypes.h>
#include <stdio.h>
#include <stdlib.h>

//int counter;
/*
void print_binary(uint32_t value){
     counter++;
    if (value <= 1){
        if (counter < 32) {  //print 0 until first 1
            print_binary(0);
        }
        printf("%" PRIu32, value);
        return;
    }
    else{
        print_binary(value/2);
        printf("%"PRIu32, value%2);
        return;
    }
} */

void print_binary(uint32_t value, int digits){
    if (digits == 0)
        return;
    print_binary(value/2, digits - 1); //takes the 32 digits and counts downwards until it becomes 0 
    printf("%"PRIu32, value%2); //prints the binary number over the last digits

}

int main(void) {
    uint32_t value;
    printf("value: ");
    if (scanf("%"SCNu32, &value) != 1) {
        fprintf(stderr, "ERROR: While reading the 'uint32_t' value an error occurred!\n");
        return EXIT_FAILURE;
    }
    printf("\n");
    printf("%" PRIu32, value);
    printf(" = 0b");
    print_binary(value, 32);
    printf("\n");
    return EXIT_SUCCESS;
}
