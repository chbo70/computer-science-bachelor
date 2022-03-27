#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int number;
    if (scanf("%d", &number) != 1) {
        printf("ERROR: While reading the 'int' value an error occurred!\n");
        return EXIT_FAILURE;
    }
    if (number < 0){
        printf("ERROR: The entered number is negative\n");
        return EXIT_FAILURE;
    }
    if (number == 0)
    {
        printf("%d", number);
    }
    
    while (number > 0)
    {
        printf("%d", (number % 10));
        number = (number /10);
    }
    printf("\n");

    return EXIT_SUCCESS;
}
