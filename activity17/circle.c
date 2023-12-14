#include <math.h> // for math functions

// C does not include a pi constant! 4 * atan(1) is one way to compute it.
const double PI = 4 * atan(1);

double circle_radius_to_area(double r)
{
    return PI*r*r;
}

double circle_radius_to_circumference(double r)
{
    return 2*PI*r;
}
