/*
 * fallthrough.c
 *
 * A program demonstrating fallthrough in switch cases
 */

#include <stdio.h>

void f(const char *prefix, int number);

int main()
{
    f("CMPT", 150);
    f("CMPT", 251);
    f("CMPT", 385);
    f("CMPT", 440);
    
    return 0;

}


void f(const char *prefix, int number)
{
    const char *level;
    switch (number / 100 * 100)
    {
        case 0:
            level = "Pre-college";
//            break;
        case 100:
            level = "Freshman";
  //          break;
        case 200:
            level = "Sophomore";
    //        break;
        case 300:
            level = "Junior";
      //      break;
        case 400:
            level = "Senior";
        //    break;
        default:
            level = "Graduate";
    }

    printf("%s %d has class level %s\n", prefix, number, level);
}
