#include "circle.h"

double cylinder_volume(double r, double h){
    double area = circle_radius_to_area(r);
    return area * h;
}
