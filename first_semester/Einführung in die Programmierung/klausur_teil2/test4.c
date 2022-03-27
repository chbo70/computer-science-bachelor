#include <stdlib.h>
#include <stdio.h>

int main(){
    char *str[] = {"AAAAA", "BBBBB", "CCCCC", "DDDDD"};
    char **sptr[] = {str +3, str +2, str +1, str};
    char ***pp;

    pp = sptr; //str + 3
    ++pp; //str + 2
    printf("%s", **++pp +2); //str + 1 = "BBBBB" -> + 2 = "BBB"
    return EXIT_SUCCESS;
}