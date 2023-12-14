/*
 * project.c
 *
 * Project 1: split up the fields of a double value to
 * manipulate its value.
 *
 * [YOUR NAME HERE]
 */

#include <stdio.h>

// These functions will be implemented in a separate file
// (hence "extern") for Steps 1 and 4
extern long double_to_long_bits(double x);
extern double long_bits_to_double(long l);

// Functions to fill in for Step 2
long sign_of(long l);
long exponent_of(long l);
long fraction_of(long l);

// Function to fill in for Step 3
long assemble_long_bits(long sign, long exponent, long fraction);

// Functions to fill in for Step 5
double log_base_2(double x);
double negative_zero();
double infinity();
double halve(double x);

int main()
{
    // TAKING THE DOUBLE INPUT HERE
    double inputDouble;
    printf("Enter the number: ");
    scanf("%lf",&inputDouble);
    // ALL OTHER OPERATIONS
    long returnedLongInt = double_to_long_bits(inputDouble);
    printf("Long Int = %li\n",returnedLongInt);
    printf("------------DISASSEMBLE------------\n");
    printf("Exponent = %li\n",exponent_of(returnedLongInt));
    printf("Fraction = %li\n",fraction_of(returnedLongInt));
    printf("Sign = %li\n",sign_of(returnedLongInt));
    long assembledBits = assemble_long_bits(sign_of(returnedLongInt),exponent_of(returnedLongInt),fraction_of(returnedLongInt));
    printf("-------------REASSEMBLE-------------\n");
    printf("Assembled Bits = %li\n",assembledBits);
    printf("Double Result = %f\n",long_bits_to_double(assembledBits));
    printf("-------------OTHER OPS-------------\n");
    printf("Log Base 2 = %f\n",log_base_2(inputDouble));
    printf("Negative 0 = %f\n",negative_zero());
    printf("Infinity = %f\n",infinity());
    printf("Half of Input = %f\n",halve(inputDouble));
    return 0;
}

// -- Functions for Step 2 --
long sign_of(long l)
{
    l = l >> 63;
    return l & 0x1;
}

long exponent_of(long l)
{
    long bitmask = 0x7ff;
    l = l >> 52;
    return bitmask & l;
}

long fraction_of(long l)
{
    long bitmask = 0xfffffffffffff;
    return bitmask & l;
}

// -- Function for Step 3 --
long assemble_long_bits(long sign, long exponent, long fraction)
{
    long assembledBits = (sign<<63) | (exponent<<52) | (fraction);
    return assembledBits;
}

// -- Functions for Step 5 --
double log_base_2(double x)
{
    long convertedValue = double_to_long_bits(x);
    long logBase2 = exponent_of(convertedValue) - 1023;
    return logBase2;
}

double negative_zero()
{
    long assembledBits = assemble_long_bits(1,0,0);
    return long_bits_to_double(assembledBits);
}

double infinity()
{
    long assembledBits = assemble_long_bits(0,0x7ff,0);
    return long_bits_to_double(assembledBits);
}

double halve(double x)
{
    long numberToOperate = double_to_long_bits(x);
    long exponentMinus1 = exponent_of(numberToOperate) - 1;
    long assembledBits = assemble_long_bits(sign_of(numberToOperate),exponentMinus1,fraction_of(numberToOperate));
    return long_bits_to_double(assembledBits);
}
