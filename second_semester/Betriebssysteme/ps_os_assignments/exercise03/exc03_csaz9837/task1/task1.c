#include <stdio.h>
#include <stdlib.h>
#include <aio.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>

double mc_pi(int64_t S) {
    int64_t in_count = 0;
    for(int64_t i = 0; i < S; ++i) {
        const double x = rand() / (double)RAND_MAX;
        const double y = rand() / (double)RAND_MAX;
        if(x*x + y*y <= 1.f) {
            in_count++;
        }
    }
    return 4 * in_count / (double)S;
}

int main(int argc, char* argv[]){
  //check if there are more than the two arguments
  if (argc > 3)
  {
    printf("To many passed arguments!\n");
    return EXIT_FAILURE;
  }
  
  //convert string to int
  int nTimes = atoi(argv[1]);
  int samples = atoi(argv[2]);

  for (int i = 0; i < nTimes; ++i)
  {
    pid_t child = fork();
    srand(getpid());
    if (child == 0) //child is always 0 to begin with
    {
      printf("Child %d PID = %d. mc_pi(%d) = %f. \n", i , getpid(), samples, mc_pi(samples));    
      exit(0);  
    }
  }

  for (int i = 0; i < nTimes; ++i)
  {
    wait(NULL);
  }
  printf("Done.");
  return EXIT_SUCCESS;
}
