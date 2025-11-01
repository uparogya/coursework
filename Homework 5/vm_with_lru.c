#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define BUFFER_SIZE 10
#define CHUNK 256

FILE *address_file;
FILE *backing_store;

char address[BUFFER_SIZE];
int logical_address;
signed char buffer[CHUNK];


// PAGE TABLE
// Number of page frames:
#define PAGE_FRAMES 128

typedef struct {
    int ptable_page_number;
    int ptable_frame_number;
} PTABLE_STRUCTURE;

PTABLE_STRUCTURE page_table[PAGE_FRAMES];

int current_page_table_length = 0;

// PHYSICAL MEMORY
signed char physical_memory[256*PAGE_FRAMES];

int current_free_physical_index = 0;

// TLB
#define TLB_LENGTH 16

typedef struct {
    int tlb_page_number;
    int tlb_frame_number;
} TLB_STRUCTURE;

TLB_STRUCTURE tlb[TLB_LENGTH];

int current_tlb_length = 0;

int fifo_replacement_index = 0;

// HELPER FUNCTIONS
void insert_frame(int page_number, int frame_number); // insert frame number into TLB
int search_frame(int page_number); // search for page number | returns frame number
int search_frame_return_index(int page_number); // same as above | returns index
int search_in_page_table(int page_number); // search for page in page table | returns index
int insert_frame_to_page_table(int page_number); // insert frame and chunk of values into page_table and physical_memory | returns index of insertion

// FOR COMPARISON TO correct-values.txt
#define PRINT_VALUES_ONLY 0

