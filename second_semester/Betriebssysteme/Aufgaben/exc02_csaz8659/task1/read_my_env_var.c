#include <stdio.h>
#include <stdlib.h>

void print_env_var(const char *name) {
    char *val = getenv(name);
    if (!val) {
        printf("%s is not set\n", name);
    } else {
        printf("%s is set to '%s'\n", name, val);
    }
}

int main() {
    // setenv ("MY_ENV_VAR", "value", 1);
    print_env_var("MY_ENV_VAR");
    return 0;
}

// export MY_ENV_VAR="Test"
// echo $MY_ENV_VAR
// unset MY_ENV_VAR

// PATH env var specifies the directories to be searched to find a command. E.g. if "java" is run, the PATH env var specifies where to find the java runnable