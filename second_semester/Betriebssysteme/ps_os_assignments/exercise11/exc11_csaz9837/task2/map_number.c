#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define _GNU_SOURCE
#include <dlfcn.h>


int main(int argc, char *argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Usage: %s <number> <plugin1> <plugin2> ...\n", argv[0]);
        return EXIT_FAILURE;
    }
    int number = atoi(argv[1]);

    //the rest of the arguments are the plugins
    for (int i = 2; i < argc; i++) {
        //load the plugin
        void *handle = dlopen(argv[i], RTLD_LAZY);

        char *pluginName = argv[i];

        //remove the ./ from the pluginName
        if (strncmp(pluginName, "./", 2) == 0) {
            pluginName += 2;
        }

        //remove the .so from the pluginName
        pluginName[strlen(pluginName) - 3] = '\0';

        //get the function
        int (*function)(int) = dlsym(handle, pluginName);

        number = function(number);
        printf("%s: %d\n", argv[i], number);
        dlclose(handle);
    }
    return EXIT_SUCCESS;
}