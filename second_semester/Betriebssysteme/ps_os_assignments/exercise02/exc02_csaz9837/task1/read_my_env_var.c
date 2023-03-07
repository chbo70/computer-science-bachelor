#include <stdio.h>
#include <stdlib.h>


int main()
{
    char* my_env_var = getenv("MY_ENV_VAR");

    if(my_env_var)
        printf("MY_ENV_VAR is set to %s\n", my_env_var );
    else
        printf("MY_ENV_VAR is not set\n");                

    return 0;
}
