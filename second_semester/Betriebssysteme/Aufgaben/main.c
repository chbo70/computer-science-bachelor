#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <errno.h>
#include <time.h>
#include <stdatomic.h>
#include <signal.h>
#include <unistd.h>
#include <stdbool.h>

#define ERROR_CATCH(x) if(x != 0) { perror(#x "failed"); exit(EXIT_FAILURE);}

volatile sig_atomic_t signal_flag = 0;
void actionHandler(int sig){
    if(sig == SIGINT){
        write(STDOUT_FILENO, "SIGINT was received\n", 20);
        signal_flag++;
    }
}

//this following code is from the correction from exercise 3.3
int arguments_to_int(char *str) {
    char *endptr;
    int s = (int) strtol(str, &endptr, 0);
    if (errno != 0 || endptr == str || *endptr != '\0') {
        perror("parsing argument failed");
        exit(EXIT_FAILURE);
    }
    return s;
}

typedef struct argument1_arguments{
    int id;
    bool special_functionality;
}argument1_arguments;

pthread_mutex_t mutex;

void *thread_function(void *arg){
    argument1_arguments *arguments = arg;

    while(1){


        if(!arguments->special_functionality){
            //THREAD_FUNKTIONALITÄT
            
        }else if(arguments->special_functionality){
            
        }
    }
    pthread_exit(NULL);
}   

int main(int argc, char *argv[]){

    struct sigaction sa;
    sa.sa_handler = actionHandler;
    sigaction(SIGINT, &sa, NULL);

    int amount_arguments = 1000000;
    if(argc != amount_arguments){
        perror("Wrong amount of given arguments");
        exit(EXIT_FAILURE);
    }


    bool special_functionality = arguments_to_int(argv[1]);

    int thread_argument1_amount = arguments_to_int(argv[10]); //Error gets thrown in function

    ERROR_CATCH(pthread_mutex_init(&mutex, NULL)); //Initialise mutex

    pthread_t argument1_threads[thread_argument1_amount];

    argument1_arguments argument1_arguments[thread_argument1_amount];


    for(int i = 0; i < thread_argument1_amount; ++i){
        argument1_arguments[i].id = i;
        argument1_arguments[i].special_functionality = special_functionality;
        ERROR_CATCH(pthread_create(&argument1_threads[i], NULL, &thread_function, &argument1_arguments[i]));
    }
    

    for(int i = 0; i < thread_argument1_amount; ++i){
        ERROR_CATCH(pthread_join(argument1_threads[i], NULL));
    }

    pthread_mutex_destroy(&mutex);

    return EXIT_SUCCESS;
}