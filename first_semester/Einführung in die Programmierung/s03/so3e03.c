#include <stdio.h>
#include <stdlib.h>


int main (void) {
    double a = 1.23e+14;
    float b = 0.034;
    char c = 'Z';
    unsigned int d = 3147483647;
    long e = 31474836472;
    int pad = 5;

    printf("a = %.2e\n", a);
    printf("b = %.3f\n", b);
    printf("c = %c\n", c);
    printf("d = %u\n", d);
    printf("e = %ld\n", e);

    printf ("b_10 = %.10f\n", b); // da es ein float ist wird mit 6 stellen von 0 aufgefüllt
    printf ("c_hex = 0x%x\n", c);
    printf ("c_pad = %0*d\n", pad, c);

    printf ("a = %15.4f\n", a);
    printf ("b = %20.4f\n", b);
    printf ("c = %15.d\n", c);
    printf ("d = %15.u\n", d);
    printf ("e = %15.ld\n", e);

    
    return EXIT_SUCCESS;
}