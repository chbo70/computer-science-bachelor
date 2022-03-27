#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int seconds;

    if (scanf("%d", &seconds) != 1) {
        printf("ERROR: While reading the 'int' value an error occurred!\n");
        return EXIT_FAILURE;
    }
    if (seconds < 0){
        printf("ERROR: ERROR: The entered number is negative\n");
        return EXIT_FAILURE;
    }
    int secondsnew = seconds % 60;
    int minutes = (seconds / 60) % 60;
    int hours = (seconds / 3600);

    printf("%d:%d:%d\n", hours, minutes, secondsnew);
    
    
    return EXIT_SUCCESS;
}