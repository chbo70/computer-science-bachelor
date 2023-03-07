#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <time.h>
#include <unistd.h>

pthread_barrier_t barrier;
pthread_barrier_t barrierCalculate;
const int numberOfPlayers = 5;
int alivePlayer = 0;
int diceValues[5];
int status[5] = {0};

void gameLogic() {
    // Calculate winner
    int min = 6;

    for (int i = 0; i < numberOfPlayers; i++) {
        if (diceValues[i] < min && status[i] == 0) {
            min = diceValues[i];
        }
        if (status[i] == 0) {
            printf("Player %d rolled %d\n", i, diceValues[i]);
        }
    }

    for (int i = 0; i < numberOfPlayers; i++) {
        if (diceValues[i] == min && status[i] == 0) {
            status[i] = 1;
            alivePlayer++;
            printf("Player %d got eliminated\n", i);
        }
    }
    printf("-------------------------------------------------\n");
}

void *rollDiceFunction(void *args) {
    int index = *(int *) args;
    //int seed = time(NULL);
    while (1) {
        diceValues[index] = rand() % 6 + 1;
        if (pthread_barrier_wait(&barrierCalculate) == PTHREAD_BARRIER_SERIAL_THREAD) {
            gameLogic();
        }
        pthread_barrier_wait(&barrierCalculate);
        if (alivePlayer >= numberOfPlayers - 1) {
            free(args);
            return NULL;
        }
    }
}

int main() {
    srand(time(NULL));
    pthread_t playerThreads[numberOfPlayers];
    //pthread_barrier_init(&barrier, NULL, numberOfPlayers + 1);
    pthread_barrier_init(&barrierCalculate, NULL, numberOfPlayers);
    for (int i = 0; i < numberOfPlayers; ++i) {
        int *a = malloc(sizeof(int));
        *a = i;
        if (pthread_create(&playerThreads[i], NULL, &rollDiceFunction, (void *) a)) {
            perror("thread creation");
            return EXIT_FAILURE;
        }
    }
    int k = 0;
    for (int i = 0; i < numberOfPlayers; ++i) {
        pthread_join(playerThreads[i], NULL);
        if (status[i] == 0) {
            printf("Player %d won the game!\n", i);
            k = 1;
        }
    }
    if (k == 0) {
        printf("All Players are eliminated!\n");
    }
    pthread_barrier_destroy(&barrier);
    pthread_barrier_destroy(&barrierCalculate);
    return EXIT_SUCCESS;
}
