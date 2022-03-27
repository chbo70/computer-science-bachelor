#include <stdlib.h>
#include <stdio.h>

void sort(char array[], size_t length)
{

    for (size_t i = 0; i < length; i++)
    {
        for (size_t j = 0; j < (length - 1); j++)
        {
            if (array[j] > array[j + 1])
            {
                int temporary = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temporary;
            }
        }
    }
}

void sortedArray(char sortedArray[], size_t length)
{

    printf("sorted array: ");
    for (size_t i = 0; i < length; i++)
    {
        printf("%d, ", sortedArray[i]);
    }
    printf("\n");
}

int main()
{
    char newarray[] = {3, 0, 2, 4, 1};
    size_t length = sizeof(newarray) / sizeof(newarray[0]);
    printf("array to sort: ");
    for (size_t i = 0; i < length; i++)
    {
        printf("%d,", newarray[i]);
    }
    printf("\n");
    sort(newarray, length);
    sortedArray(newarray, length);
    return EXIT_SUCCESS;
}