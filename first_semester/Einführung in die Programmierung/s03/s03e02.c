#include <stdio.h>
#include <stdlib.h>
#include <float.h>
#include <limits.h>

int main(void){
    int b = 2.0f * 017;
    float a = 41 / (float)17;
    int e = INT_MAX;
    int d = (1 + DBL_MAX) / DBL_MAX;
    printf ("%d\n", b);
    printf ("%f\n", a);
    printf ("%d\n", d);
    printf ("%d\n", e);
    return EXIT_SUCCESS;
}