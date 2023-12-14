/*
 * switch.c
 *
 * A demonstration of C's switch statement
 */

#include <stdio.h>

const char *day_name(int day);
const char *month_name(int month);

int main()
{
    int input_day;
    int input_month;
    printf("Enter Your Birth Month: ");
    scanf("%d",&input_month);
    printf("Enter Your Birth Day: ");
    scanf("%d",&input_day);
    printf("Your were born in %s of %s\n",day_name(input_day),month_name(input_month));
    return 0;
}

const char *day_name(int day)
{
    switch (day)
    {
        case 0:
            return "Monday";
            break;
        case 1:
            return "Tuesday";
            break;
        case 2:
            return "Wednesday";
            break;
        case 3:
            return "Thursday";
            break;
        case 4:
            return "Friday";
            break;
        case 5:
            return "Saturday";
            break;
        case 6:
            return "Sunday";
            break;
        default:
            return "Invalid day number!";
    }
}

const char *month_name(int month)
{
    switch (month)
    {
        case 1:
            return "January";
            break;
        case 2:
            return "February";
            break;
        case 3:
            return "March";
            break;
        case 4:
            return "April";
            break;
        case 5:
            return "May";
            break;
        case 6:
            return "June";
            break;
        case 7:
            return "July";
            break;
        case 8:
            return "August";
            break;
        case 9:
            return "September";
            break;
        case 10:
            return "October";
            break;
        case 11:
            return "November";
            break;
        case 12:
            return "December";
            break;
        default:
            return "Invalid Month";
            break;
    }
}
