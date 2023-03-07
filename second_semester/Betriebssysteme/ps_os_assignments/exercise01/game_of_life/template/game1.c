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
//					   10		  10		               randomfield

void displayField(int rows, int columns, int* field){
	//int * simulation = randomGamefield(rows, columns, density);
	for(int i = 0; i < rows; i++) {
		for(int j = 0; j < columns; j++) {
			printf("%d ", *(field + i * columns + j)); //field[i][j]
		}
		printf("\n");
	}
}
/*
for (i = y-1; i <= y+1; i++)
	{
		for (j = x-1; j <= x+1; j++)
		{
			if ((i == y && j == x) || (i < 0 || j < 0) || (i >= rows || j >= columns))
			{
				continue; //ignores the cells outside the border
			}
			if (field[i * j] == 1) 
			{
				count++;
			}
		}
	}
*/

int countNeighbours(int rows, int columns, int x, int y, int *field) { // **field 
	int i, j, count = 0;

	for (i = y-1; i <= y+1; i++)
	{
		for (j = x-1; j <= x+1; j++)
		{
			if ((i == y && j == x) || (i < 0 || j < 0) || (i >= rows || j >= columns))
			{
				continue; //ignores the cells outside the border
			}
			if (field[i * j] == 1) //field[i][j]
			{
				count++;
			}
		}
	}
	return count;
}


/*
int count_live_neighbour_cell(int a[row][col], int r, int c){
    int i, j, count=0;

    for(i = r-1; i <= r+1; i++){
        for(j = c-1;j <= c+1;j++){
            if((i == r && j == c) || (i < 0 || j < 0) || (i >= row || j >= col)){
                continue;
            }
            if(a[i][j] == 1){
                count++;
            }
        }
    }
    return count;
}
*/

double getRandomNumber() {
	return ((double)rand() / RAND_MAX); //*(max-min)+min
}

int *randomGamefield(int rows, int columns, double density) { //int **randomGamefield
	int* field = (int*)malloc(rows * columns * sizeof(int));
	int i, j;


	for(i = 0; i < rows; ++i) {
		for(j = 0; j < columns; ++j) {
			if(getRandomNumber() <= density) {
				*(field + i * columns + j) = 1; //field[i][j] = 1
			} else {
				*(field + i * columns + j) = 0; //field[i][j] = 0
			}
		}
	}
	/*
	for(int i = 0; i < rows; i++) {
		for(int j = 0; j < columns; j++) {
			printf("%d ", *(field + i * columns + j));
		}
		printf("\n");
	}*/
	return field; //**field
}

void printUsage(const char* programName) {
	printf("usage: %s <width> <height> <density> <steps>\n", programName);
}


void begin(int rows, int columns, double density){
	int * simulation = randomGamefield(rows, columns, density);
	if (simulation == NULL) {
		exit(0);
		}
	displayField(rows,columns,simulation);
}

int main(int argc, char* argv[]) {
	if(argc != 5) {
		printUsage(argv[0]);
		return EXIT_FAILURE;
	}

	const int width = atoi(argv[1]);  // colums
	const int height = atoi(argv[2]); // rows
	const double density = atof(argv[3]);
	const int steps = atoi(argv[4]);
	// int *destination = malloc(height * width * sizeof(int));
	

	printf("width:   %4d\n", width);
	printf("height:  %4d\n", height);
	printf("density: %4.0f%%\n", density * 100);
	printf("steps:   %4d\n", steps);

	// Seeding the random number generator so we get a different starting field
	// every time.
	srand(time(NULL));

	//begin(height, width, density);
	int live_cell;
	int *source = randomGamefield(height, width, density); //int **source
	displayField(height, width, source);
	for (int i = 0; i < height; ++i)
	{
		for (int j = 0; j < width; ++j)
		{
			live_cell = countNeighbours(height, width, i, j, source);//source[i][j]
			
		}
		
	}
	printf("%d",live_cell);

	return EXIT_SUCCESS;
}
