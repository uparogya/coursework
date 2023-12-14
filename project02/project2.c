#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>

// Struct for an image, containing its dimensions and pixel data
// DO NOT CHANGE ANYTHING ABOUT THIS or the testers will break!
struct bitmap
{
	int width;
	int height;
	int *pixels;
};

const int DIB_HEADER_SIZE = 14;
const int BMP_HEADER_SIZE = 40;

// Make "byte" mean "unsigned char"
typedef unsigned char byte;

// Calculates the stride of a .bmp file.
// (The stride is how many bytes of memory a single row of
// the image requires.)
inline int bmp_file_stride(struct bitmap *bmp);

// Calculates the total size that a .bmp file for this bitmap
// would need (in bytes)
inline int bmp_file_size(struct bitmap *bmp);

// Opens the file with the given name and maps it into memory
// so that we can access its contents through pointers.
void *map_file_for_reading(char *filename);

// Opens (and creates if necessary) the file with the given name
// and maps it into memory so that we can access its contents
// through pointers.
void *map_file_for_writing(char *filename, int file_size);

// Takes the contents of a bitmap file (bmp_file) and reads
// its data, filling in the struct bitmap pointed to by bmp.
// Returns 0 if everything worked, -1 if the file data isn't
// valid.
int read_bitmap(void *bmp_file, struct bitmap *bmp);

// Converts between a packed pixel (0xRRGGBB) and its components.
void rgb_to_pixel(int *p, int r, int g, int b);
void pixel_to_rgb(int p, int *r, int *g, int *b);

// Step 4 functions
void bitmap_to_grayscale(struct bitmap *bmp);
void bitmap_posterize(struct bitmap *bmp);

// Step 5 functions
void bitmap_squash(struct bitmap *bmp);
void bitmap_mirror(struct bitmap *bmp);

void do_main_menu();

int main()
{
    // Write any testing code you wish ---



    /* Uncomment this if you want to run the menu-based application
     * (this won't do anything useful until you have completed at
     * least Steps 1 through 3). */
    do_main_menu();

	return 0;
}

int bmp_file_stride(struct bitmap *bmp)
{
    return (24 * bmp->width + 31) / 32 * 4;
}

int bmp_file_size(struct bitmap *bmp)
{
    int stride = bmp_file_stride(bmp);
    return DIB_HEADER_SIZE
        + BMP_HEADER_SIZE
        + stride * bmp->height;
}

void *map_file_for_reading(char *filename)
{
    // A) Use open() to open the file for reading.
    int fd = open(filename, O_RDONLY);
    //printf("fd = %d\n",fd);
    if(fd == -1){
        perror(NULL);
        return NULL;
    }
    // B) Use fstat() to determine the size of the file.
    struct stat statbuf;
    int status = fstat(fd, &statbuf);
    //printf("status = %d\n",status);
    if(status != 0){
        perror(NULL);
        return NULL;
    }
    // C) Call mmap() to map the file into memory.
    //printf("size = %ld\n",statbuf.st_size);
    void *returned_pointer = mmap(NULL, statbuf.st_size, PROT_READ, MAP_SHARED, fd, 0);
    // D) Close the file using close().
    close(fd);

    // E) Return the pointer returned from mmap().
    if(returned_pointer){
        return returned_pointer;
    }
    // Default implementation: just returns NULL.
    perror(NULL);
    return NULL;
}

void *map_file_for_writing(char *filename, int file_size)
{
    // A) Use open() to open the file for writing.
    int fd = open(filename, O_RDWR | O_CREAT, 0644);
    if (fd == -1){
        perror(NULL);
        return NULL;
    }

    // B) Use ftruncate() to set the size of the file.
    int set_filesize = ftruncate(fd, file_size);
    if (set_filesize == -1){
        perror(NULL);
        return NULL;
    }

    // C) Call mmap() to map the file into memory.
    void *returned_pointer = mmap(NULL, (long) file_size, PROT_WRITE, MAP_SHARED, fd, 0);

    // D) Close the file using close().
    close(fd);


    // E) Return the pointer returned from mmap().
    if(returned_pointer){
        return returned_pointer;
    }

    // Default implementation: just returns NULL.
    perror(NULL);
    return NULL;

}

