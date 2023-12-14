#include <stdio.h>
#define PI 3.141592653589793

double calculateArea(double radius);

int main()
{
    double radius;
    printf("Enter the radius of circle: ");
    scanf("%lf",&radius);
    printf("\nArea of the circle = %lf\n",calculateArea(radius));
    return 0;
}

double calculateArea(double radius) {
    return PI*radius*radius;
}
