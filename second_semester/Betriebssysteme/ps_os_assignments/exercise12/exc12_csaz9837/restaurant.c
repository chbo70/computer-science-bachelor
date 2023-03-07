#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <stdbool.h>

#include "myqueue.h"

//available size of the counter
#define COUNTER_SIZE 1

pthread_mutex_t mutexQueue = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutexCounter = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
myqueue orders;

struct order_t {
    int id;                     //order id
    int *counter;               //counter id
    int *pickUpTime;            //pick up time of the orders
    int *useCondition;          //use condition of the orders based on the notification
    bool *is_done;              //flag to indicate if the order is done
    pthread_cond_t *condition;  //condition to wait for the order to be done
    myqueue *orderQueue;        //queue to store the orders
};

void *guest(void *args) {
    //parameter is the guest strct
    struct order_t *order = (struct order_t *) args;
    printf("Guest %d has made meal order %d\n", order->id, order->id);
    pthread_mutex_lock(&mutexQueue);
    myqueue_push(order->orderQueue, order->id);
    pthread_mutex_unlock(&mutexQueue);

    while (1) {
        usleep(100000);
        pthread_mutex_lock(&mutexCounter);
        if (*(order->useCondition) == 1) {
            while (order->counter[0] == -1) {
                pthread_cond_wait(order->condition, &mutexCounter);
            }
        }
        //increase wait time for the guest for order id
        order->pickUpTime[order->id] += 200;
        pthread_mutex_unlock(&mutexCounter);

        usleep(100000);

        //for (int i = 0; i < COUNTER_SIZE; ++i) {
        pthread_mutex_lock(&mutexCounter);
        if (order->counter[0] == order->id) {
            printf("Guest %d has picked up meal order %d after %d ms\n", order->id, order->id,
                   order->pickUpTime[order->id]);
            order->counter[0] = -1;
            pthread_mutex_unlock(&mutexCounter);

            free(order);
            pthread_mutex_unlock(&mutexCounter);
            return NULL;
        }
        pthread_mutex_unlock(&mutexCounter);
        //}
    }
}
void *cook(void *args) {
    //parameter is the cook strct
    struct order_t *order = (struct order_t *) args;

    while (1) {
        if (*order->is_done == true) {
            free(order);
            return NULL;
        }

        //lock the queue
        pthread_mutex_lock(&mutexQueue);

        if (!myqueue_is_empty(order->orderQueue)) {
            int idOfMeal = myqueue_pop(order->orderQueue);
            pthread_mutex_unlock(&mutexQueue);


            printf("Cook %d is preparing meal order %d\n", order->id, idOfMeal);
            int randomNumber = (rand() % 400) + 100;
            bool isFoodOnCounter = false;

            // continuously check if there is place on the counter
            usleep(randomNumber * 1000);
            while (1) {
                // Look through the counter segment.

                //for (int i = 0; i < COUNTER_SIZE; i++) {

                // Lock the counter and look at the current segment.
                pthread_mutex_lock(&mutexCounter);
                if (order->counter[0] == -1) {
                    // Print that the cool placed the food on the counter and set the current counter segment to the meal index, as well as increase the wait time.
                    printf("Cook %d has finished preparing meal order %d\n", order->id, idOfMeal);
                    order->counter[0] = idOfMeal;
                    order->pickUpTime[idOfMeal] += randomNumber;
                    pthread_mutex_unlock(&mutexCounter);

                    if (*order->useCondition == 1) {
                        //unlocks all the guests waiting for the counter if the condition is true
                        pthread_cond_broadcast(order->condition);
                    }

                    printf("Cook %d has placed order %d on counter\n", order->id, idOfMeal);
                    isFoodOnCounter = true;
                    //break;
                }
                pthread_mutex_unlock(&mutexCounter);
                //}
                // If the food was places on the counter, then break out of the loop.
                if (isFoodOnCounter) {
                    break;
                }
            }
        } else {
            pthread_mutex_unlock(&mutexQueue);
        }
    }
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        fprintf(stderr, "Usage: <enable notification> <number of guests> <number of cooks>\n");
        return EXIT_FAILURE;
    }

    int enable_notification = (int) strtol(argv[1], NULL, 10);
    int num_guests = (int) strtol(argv[2], NULL, 10);
    int num_cooks = (int) strtol(argv[3], NULL, 10);

    //check whether the number of guests is greater than 0
    if (num_guests <= 0) {
        fprintf(stderr, "Number of guests must be greater than 0\n");
        return EXIT_FAILURE;
    }
    //check whether the number of cooks is greater than 0
    if (num_cooks <= 0) {
        fprintf(stderr, "Number of cooks must be greater than 0\n");
        return EXIT_FAILURE;
    }
    //check whether enable notification is 0 or 1
    if (enable_notification != 0 && enable_notification != 1) {
        fprintf(stderr, "Enable notification must be 0 or 1\n");
        return EXIT_FAILURE;
    }
    //declaring all the variables
    int counter[COUNTER_SIZE];
    int pickUpTime[num_guests];
    bool exit = false;
    pthread_t cooks[num_cooks];
    pthread_t guests[num_guests];


    //for (int i = 0; i < COUNTER_SIZE; i++) {
    counter[0] = -1;
    //}

    srand(time(NULL));
    myqueue_init(&orders);

    //creating all the cook threads and also initializing the order information
    for (int i = 0; i < num_cooks; i++) {
        struct order_t *orderContent = malloc(sizeof(struct order_t));
        orderContent->id = i;
        orderContent->counter = counter;
        orderContent->pickUpTime = pickUpTime;
        orderContent->useCondition = &enable_notification;
        orderContent->is_done = &exit;
        orderContent->condition = &cond;
        orderContent->orderQueue = &orders;
        //thread creation of the cooks
        if (pthread_create(&cooks[i], NULL, cook, (void *) orderContent) != 0) {
            fprintf(stderr, "Error creating thread\n");
            return EXIT_FAILURE;
        }
    }

    //creating all the guest threads and also initializing the order information
    for (int i = 0; i < num_guests; i++) {
        pickUpTime[i] = 0;
        //contents for the thread
        struct order_t *orderContent = malloc(sizeof(struct order_t));
        orderContent->id = i;
        orderContent->useCondition = &enable_notification;
        orderContent->counter = counter;
        orderContent->pickUpTime = pickUpTime;
        orderContent->is_done = &exit;
        orderContent->condition = &cond;
        orderContent->orderQueue = &orders;
        //thread creation of the guest
        if (pthread_create(&guests[i], NULL, guest, (void *) orderContent) != 0) {
            fprintf(stderr, "Error creating thread\n");
            return EXIT_FAILURE;
        }
    }

    for (int i = 0; i < num_guests; i++) {
        if (pthread_join(guests[i], NULL) != 0) {
            fprintf(stderr, "Error joining guest thread\n");
            return EXIT_FAILURE;
        }
    }

    //setting the exit flag to true
    exit = true;

    for (int i = 0; i < num_cooks; i++) {
        if (pthread_join(cooks[i], NULL) != 0) {
            fprintf(stderr, "Error joining thread\n");
            return EXIT_FAILURE;
        }
    }

    //compute the average wait time in ms
    int sum = 0;
    for (int i = 0; i < num_guests; i++) {
        sum += pickUpTime[i];
    }
    int average = sum / num_guests;
    printf("All guests have been served with an average wait time of %d ms\n", average);

    //cleaning up
    pthread_mutex_destroy(&mutexQueue);
    pthread_mutex_destroy(&mutexCounter);
    pthread_cond_destroy(&cond);
    return EXIT_SUCCESS;
}
