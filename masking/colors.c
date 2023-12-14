#include <stdio.h>

int main()
{
    // EXTRACTING THE COLORS
    int color = 0xfe87c7;
    // r = 0xfe, g = 0x87, b = 0xc7
    // extracting blue
    // in binary: 0b0000 0000 RRRR RRRR GGGG GGGG BBBB BBBB
    //           &0b0000 0000 0000 0000 0000 0000 1111 1111
    //            -----------------------------------------
    //            0b0000 0000 0000 0000 0000 0000 1111 1111
    int b = color & 0x0000ff;
    int g = (color & 0x00ff00) >> 8;
    int r = (color & 0xff0000) >> 16;

    printf("c = 0x%x\n",color);
    printf("r = 0x%x\n",r);
    printf("g = 0x%x\n",g);
    printf("b = 0x%x\n",b);

    int rgb = (r << 16) | (g << 8) | (b);
    printf("rgb = 0x%x\n",rgb);
    return 0;
}
