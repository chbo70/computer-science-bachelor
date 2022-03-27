#include <stdlib.h>
#include <stdio.h>


size_t copy_distinct(int dst[], const int src[], size_t len)
{
    size_t count = 0;
    size_t dub = 0;
    size_t position = 0;
    for (size_t k = 0; k < len; k++)
    {
        dst[k] = 0; // setzt dst auf 0
    }

    for (size_t l = 0; l < len; l++) // schleife sorgt das nicht mehrere 0 den counter hochsetzen
    {
        if (src[l] == 0)
        {
            count++;
            break; 
        }
    }

    for (size_t i = 0; i < len; i++)
    {
        dub = 0;
        for (size_t j = 0; j < len; j++)
        {
        if (src[i] == dst[j])
        {
            dub++; //setzt dublicate hoch, damit die if schleife nicht ausgefphrt wird
        }
        }

        if (dub == 0) //erhöht position im dst[] und den count, wenn es kein dublicate ist
        {
        dst[position] = src[i];
        position++;
        count++;
        }
    }

    return count;
}

int main()
{
    const int src[] = {5, 2, 4, 5, 2};
    int dst[] = {0, 0, 0, 0, 0};
    size_t len = sizeof(src) / sizeof(int);
    copy_distinct(dst, src, len);

    return EXIT_SUCCESS;
}
