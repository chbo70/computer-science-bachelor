#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>

void fun(char *msg, ...);

int main(){
    fun("innbruck", 1, 4, 7, 11, 0);
    return EXIT_SUCCESS;
}

void fun(char *msg, ...){
    va_list ptr;
    int num;
    va_start(ptr,msg);
    num =va_arg(ptr, int);
    num = va_arg(ptr, int);
    printf("%d", num);
}