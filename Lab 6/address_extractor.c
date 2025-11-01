#include <stdlib.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
    
    int i;
    unsigned int number;
    const int MASK = 0xf; // in decimal this is 15 in binary this is 1111
    

    const int OFFSET_MASK = 0x3ff;
    const int PAGE_MASK = 0xfffc00;

    int result;

    // get the numbers we are passed on the command line
    for (i = 1; i < argc; i++) {
        number = atoi(argv[i]);

        // AND number with MASK
        result = number & OFFSET_MASK;

        /**
         *
         * an example:

         number = 295 = 100100111 in binary
         MASK = 15 =    000001111
         -------------------------
         num & mask     000000111
         */

        printf("#%d Number = %d",i,number);
        printf(" offset = %d", result);

        // shift the number 4 bit-positions to the right

	result = number & PAGE_MASK;
        result = result >> 10;

        // number >> 4 = 100100111 -> 000010010
        printf(" page number = %d\n",result);
    }

    return 0;
}