int read_bitmap(void *bmp_file, struct bitmap *bmp)
{
    // Cast bmp_file to a byte * so we can access it
    // byte by byte.
    byte *file = (byte *) bmp_file;

    // Check the magic: it should start with "BM"
    if(file[0] != 'B' || file[1] != 'M'){
        perror("File is not a bitmap!");
        return(-1);
    }

    //GETTING THE REQUIRED HEIGHT & WIDTH
    bmp->width = *((int *)(file + 18));
    bmp->height = *((int *)(file + 22));

    //GETTING OTHER META DATA
    int pixel_data_offset = *((int *)(file + 10));

    short color_depth = *((short *)(file + 28));
    if(color_depth != 24){
        perror("Color Depth Not 24!");
        return -1;
    }

    int compression_method = *((int *)(file + 30));
    if(compression_method != 0){
        perror("Invalid Compression Method");
        return -1;
    }

    int *pixels = (int *) malloc(bmp->width * bmp->height * sizeof(int));
    int stride = (24 * bmp->width + 31) / 32 * 4;
    printf("width = %d\n",bmp->width);
    int index = 0;

    for (int y = 0; y < bmp->height; ++y){

        byte *row = (byte *) (pixel_data_offset + file + (stride*(bmp->height-(y + 1))));

        for (int x = 0; x < bmp->width; ++x){

            int b = *row;
            int g = *(row + 1);
            int r = *(row + 2);

            rgb_to_pixel(&pixels[index],r,g,b);
            index++;
            row = row + 3;

        }

    }
    bmp->pixels = pixels;

    // Default implementation: just returns 0.
    return 0;
}

void write_bitmap(void *bmp_file, struct bitmap *bmp)
{

    // Cast bmp_file to a byte * so we can access it
    // byte by byte.
    byte *file = (byte *) bmp_file;
    int stride = (24 * bmp->width + 31) / 32 * 4;

    // Fill in the rest :)
    file[0] = 'B'; file[1] = 'M'; //magic
    *((int *)(file + 2)) = bmp_file_size(bmp); //file size
    *((int *)(file + 10)) = 54; //pixel data offset
    *((int *)(file + 14)) = 40; //header size
    *((int *)(file + 18)) = bmp->width; // bitmap width
    *((int *)(file + 22)) = bmp->height; //bitmap height
    *((int *)(file + 26)) = 1; // color planes
    *((int *)(file + 28)) = 24; // color depth
    *((int *)(file + 30)) = 0; // compression method
    *((int *)(file + 34)) = bmp->height * stride; // image size
    *((int *)(file + 46)) = 0; // palette size
    int index = 0;

    for (int y = 0; y < bmp->height; ++y){

        byte *row = (byte *) (54 + file + (stride*((bmp->height)-(y + 1))));

        for (int x = 0; x < bmp->width; ++x){

            int pixel = bmp->pixels[index];
            int r; int g; int b;
            pixel_to_rgb(pixel,&r,&g,&b);
            *row = b;
            *(row + 1) = g;
            *(row + 2) = r;

            index++;
            row = row + 3;

        }

    }

}

void rgb_to_pixel(int *p, int r, int g, int b)
{
    // Pack r, g, and b into an int value and save
    // into what p points to
    *p = (r<<16)|(g<<8)|(b<<0);
}

void pixel_to_rgb(int p, int *r, int *g, int *b)
{
    // Separate the pixel p into its components
    // and save in the pointers
    *r = (p>>16)&(0x0000ff);
    *g = (p>>8)&(0x0000ff);
    *b = (p>>0)&(0x0000ff);

}

// Step 4 functions
void bitmap_to_grayscale(struct bitmap *bmp)
{
    int index = 0;

    for (int y = 0; y < bmp->height; ++y){

        for (int x = 0; x < bmp->width; ++x){

            int pixel = bmp->pixels[index];
            int r; int g; int b;
            pixel_to_rgb(pixel,&r,&g,&b);
            int average = (r+g+b)/3;
            rgb_to_pixel(&bmp->pixels[index],average,average,average);

            index++;

        }

    }
}

