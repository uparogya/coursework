#include "circle.h"
#include "cylinder.h"
#include <stdio.h>

//double circle_radius_to_area(double r);

int main(){
    printf("Area = %f\n",circle_radius_to_area(5));
    printf("Circumference = %f\n",circle_radius_to_circumference(5));
    printf("Volume = %f\n",cylinder_volume(5, 5));
}
