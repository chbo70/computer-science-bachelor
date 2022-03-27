//Angenommen die Speichergröße einer Integer-Variable ist 2 Byte.
//Was gibt das folgende Programm aus?


#include <stdlib.h>
#include <stdio.h>

#define MAXROW 4
#define MAXCOL 3

int main(){
    int (*p) [MAXCOL];                               //int (*p) [3] = 2 * 3
    p = (int(*)[MAXCOL])malloc(MAXROW * sizeof(*p)); //wegen dem cast 2 byte 
    printf("%ld ,%ld\n", sizeof(p), sizeof(*p));

    //int *a;
    //printf("%ld\n", sizeof(*a));
    return EXIT_SUCCESS;
}