void bitmap_posterize(struct bitmap *bmp)
{
    int index = 0;

    for (int y = 0; y < bmp->height; ++y){

        for (int x = 0; x < bmp->width; ++x){

            int pixel = bmp->pixels[index];
            int r; int g; int b;
            pixel_to_rgb(pixel,&r,&g,&b);

            //FINDING NEW RGB
            if(r < 32){
                r = 0;
            }else if(r >= 32 && r <= 95){
                r = 64;
            }else if(r >= 96 && r <= 159){
                r = 128;
            }else if(r >= 160 && r <= 223){
                r = 192;
            }else{
                r = 255;
            }

            if(g < 32){
                g = 0;
            }else if(g >= 32 && g <= 95){
                g = 64;
            }else if(g >= 96 && g <= 159){
                g = 128;
            }else if(g >= 160 && g <= 223){
                g = 192;
            }else{
                g = 255;
            }

            if(b < 32){
                b = 0;
            }else if(b >= 32 && b <= 95){
                b = 64;
            }else if(b >= 96 && b <= 159){
                b = 128;
            }else if(b >= 160 && b <= 223){
                b = 192;
            }else{
                b = 255;
            }

            rgb_to_pixel(&bmp->pixels[index],r,g,b);

            index++;

        }

    }
}

// Step 5 functions
void bitmap_squash(struct bitmap *bmp)
{
    int new_width = bmp->width / 2;
    int *pixels = (int *) malloc(new_width * bmp->height * sizeof(int));

    int index = 0;
    int new_index = 0;

    for (int y = 0; y < bmp->height; ++y){

        for (int x = 0; x < new_width; ++x){

            int pixel_1 = bmp->pixels[index];
            int pixel_1_r; int pixel_1_g; int pixel_1_b;
            pixel_to_rgb(pixel_1,&pixel_1_r,&pixel_1_g,&pixel_1_b);

            int pixel_2 = bmp->pixels[index + 1];
            int pixel_2_r; int pixel_2_g; int pixel_2_b;
            pixel_to_rgb(pixel_2,&pixel_2_r,&pixel_2_g,&pixel_2_b);

            int new_pixel;
            rgb_to_pixel(&new_pixel, (pixel_1_r+pixel_2_r)/2, (pixel_1_g+pixel_2_g)/2, (pixel_1_b+pixel_2_b)/2 );
            pixels[new_index] = new_pixel;

            //printf("NEW PIXEL = %x",pixels[new_index]);
            index += 2;
            new_index++;

        }

    }

    free(bmp->pixels);
    bmp->width = new_width;
    bmp->pixels = pixels;
}

void bitmap_mirror(struct bitmap *bmp)
{
    int new_width = bmp->width * 2;
    int *pixels = (int *) malloc(new_width * bmp->height * sizeof(int));

    int index = 0;

    for (int y = 0; y < bmp->height; ++y){

        for (int x = 0; x < bmp->width; ++x){
            pixels[y * bmp->width + index] = bmp->pixels[index];
            pixels[(new_width * y) + (new_width - (x+1))] = bmp->pixels[index];
            index++;
        }

     }

    free(bmp->pixels);
    bmp->width = new_width;
    bmp->pixels = pixels;
}

// Extra credit functions
void bitmap_reflect(struct bitmap *bmp)
{
    int *pixels = (int *) malloc(bmp->width * bmp->height * sizeof(int));
    int index = 0;

    for (int y = 0; y < bmp->height; ++y){
        for (int x = bmp->width-1; x >= 0; --x){
            pixels[index] = bmp->pixels[y * bmp->width + x];
            index++;
        }
     }
    free(bmp->pixels);
    bmp->pixels = pixels;
}

void bitmap_skew(struct bitmap *bmp)
{
    //runs but doesn't give correct output for non square images
    int *pixels = (int *) malloc(bmp->width * bmp->height * sizeof(int));
    int index = 0;
    //printf("WIDTH = %d, HEIGHT = %d\n",bmp->width,bmp->height);
    int step = 0;
    for (int y = 0; y < bmp->height; ++y){
        //int step;
        if(y % bmp->width == 0){
            //printf("Y+1 = %d\n",y+1);
            //printf("STEP = %d\n", step);
            step = 0;
        }else{
            step++;
        }
        for (int x = step; x < bmp->width; ++x){
            pixels[index] = bmp->pixels[y * bmp->width + x];
            index++;
        }
        for (int x = 0; x < step; ++x){
            pixels[index] = bmp->pixels[y * bmp->width + x];
            index++;
        }
    }
    free(bmp->pixels);
    bmp->pixels = pixels;
}

