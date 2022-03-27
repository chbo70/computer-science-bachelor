#include <stdlib.h>

int a;

long fun(long b, long c) { return b + c; }

int main(void) {
    short d;
    {
        char e;
    }
    return EXIT_SUCCESS;
}


/*

Die vier Gültigkeitsbereiche:
-Datei
-Funktion (vorwärtsdeklaration von funktionen)
-Block
-Funktionsprototyp

+----------+--------------------+
| Variable | Gültigkeitsbereich |
+==========+====================+
| a        |Datei               |
+----------+--------------------+
| b        |Funktionsprototyp   |
+----------+--------------------+
| c        |Funktionsprototyp   |
+----------+--------------------+
| d        |Block               |
+----------+--------------------+
| e        |Block               |
+----------+--------------------+

*/