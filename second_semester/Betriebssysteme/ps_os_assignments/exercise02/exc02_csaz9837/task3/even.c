#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool isNumber(const char* input){
  for (size_t i = 0; i < strlen(input); i++)
  {
    if (input[i] < '0' || input[i] > '9')
    {
      return true;
    }
  }
  return false;
}


int main(int argc, char* argv[]){
  
  if (!(argv[1]) || argc > 2){
    return 2;
  } 
  int input = atoi(argv[1]);
  if ((isNumber(argv[1])))
  {
    return 3;
  }
  else if (input % 2){
    return 1;
  }
  else if (input % 2 == 0){
    return 0;
  
  }
  
 return EXIT_SUCCESS; 
}