void bitmap_rotate(struct bitmap *bmp)
{
    int *pixels = (int *) malloc(bmp->width * bmp->height * sizeof(int));
    int index = 0;

    for (int x = bmp->width-1; x >= 0; --x){
        for (int y = 0; y < bmp->height; ++y){
            pixels[index] = bmp->pixels[y * bmp->width + x];
            index++;
        }
     }
    free(bmp->pixels);
    int temp = bmp->height;
    bmp->height = bmp->width;
    bmp->width = temp;
    bmp->pixels = pixels;
}

void bitmap_shrink(struct bitmap *bmp)
{
    bitmap_squash(bmp);
    bitmap_rotate(bmp);
    bitmap_squash(bmp);
    bitmap_rotate(bmp);
    bitmap_rotate(bmp);
    bitmap_rotate(bmp);
}

void do_main_menu()
{
    char input_filename[100];

    void *file;
    while (1)
    {
        printf("Enter input image (a .bmp file): ");
        scanf("%s", input_filename);

        file = map_file_for_reading(input_filename);
        if (file != NULL)
            break;

        printf("Unable to read file \"%s\". Please try again.\n\n", input_filename);
    }

    struct bitmap bmp;
    if (read_bitmap(file, &bmp) == -1)
    {
        printf("Unable to read bitmap from \"%s\".\n", input_filename);
        return;
    }
    void *temp = file;
    munmap(file, bmp_file_size(&bmp));
    file = temp;

    char choice_str[20];
    char choice = '\0';

    while (choice != 'Q' && choice != 'q')
    {
        printf("Menu:\n");
        printf("\tG) Make grayscale\n");
        printf("\tP) Posterize\n");
        printf("\tU) Squash\n");
        printf("\tM) Mirror\n");
        printf("\tR) Reflect\n");
        printf("\tN) Rotate 90 Degrees\n");
        printf("\tH) Shrink\n");
        printf("\tK) Skew\n");
        printf("\tS) Save\n");
        printf("\tQ) Quit\n");
        printf("What would you like to do? ");
        scanf("%s", choice_str);
        choice = choice_str[0];

        if (choice == 'G' || choice == 'g')
        {
            printf("Grayscale selected\n\n");
            bitmap_to_grayscale(&bmp);
        }
        else if (choice == 'P' || choice == 'p')
        {
            printf("Posterize selected\n\n");
            bitmap_posterize(&bmp);
        }
        else if (choice == 'U' || choice == 'u')
        {
            printf("Squash selected\n\n");
            bitmap_squash(&bmp);
        }
        else if (choice == 'M' || choice == 'm')
        {
            printf("Mirror selected\n\n");
            bitmap_mirror(&bmp);
        }
        else if (choice == 'R' || choice == 'r')
        {
            printf("Reflect selected\n\n");
            bitmap_reflect(&bmp);
        }
        else if (choice == 'N' || choice == 'n')
        {
            printf("Rotate selected\n\n");
            bitmap_rotate(&bmp);
        }
        else if (choice == 'H' || choice == 'h')
        {
            printf("Shrink selected\n\n");
            bitmap_shrink(&bmp);
        }
        else if (choice == 'K' || choice == 'k')
        {
            printf("Skew selected\n\n");
            bitmap_skew(&bmp);
        }
        else if (choice == 'S' || choice == 's')
        {
            printf("Enter filename: ");
            char filename[100];
            scanf("%s", filename);
            printf("Saving to %s...\n", filename);
            void *output = map_file_for_writing(filename, bmp_file_size(&bmp));
            if (output)
            {
                write_bitmap(output, &bmp);
                munmap(output, bmp_file_size(&bmp));
                printf("Saved!\n\n");

            }
            else
            {
                printf("Unable to save to file.\n\n");
            }
        }
        else if (choice == 'Q' || choice == 'q')
        {
            printf("Bye!\n\n");
            return;
        }
        else
        {
            printf("Invalid option. Please try again!\n\n");
        }
    }
}
