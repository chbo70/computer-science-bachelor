#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

pthread_barrier_t barrier;

struct player_data {
    int id;
    int roll;
    bool eliminated;
};
typedef struct player_data player_data;
player_data *player_data_arr[5];

bool game_over() {
    // game is over if only one player is left or all players are eliminated
    int count = 0;
    for (int i = 0; i < 5; i++) {
        if (!player_data_arr[i]->eliminated) {
            count++;
        }
    }
    return count == 1 || count == 0;
}

player_data *determine_winner() {
    // determine the winner
    for (int i = 0; i < 5; i++) {
        if (!player_data_arr[i]->eliminated) {
            return player_data_arr[i];
        }
    }
    return NULL;
}

int get_smallest_roll() {
    // determine the smallest roll
    int smallest = 6;
    for (int i = 0; i < 5; i++) {
        if (!player_data_arr[i]->eliminated && player_data_arr[i]->roll < smallest) {
            smallest = player_data_arr[i]->roll;
        }
    }
    return smallest;
}

void *player(void *arg) {
    player_data *data = ((player_data *) arg);
    // as we can't change the barrier thread count, players that are eliminated still continue to use the barrier.
    while (!game_over()) {
        if (!data->eliminated) {
            data->roll = rand() % 6 + 1;
            printf("Player %d rolled a %d\n", data->id, data->roll);
        }
        // wait for all players to roll
        if (pthread_barrier_wait(&barrier) == PTHREAD_BARRIER_SERIAL_THREAD) {

            // all players have rolled, determine who is eliminated

            // find the smallest roll
            int min = get_smallest_roll();

            // determine who is eliminated
            for (int i = 0; i < 5; i++) {
                if (player_data_arr[i]->roll == min && !player_data_arr[i]->eliminated) {
                    player_data_arr[i]->eliminated = true;
                    printf("Eliminating player %d\n", player_data_arr[i]->id);
                }
            }
            printf("---------------------\n");

            if (game_over()) {
                player_data *winner = determine_winner();
                if (winner != NULL) {
                    printf("Player %d has won the game!\n", winner->id);
                } else {
                    printf("All players were eliminated!\n");
                }
            }

            fflush(stdout);
        }
        // wait again so that the other players don't roll before results are printed
        // (serial thread player has to finish printing)
        pthread_barrier_wait(&barrier);
    }
    return NULL;
}


int main() {
    // init barrier
    if (pthread_barrier_init(&barrier, NULL, 5) != 0) {
        perror("pthread_barrier_init");
        return EXIT_FAILURE;
    }
    for (int i = 0; i < 5; i++) {
        player_data *data = malloc(sizeof(player_data));
        data->id = i;
        player_data_arr[i] = data;
    }

    pthread_t players[5];
    for (int i = 0; i < 5; i++) {
        pthread_create(&players[i], NULL, player, player_data_arr[i]);
    }

    for (int i = 0; i < 5; i++) {
        pthread_join(players[i], NULL);
    }

    pthread_barrier_destroy(&barrier);

    for (int i = 0; i < 5; i++) {
        free(player_data_arr[i]);
    }
    return EXIT_SUCCESS;
}