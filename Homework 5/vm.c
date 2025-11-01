/**
 * Demonstration C program illustrating how to perform file I/O for vm assignment.
 *
 * Input file contains logical addresses
 *
 * Backing Store represents the file being read from disk (the backing store.)
 *
 * We need to perform random input from the backing store using fseek() and fread()
 *
 * This program performs nothing of meaning, rather it is intended to illustrate the basics
 * of I/O for the vm assignment. Using this I/O, you will need to make the necessary adjustments
 * that implement the virtual memory manager.
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

// number of characters to read for each line from input file
#define BUFFER_SIZE         10

// number of bytes to read
#define CHUNK               256

FILE    *address_file;
FILE    *backing_store;

// how we store reads from input file
char    address[BUFFER_SIZE];

int     logical_address;

// the buffer containing reads from backing store
signed char     buffer[CHUNK];

// the value of the byte (signed char) in memory
// signed char     value;

// PAGE TABLE
int 	page_table[256];
signed char physical_memory[65536];

// TLB
#define TLB_LENGTH 16
typedef struct {
    int tlb_page_number;
    int tlb_frame_number;
} TLB_STRUCTURE;

TLB_STRUCTURE tlb[TLB_LENGTH];

int current_tlb_length = 0;

int fifo_replacement_index = 0;

// helper functions:
void insert_frame(int page_number, int frame_number);
int search_frame(int page_number);

int main(int argc, char *argv[])
{
// int i = 0;

    // perform basic error checking
    if (argc != 3) {
        fprintf(stderr,"Usage: ./vm [backing store] [input file]\n");
        return -1;
    }

    // open the file containing the backing store
    backing_store = fopen(argv[1], "rb");

    if (backing_store == NULL) {
        fprintf(stderr, "Error opening %s\n",argv[1]);
        return -1;
    }

    // open the file containing the logical addresses
    address_file = fopen(argv[2], "r");

    if (address_file == NULL) {
        fprintf(stderr, "Error opening %s\n",argv[2]);
        return -1;
    }

    for (int index = 0; index<256; index++){
	page_table[index] = -1;
    }


    int tlb_hits = 0;
    int temp_page_faults = 0;
    int addresses_translated = 0;
    int current_free_physical_index = 0;

    // read through the input file and output each logical address
    while ( fgets(address, BUFFER_SIZE, address_file) != NULL) {
        logical_address = atoi(address);

        // first seek to byte CHUNK in the backing store
        // SEEK_SET in fseek() seeks from the beginning of the file
//        if (fseek(backing_store, CHUNK * i, SEEK_SET) != 0) {
//            fprintf(stderr, "Error seeking in backing store\n");
//            return -1;
//        }

        // now read CHUNK bytes from the backing store to the buffer
//        if (fread(buffer, sizeof(signed char), CHUNK, backing_store) == 0) {
//            fprintf(stderr, "Error reading from backing store\n");
//            return -1;
//        }

        // arbitrarily retrieve the 50th byte from the buffer
//        value = buffer[50];

//	printf("logical address = %d \n",logical_address);

	int offset = logical_address & 0xff;
	int page_number = (logical_address >> 8) & 0xff;
//	printf("offset = %d \n", offset);
//	printf("page_number = %d \n", page_number);

	int physical_address = 0;

	if (search_frame(page_number) != -1){
		int frame_number = search_frame(page_number);
		physical_address = frame_number + offset;
//		printf("\n\nTBL_HIT %d\n\n", physical_address);
		tlb_hits++;

	}else{

//	}


	if (page_table[page_number] == -1){
		// printf("FAULT AT: %d\n",page_number);
		temp_page_faults++;
		// page_table[page_number] = page_number;

		if (fseek(backing_store, CHUNK * page_number, SEEK_SET) != 0) {
                	fprintf(stderr, "Error seeking in backing store\n");
                	return -1;
        	}

		if (fread(buffer, sizeof(signed char), CHUNK, backing_store) == 0) {
                	fprintf(stderr, "Error reading from backing store\n");
                	return -1;
        	}

		for(int index = 0; index < 256; index++){
			physical_memory[current_free_physical_index + index] = buffer[index];
		}

		page_table[page_number] = current_free_physical_index;
		current_free_physical_index += CHUNK;

	}

	physical_address = page_table[page_number] + offset;
	insert_frame(page_number, page_table[page_number]);

	}

//	int physical_address = page_table[page_number] + offset;
	printf("Virtual address: %d Physical address: %d Value: %d\n", logical_address, physical_address, physical_memory[physical_address]);

	//if (fseek(backing_store, CHUNK * page_number, SEEK_SET) != 0) {
	//	fprintf(stderr, "Error seeking in backing store\n");
        //        return -1;
        //}

        //if (fread(buffer, sizeof(signed char), CHUNK, backing_store) == 0) {
        //        fprintf(stderr, "Error reading from backing store\n");
        //        return -1;
        //}

	// CORRECT VALUES:
	//printf("%d\n", (buffer[offset]));

        // just so we seek to 10 different spots in the file
        // i = (i + 1) % 10;

	addresses_translated++;
    }


    fclose(address_file);
    fclose(backing_store);


    printf("Number of Translated Addresses = %d\n", addresses_translated);
    printf("Page Faults = %d\n", temp_page_faults);
    float fault_rate = (float)temp_page_faults/addresses_translated;
    printf("Page Fault Rate = %.3f\n", fault_rate);
    float tlb_hit_rate = (float)tlb_hits/addresses_translated;
    printf("TLB Hits = %d\n", tlb_hits);
    printf("TLB Hit Rate = %.3f\n", tlb_hit_rate);

    return 0;
}

int search_frame(int page_number) {
	for (int i = 0; i < TLB_LENGTH; i++) {
        	if (tlb[i].tlb_page_number == page_number) {
            		return tlb[i].tlb_frame_number;
        	}
    	}
    	return -1;
}

void insert_frame(int page_number, int frame_number) {
	//if (search_frame(page_number) == 0){
		if (current_tlb_length < TLB_LENGTH){
			tlb[current_tlb_length].tlb_page_number = page_number;
			tlb[current_tlb_length].tlb_frame_number = frame_number;
		} else {
			tlb[fifo_replacement_index].tlb_page_number = page_number;
                        tlb[fifo_replacement_index].tlb_frame_number = frame_number;
			fifo_replacement_index = (fifo_replacement_index + 1) % TLB_LENGTH;
		}
		current_tlb_length++;
	//}
}
