#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

int main(int argc, char** argv) {
    if(argc < 3) {
        printf("Usage: %s <number> <plugin1> <plugin2> ...\n", argv[0]);
        return 1;
    }
    int number = atoi(argv[1]);
    for(int i = 2; i < argc; i++) {
        void* handle = dlopen(argv[i], RTLD_LAZY);
        if(!handle) {
            printf("Cannot open %s: %s\n", argv[i], dlerror());
            return 1;
        }
        int (*plugin)(int) = dlsym(handle, "plugin");
        if(!plugin) {
            printf("Cannot find symbol 'plugin' in %s\n", argv[i]);
            return 1;
        }
        number = plugin(number);
        printf("%s: %d\n", argv[i], number);
        dlclose(handle);
    }
    return 0;
}