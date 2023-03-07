#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/*
Jede lebende Zelle mit weniger als zwei lebenden Nachbarn stirbt -> <2
Jede lebende Zelle mit mehr als drei lebenden Nachbarn stirbt -> x>3
Jede lebende Zelle mit zwei oder drei Nachbarn überlebt -> 2 <= x <= 3
Jede tote Zelle mit drei lebenden Nachbarn lebt danach -> if cell == 0 && x == 3 then live else
death
*/


void displayField(int rows, int columns, int* field) {
	// int * simulation = randomGamefield(rows, columns, density);
	for(int i = 1; i < rows - 1; i++) {
		for(int j = 1; j < columns - 1; j++) {
			printf("%d ", *(field + i * columns + j));
		}
		printf("\n");
	}
	printf("\n");
}



int countNeighbours(int columns, int x, int y, int* field) {
	int count = 0;
	int element = y * columns + x;
	if(field[element] == 1) {
		count--;
	}
	for(int i = -1; i <= 1; i++) {
		for(int j = -1; j <= 1; j++) {
			int c = *(field + element + (i * columns) + j);

			if(c == 1) count++;
		}
	}
	return count;
}

double getRandomNumber() {
	return ((double)rand() / RAND_MAX); //*(max-min)+min
}

int* randomGamefield(int rows, int columns, double density) {
	int* field = malloc(rows * columns * sizeof(int));
	int i, j;

	for(i = 1; i < rows - 1; i++) {
		for(j = 1; j < columns - 1; j++) {
			if(getRandomNumber() <= density) {
				*(field + i * columns + j) = 1;
			} else {
				*(field + i * columns + j) = 0;
			}
		}
	}
	return field;
}

int* step(int rows, int columns, int* sourceField) {
	int* destinationField = malloc(rows * columns * sizeof(int));
	if(destinationField == NULL) {
		return NULL;
	}
	for(int y = 1; y < rows - 1; y++) {
		for(int x = 1; x < columns - 1; x++) {
			int living = countNeighbours(columns, x, y, sourceField);
			int cell = *(sourceField + y * columns + x);
			// if (cell == 1) {living--;}
			*(destinationField + y * columns + x) = cell;
			if(living < 2) {
				*(destinationField + y * columns + x) = 0;
			} else if((living == 2 || living == 3) && cell == 1) {
				*(destinationField + y * columns + x) = 1;
			} else if(living > 3 && cell == 1) {
				*(destinationField + y * columns + x) = 0;
			} else if(living == 3 && cell == 0) {
				*(destinationField + y * columns + x) = 1;
			}
		}
	}

	return destinationField;
}

void begin(int rows, int columns, double density, const int steps) {
	int* sourceField = randomGamefield(rows, columns, density);
	if(sourceField == NULL) {
		exit(0);
	}
	displayField(rows, columns, sourceField);

	for(int i = 0; i < steps; i++) {
		int* destinationField = step(rows, columns, sourceField);
		if(destinationField == NULL) {
			exit(0);
		}
		free(sourceField);
		sourceField = destinationField;
		displayField(rows, columns, sourceField);
	}
}

void printUsage(const char* programName) {
	printf("usage: %s <width> <height> <density> <steps>\n", programName);
}

int main(int argc, char* argv[]) {
	if(argc != 5) {
		printUsage(argv[0]);
		return EXIT_FAILURE;
	}

	int width = atoi(argv[1]);  // colums
	int height = atoi(argv[2]); // rows
	const double density = atof(argv[3]);
	const int steps = atoi(argv[4]);

	printf("width:   %4d\n", width);
	width += 2;
	printf("height:  %4d\n", height);
	height += 2;
	printf("density: %4.0f%%\n", density * 100);
	printf("steps:   %4d\n", steps);

	// Seeding the random number generator so we get a different starting field
	// every time.
	srand(time(NULL));
	begin(height, width, density, steps);

	return EXIT_SUCCESS;
}
