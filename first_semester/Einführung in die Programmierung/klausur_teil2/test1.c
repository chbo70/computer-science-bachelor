#include <stdio.h>
#include <stdlib.h>
#define P3 x, y ,z

typedef struct point {
    int P3;
} point_t;

point_t init_point(){
    point_t p;
    for (int i = 0; i < 3; i++) {
        *((int *)&p + i) = 3 * i;
    }
    return p;
}

int calc(point_t p0, point_t p1) {return p0.x + p1.y;}

int main (){
    point_t p = {1,2,3};
    printf("%d\n", calc(p, init_point()));
    return EXIT_SUCCESS;
}