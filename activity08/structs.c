#include <stdio.h>
#include <math.h> // for math functions like sqrt()

struct point
{
    double x;
    double y;
};

void printPoint(struct point p);
struct point inputPoint(const char *prompt);
double distanceFromOrigin(struct point p);

int main()
{
    struct point p;

    p.x = 1.8;
    p.y = -4.7;

    // Delete the comment below to do #4
    
    p = inputPoint("Type in a point in the form (x, y): ");
    // */

    printf("The point is ");
    printPoint(p);
    printf("\n");

    printf("The distance from origin is: %lf\n",distanceFromOrigin(p));

    return 0;
}

void printPoint(struct point p)
{
    // Add code here (print the point in the form "(x, y)")
    printf("(%lf, %lf)\n",p.x,p.y);
}

struct point inputPoint(const char *prompt)
{
    struct point p;
    printf("%s\n",prompt);
    scanf("(%lf, %lf)",&p.x,&p.y);
    // Add code here (print the prompt, then have the user
    // have the user write a point in the form "(x, y)")
    

    return p;
}

double distanceFromOrigin(struct point p)
{
    return ( sqrt( pow(p.x,2) + pow(p.y,2)  ) );
}
