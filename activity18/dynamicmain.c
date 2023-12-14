#include <date.h>
#include <stdio.h>

int main()
{
    int month;
    printf("Enter month number: ");
    scanf("%d",&month);
    printf("Number of days in %d = %d\n", month, date_days_in_month(month));
    return 0;
}
