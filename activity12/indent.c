#include <stdio.h>
#include <stdlib.h>

int main() {
    char *text = NULL;
    size_t text_size = 0;
    ssize_t return_value = 0;

    while (return_value != -1){
        return_value = getline(&text, &text_size, stdin);
        if(return_value == -1){
            break;
        }
        printf("    %s",text);
    }

    free(text);
    return 0;
}
