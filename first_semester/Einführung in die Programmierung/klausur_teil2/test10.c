#include <stdio.h>
#include <stdlib.h>

double addition(double x, double y) { return x + y; }
double subtraction(double x, double y) { return x - y; }
double multiplication(double x, double y) { return x * y; }
double division(double x, double y) { return x / y; }

typedef union variant
{
    char cval[20];
    short sval;
    int ival;
    double dval;
} variant_t;


int main(void)
{
    variant_t var;
    printf("Union = %d Bytes\n", sizeof(var));
        char *ptr;
        printf("%d\n", sizeof(ptr));
        double (*fptr[4])(double d1, double d2) = {addition, subtraction, multiplication, division};

        double v1, v2;

        int operator;
        float test;
        printf("%d\n", sizeof(test));
        printf("Number 1: ");
        scanf("%lf", &v1);
        printf("Number 2: ");
        scanf("%lf", &v2);

        printf("Choose operator:\n");

        printf("0 = +\n1 = -\n2 = *\n3 = /\n");

        printf("Your choice: ");

        scanf("%d", &operator);

        if (!(operator>= 0 && operator<= 3))
        {
            printf("Error: Wrong operator!\n");
            return EXIT_FAILURE;
        }

    printf ("Result: %f\n", fptr[operator](v1, v2));

    return EXIT_SUCCESS;
}