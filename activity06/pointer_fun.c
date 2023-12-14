/*
 * pointer_fun.c
 *
 * A program with some functions to fill in that use pointers =)
 */

#include <stdio.h>

void swap(int *i, int *j);
void inputPoint(char *prompt, int *x, int *y);
void decompose_rgb(int rgb, int *r, int *g, int *b);
void compose_rgb(int *rgb, int r, int g, int b);

int main()
{
    // Uncomment this part once you're done with swap()
    
    int m = 99, n = 150;
    printf("Initially m=%d, n=%d\n", m, n);
    // Write a line of code to call swap() to swap the values of m and n
    swap(&m,&n);
    printf("After swap() m=%d, n=%d\n\n\n", m, n);
    
    // If you've done this correctly, the above should print the following:
    //     After swap() m=150, n=99
    // */

    // Uncomment this part once you're done with inputPoint()
    // (You can comment it out again afterwards if you like)
    
    int x, y;
    inputPoint("Enter a point like \"x, y\": ", &x, &y);
    printf("You entered the point (%d, %d)\n\n\n", x, y);
    
    // If you've done this correctly, the above should print the point that
    // was entered.
    // */

    // Uncomment this part once you're done with decompose_rgb()
    
    int rgb;
    printf("Enter a color in hex (like ff7040): ");
    scanf("%x", &rgb);
    printf("Starting with the color %06x:\n", rgb);
    int r, g, b;
    decompose_rgb(rgb, &r, &g, &b);
    printf("The components are red=%02x, green=%02x, blue=%02x\n\n\n", r, g, b);

    // If you've done this correctly, the above should print out the red,
    // green, and blue parts of the color you entered (e.g. if you
    // entered ff7040, it should report ff for red, 70 for green, and
    // 40 for blue).
    // */

    // Uncomment this part once you're done with compose_rgb()
    
    compose_rgb(&rgb, g, b, r);
    printf("After rotating the components we get %06x\n\n\n", rgb);

    // If you've done this correctly, the above should print out the color
    // you entered previously but with red, green, blue switched around.
    // (E.g. if you entered ff7040, it should print out 7040ff).
    // */

    return 0;
}

// Swap the values that i and j point to
void swap(int *i, int *j)
{
    int temp = *i;
    *i = *j;
    *j = temp;
}

// Print the prompt, then read input in the form "x, y" and put in the x & y variables.
void inputPoint(char *prompt, int *x, int *y)
{
    printf("%s\n", prompt);
    scanf("%d, %d", x, y);
    // Write a printf() to print the prompt

    // Write a scanf() here
    // (Use "%d, %d" for the format string)

}

// Decomposes an RGB color into r, g, and b components and places them in r, g, and b
// An RGB color is (in binary)
//     RRRR RRRR GGGG GGGG BBBB BBBB
// (Eight bits each for R, G, and B)
// You will want to use >> and & to separate the pieces.
// (0xFF is 8 ones in binary)
void decompose_rgb(int rgb, int *r, int *g, int *b)
{
    *r = (rgb>>16)&(0xFF);
    *g = (rgb>>8)&(0xFF);
    *b = rgb&(0xFF);
}

// Do the reverse: take r, g, and b components and assemble them into an RGB color
// You will need to use << to place the pieces in the right bits.
void compose_rgb(int *rgb, int r, int g, int b)
{
    *rgb = (r<<16)|(g<<8)|(b<<0);
}
