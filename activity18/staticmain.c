#include <segment.h>
#include <stdio.h>

int main()
{
    double xm;
    double ym;
    segment_midpoint(5.0,8.0,10.0,13.0,&xm,&ym);

    printf("Mid point of x1 and x2 = %f\n",xm);
    printf("Mid point of y1 and y2 = %f\n",ym);

    return 0;
}
