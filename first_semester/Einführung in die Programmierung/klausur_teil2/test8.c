#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    enum boolean
    {
        FALSE,
        TRUE
    } bool;

    enum test1
    {
        ALPHA = 5,
        BETA = 3,
        GAMMA = 6
    };

    enum months
    {
        JAN = 2,
        FEB,
        MAR,
        APR,
        MAY,
        JUN,
        JUL,
        AUG,
        SEP,
        OCT,
        NOV,
        DEZ
    };

    bool = TRUE;

    enum test1 t1 = GAMMA;
    enum months mon = APR;
    enum test1 t2 = 100;

    printf("%d\n", bool);
    printf("%d\n", t1);
    printf("%d\n", mon);
    printf("%d\n", t2);

    return EXIT_SUCCESS;
}