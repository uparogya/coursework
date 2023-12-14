#include <stdio.h>
/*
 * bytes.c
 *
 * Explore how multi-byte values are stored in memory.
 */

// Declare a data type "byte" that is equivalent to
// "unsigned char". (Recall that chars in C are by
// definition single bytes.)
typedef unsigned char byte;

// Print a single byte in memory
void printByte(void *p);

// Print n bytes, starting at address p
void printBytes(void *p, int n);

int main()
{
    char c;
    printf("Enter the character: ");
    scanf("%c",&c);
    printByte(&c);

    printf("\n\nMulti Bytes: \n");
    char *string = "ABC123";
    printBytes(string, 6);

    printf("\n\nINT Bytes: \n");
    int integer = 0x11223344;
    printBytes(&integer, 4);

    printf("\n\nVariables Printer: \n");
    int eger = 1;
    int ernship = 2;
    char acter = 'A';
    char t = 'B';
    long itude = 12345;
    long er = 67890;

    printf("\nMemory Addresses: \nInt  1 = %p \nInt  2 = %p \nChar 1 = %p \nChar 2 = %p \nLong 1 = %p \nLong 2 = %p\n", &eger, &ernship, &acter, &t, &itude, &er);
    printf("\nPrinting Bytes: \n");
    printBytes(&er,8);
    printBytes(&itude,8);
    printBytes(&t,1);
    printBytes(&acter,1);
    printBytes(&ernship,4);
    printBytes(&eger,4);

    long last = 1029698543277;
    printBytes(&last,8);

    return 0;
}

// Recall that a void * is a "void pointer" - a pointer
// without saying what data type it points to - in other
// words, a pure memory address.
// To dereference a void pointer, you first need to cast
// it to another type of pointer, so that C knows how to
// interpret the binary data at the memory address.
void printByte(void *p)
{
    // Cast p to a byte pointer
    byte *new_p = (byte *) p;
    // Dereference the byte pointer and print as a
    // two-digit hex number
    printf("%02x\n", *new_p);
}

void printBytes(void *p, int n)
{
    //byte *new_p = (byte *) p;
    for(int i = 0; i < n; i++){
        printByte(p+i);
    }
}
