#include <stdio.h>
#include <stdlib.h>

int main(void)
{

    FILE *fpr = fopen("datei.txt", "w");

    char content[] = "text";

    if (fpr == NULL)
    {
        printf("Fehler beim Offnen!");
        return EXIT_FAILURE;
    }

    if (fputs(content, fpr) == EOF)
    {
        printf("Fehler beim Schreiben!");
        return EXIT_FAILURE;
    }

    if (fclose(fpr) != 0)
    {
        printf("Fehler beim SchlieRen!");
        return EXIT_FAILURE;
    }
    printf("Erfolgreich beendet!");
    return EXIT_SUCCESS;
    
}