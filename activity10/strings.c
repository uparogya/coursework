#include <stdio.h>
#include <stdlib.h>

// Return the length of the string by counting the number of
// characters (bytes) *before* the NUL terminator. E.g.
// length("ABC") should return 3, not 4.
unsigned int length(const char *string);

// Copy all characters (bytes) of a string from one memory
// location to another, stopping after copying the NUL
// terminator.
void copy(const char *from, char *to);

// Returns a new string consisting of the concatenation of first and second
// (for example, concatenate("ABC", "123") returns a new string "ABC123").
// This function uses malloc() to allocate memory for the new string, so
// the caller must free() it when done using it.
char *concatenate(const char *first, const char *second);

int main(int argc, char *argv[])
{
    if(argc != 3){
        printf("Two Argument Expected \n");
        return -1;
    }

    printf("Length of the 1st argument = %d\n",length(argv[1]));
    printf("Length of the 2nd argument = %d\n",length(argv[2]));

    printf("---------------\n");

    char *conc_string = concatenate(argv[1],argv[2]);
    printf("Concat Version = %s\n",conc_string);
    printf("Length of new string = %d\n",length(conc_string));
    free(conc_string);

    printf("---------------\n");
    // For testing copy() (delete line below when ready to test)
    
    // Note: in C, if you put multiple string literals ("...") consecutively
    // in a program, the compiler joins them into a single string. (This is
    // helpful because C doesn't have a string concatenation operator!)
    char source[500] =
        "An examination of a computer system from the programmer's perspective. "
        "Examines how your programs interact with the compiler, the assembler, "
        "the operating system, and hardware, enabling students to write "
        "software that is efficient, modular, and versatile. Introduces the C "
        "programming language, the Linux operating system, and assembly "
        "programming.";

    char dest[500];

    copy(source, dest);
    printf("CMPT 251 catalog description:\n%s\n", dest);
    // End of copy() testing code
    // */
    //printf("ARGUMENTS = %d\n",argc);

    //printf("CONCAT WORD = %s\n",concatenate("AB","CD"));

    return 0;
}

unsigned int length(const char *string)
{
    const char *address = string;
    int count = 0;
    while(1){
        if(*address == '\0'){
            break;
        }
        address++;
        count++;
    }
    return count;
}

void copy(const char *from, char *to)
{
    int len = length(from);
    for (int i = 0; i <= len; i++){
        to[i] = from[i];
    }

}

char *concatenate(const char *first, const char *second)
{
    char *new_string = (char *) malloc((length(first) + length(second)) * sizeof(char));
    //char new_string[length(first) + length(second)];
    copy(first, new_string);
    for(int i = length(first); i <= length(first) + length(second); i++){
        new_string[i] = second[i-(length(first))];
    }
    return new_string;
}