int main(int argc, char *argv[])
{

    if (argc != 3) {
        fprintf(stderr,"Usage: ./vm [backing store] [input file]\n");
        return -1;
    }

    backing_store = fopen(argv[1], "rb");

    if (backing_store == NULL) {
        fprintf(stderr, "Error opening %s\n",argv[1]);
        return -1;
    }

    address_file = fopen(argv[2], "r");

    if (address_file == NULL) {
        fprintf(stderr, "Error opening %s\n",argv[2]);
        return -1;
    }

    for (int index = 0; index<PAGE_FRAMES; index++){
	page_table[index].ptable_page_number = -1;
	page_table[index].ptable_frame_number = -1;
    }


    int tlb_hits = 0;
    int temp_page_faults = 0;
    int addresses_translated = 0;

    while ( fgets(address, BUFFER_SIZE, address_file) != NULL) {
        logical_address = atoi(address);

	int offset = logical_address & 0xff;
	int page_number = (logical_address >> 8) & 0xff;

	int physical_address = 0;

	if (search_frame(page_number) != -1){
		int frame_number = search_frame(page_number);
		physical_address = frame_number + offset;
		tlb_hits++;

	}else{

		int index_in_page_table = search_in_page_table(page_number);
		if (index_in_page_table == -1){
			temp_page_faults++;
			index_in_page_table = insert_frame_to_page_table(page_number);
		}
		physical_address = page_table[index_in_page_table].ptable_frame_number + offset;
		insert_frame(page_number, page_table[index_in_page_table].ptable_frame_number);
	}

	if (PRINT_VALUES_ONLY == 0) {
		printf("Virtual address: %d Physical address: %d Value: %d\n", logical_address, physical_address, physical_memory[physical_address]);
	}else{
		printf("%d\n", physical_memory[physical_address]);
	}

	addresses_translated++;
    }


    fclose(address_file);
    fclose(backing_store);


    if (PRINT_VALUES_ONLY == 0){
	printf("Number of Translated Addresses = %d\n", addresses_translated);
    	printf("Page Faults = %d\n", temp_page_faults);
    	float fault_rate = (float)temp_page_faults/addresses_translated;
    	printf("Page Fault Rate = %.3f\n", fault_rate);
    	float tlb_hit_rate = (float)tlb_hits/addresses_translated;
    	printf("TLB Hits = %d\n", tlb_hits);
    	printf("TLB Hit Rate = %.3f\n", tlb_hit_rate);
    }

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

int search_frame_return_index(int page_number) {
	for (int i = 0; i < TLB_LENGTH; i++) {
                if (tlb[i].tlb_page_number == page_number) {
                        return i;
                }
        }
        return -1;
}

void insert_frame(int page_number, int frame_number) {
	if (current_tlb_length < TLB_LENGTH){
		tlb[current_tlb_length].tlb_page_number = page_number;
		tlb[current_tlb_length].tlb_frame_number = frame_number;
	} else {
		tlb[fifo_replacement_index].tlb_page_number = page_number;
                tlb[fifo_replacement_index].tlb_frame_number = frame_number;
		fifo_replacement_index = (fifo_replacement_index + 1) % TLB_LENGTH;
	}

	current_tlb_length++;
}

int search_in_page_table(int page_number) {
	for (int i=0; i < PAGE_FRAMES; i++){
		if (page_table[i].ptable_page_number == page_number) {
			return i;
		}
	}
	return -1;
}

int insert_frame_to_page_table(int page_number) {
	int index_in_page_table = -1;

	if (fseek(backing_store, CHUNK * page_number, SEEK_SET) != 0) {
        	fprintf(stderr, "Error seeking in backing store\n");
                return -1;
	}

	if (fread(buffer, sizeof(signed char), CHUNK, backing_store) == 0) {
        	fprintf(stderr, "Error reading from backing store\n");
                return -1;
	}

	int temp_current_tlb_assignment = -1;

	if (current_page_table_length < PAGE_FRAMES) {
		temp_current_tlb_assignment =  page_table[PAGE_FRAMES - 1].ptable_page_number;

		PTABLE_STRUCTURE temp_ptable[PAGE_FRAMES];
	        int temp_current_insertion_point = 1;
        	for (int i = 0; i < current_page_table_length; i++) {
                       	temp_ptable[temp_current_insertion_point] = page_table[i];
                       	temp_current_insertion_point++;
        	}
        	temp_ptable[0].ptable_page_number = page_number;
		temp_ptable[0].ptable_frame_number = current_free_physical_index;
        	for (int i = 0; i < PAGE_FRAMES; i++) {
                	page_table[i] = temp_ptable[i];
        	}

		index_in_page_table = 0;

	} else {
		temp_current_tlb_assignment = page_table[PAGE_FRAMES - 1].ptable_page_number;
		int current_temp_physical_memory_index = page_table[PAGE_FRAMES - 1].ptable_frame_number;

		PTABLE_STRUCTURE temp_ptable[PAGE_FRAMES];
                int temp_current_insertion_point = 1;
                for (int i = 0; i < PAGE_FRAMES-1; i++) {
                        temp_ptable[temp_current_insertion_point] = page_table[i];
                        temp_current_insertion_point++;
                }
                temp_ptable[0].ptable_page_number = page_number;
                temp_ptable[0].ptable_frame_number = current_temp_physical_memory_index;
                for (int i = 0; i < PAGE_FRAMES; i++) {
                        page_table[i] = temp_ptable[i];
                }

		index_in_page_table = 0;
		current_free_physical_index = current_temp_physical_memory_index;
	}

	for(int index = 0; index < CHUNK; index++){
        	physical_memory[current_free_physical_index + index] = buffer[index];
        }

	temp_current_tlb_assignment = search_frame_return_index(temp_current_tlb_assignment);
	if (temp_current_tlb_assignment != -1){
//		tlb[temp_current_tlb_assignment].tlb_page_number = page_number;
//		tlb[temp_current_tlb_assignment].tlb_frame_number = current_free_physical_index;
		tlb[temp_current_tlb_assignment].tlb_page_number = -99;
		tlb[temp_current_tlb_assignment].tlb_frame_number = -99;
	}
	current_free_physical_index += CHUNK;
	current_page_table_length++;
	return index_in_page_table;

}
