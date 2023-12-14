#include <stdio.h>

struct person {
    char given_name[100];
    char family_name[100];
    int age;
};

typedef struct person person_t;

void inputPerson(person_t *person);
void printPerson(person_t *person);

int main()
{
    person_t person;
    inputPerson(&person);
    printPerson(&person);
    return 0;
}

void inputPerson(person_t *person)
{
    printf("Enter your given name: ");
    scanf("%s",&(*person).given_name);
    printf("\n");

    printf("Enter your family name: ");
    scanf("%s",&(*person).family_name);
    printf("\n");

    printf("Enter your age: ");
    scanf("%d",&(*person).age);
    printf("\n");
    // Add code here to prompt the user for each field
    // in struct person, then scanf() them into the
    // person pointed to by the struct.
    //
    // DO NOT declare any variables in this function.
    // scanf() directly into person.


}

void printPerson(person_t *person)
{
    // Add code here to print out all fields of the person.
    // Use -> to access its fields.
    printf("Your name is %s %s. You are %d years old.\n",person->given_name,person->family_name,person->age);

}
