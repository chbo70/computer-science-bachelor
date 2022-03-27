#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

bool safe_division(const int *x, const int *y, int *result)
{

    if (x == NULL)
    {
        return false;
    }
    else if (y == NULL)
    {
        return false;
    }
    else if (result == NULL)
    {
        return false;
    }

    if ((*y) == 0)
    {
        return false;
    }

    *result = *x / *y;
    return true;
}

int main()
{
    const int x = 1;
    const int y = 2;
    int result = 2;
    const int *xp, *yp;
    int *resultp;
    xp = &x;
    yp = &y;
    resultp = &result;
    // safe_division(*xp, *yp, *resultp);
    printf("%d\n", safe_division(xp, yp, resultp));
    return EXIT_SUCCESS;
}