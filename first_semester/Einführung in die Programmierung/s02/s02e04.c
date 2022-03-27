#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int quantity; // TODO: Choose a value
    printf("Quantity eingeben:");
    scanf("%d", &quantity);
    int value; 
    printf("Value eingeben:");
    scanf("%d", &value); // TODO: Choose a value
    int i = 1;
    int sol;
    
    while (i <= quantity)
    {
        sol = (i) * value;
        printf("%d\n", sol);
        i++;
        
    }
    if (quantity < 0)
    {
        printf ("Die eingegebene Zahl ist negativ!\n");
    }
    
    return EXIT_SUCCESS;
}
