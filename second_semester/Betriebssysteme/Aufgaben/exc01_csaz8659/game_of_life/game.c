#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>
#include <string.h>

void iterate_gol(bool *source, bool *dest, int width, int height);

void assign_random_states(bool *pBoolean, int size, double density);

bool get_cell(bool *arr, int x, int y, int i, int i1);

int alive_neighbors(bool *arr, int x, int y, int width, int i);

void set_cell(bool *arr, int x, int y, int width, int height, bool state);

void write_pgm(bool *arr, const int width, const int height, int idx);

void printUsage(const char *programName) {
    printf("usage: %s <width> <height> <density> <steps>\n", programName);
}

int main(int argc, char *argv[]) {
    if (argc != 5) {
        printUsage(argv[0]);
        return EXIT_FAILURE;
    }

    const int width = atoi(argv[1]);
    const int height = atoi(argv[2]);
    const double density = atof(argv[3]);
    const int steps = atoi(argv[4]);

    printf("width:   %4d\n", width);
    printf("height:  %4d\n", height);
    printf("density: %4.0f%%\n", density * 100);
    printf("steps:   %4d\n", steps);

    // Seeding the random number generator so we get a different starting field
    // every time.
    srand(time(NULL));

    int size = width * height;
    bool *source = malloc(size * sizeof(bool));
    bool *dest = malloc(size * sizeof(bool));
    // dest doesn't have to be initialized, it will be overwritten by iterate_gol
    assign_random_states(source, size, density);

    for (int i = 0; i < steps; ++i) {
        iterate_gol(source, dest, width, height);
        write_pgm(dest, width, height, i);
        memcpy(source, dest, size * sizeof(bool));
    }
    free(source);
    free(dest);
    return EXIT_SUCCESS;
}

void write_pgm(bool *arr, const int width, const int height, int idx) {
    FILE *pbm;

    char name[14];
    sprintf(name, "gol_%05d.pbm", idx);

    pbm = fopen(name, "wb");
    fprintf(pbm, "P1\n");
    fprintf(pbm, "%d %d\n", width, height);

    for (int y = 0; y < height; ++y) {
        for (int x = 0; x < width; ++x) {
            bool cell = get_cell(arr, x, y, width, height);
            fprintf(pbm, "%s ", cell ? "1" : "0");
        }
        fputs("\n", pbm);
    }
    fclose(pbm);
}

void iterate_gol(bool *source, bool *dest, int width, int height) {
    for (int x = 0; x < width; ++x) {
        for (int y = 0; y < height; ++y) {
            bool cell = get_cell(source, x, y, width, height);
            int n_count = alive_neighbors(source, x, y, width, height);

            if (cell && (n_count == 2 || n_count == 3)) {
                set_cell(dest, x, y, width, height, true);
            } else if (!cell && n_count == 3) {
                set_cell(dest, x, y, width, height, true);
            } else {
                set_cell(dest, x, y, width, height, false);
            }
        }
    }
}

int alive_neighbors(bool *arr, int x, int y, int width, int height) {
    int neighbors = 0;
    for (int dx = -1; dx <= 1; ++dx) {
        for (int dy = -1; dy <= 1; ++dy) {
            if (dx != 0 || dy != 0) // if not self
                neighbors += get_cell(arr, x + dx, y + dy, width, height) ? 1 : 0;
        }
    }
    return neighbors;
}

void set_cell(bool *arr, int x, int y, int w, int h, bool state) {
    if (x < 0 || x >= w)
        exit(-1);
    if (y < 0 || y >= h)
        exit(-1);
    arr[x + y * w] = state;
}

bool get_cell(bool *arr, int x, int y, int w, int h) {
    if (x < 0 || x >= w)
        return false;
    if (y < 0 || y >= h)
        return false;
    return arr[x + y * w];
}

void assign_random_states(bool *pBoolean, int size, double density) {
    for (int i = 0; i < size; ++i) {
        *(pBoolean + i) = rand() < RAND_MAX * density;
    }
}
