#include <stdio.h>
#include <stdlib.h>

int main(){
    void *vp;
    char ch = 72, *cp = "EIDP";
    int j =66;
    vp = &ch;
    printf("%c", *(char *)vp);
    vp = &j;
    printf("%c", *(int *)vp);
    vp = cp;
    printf("%s", (char *)vp +2);
}