#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

//bool isNumber(char* input){
// for (size_t i = 0; i < strlen(input); i++)
// {
//   if (input[i] < '0' || input[i] > '9')
//   {
//     return true;
//   }
// }
// return false;



int main(int argc, char* argv[]){
  char *p = NULL;  
  if (!(argv[1]) || argc > 2){
    return 2;
  } 
  if (!(strtol(argv[1],&p,10) || *p == '\0'))
  {
    return 3;
  }
  else if (strtol(argv[1], &p, 10) % 2){
    return 1;
  }
  else if (strtol(argv[1], &p,10) % 2 == 0){
    return 0;
  
  }
  
 return EXIT_SUCCESS; 
}
