/*
 * double_long.c
 *
 * Functions to access the bits of a double as a long
 * and vice-versa.
 *
 * They are in a separate file from project.c because we
 * will need to manipulate the assembly code for the
 * functions and want to do that separately from the rest
 * of the project code.
 *
 * [YOUR NAME HERE]
 */

// -- Function for Step 1 --
long double_to_long_bits(double x)
{
    // Placeholder body for the function - we will alter
    // the assembly code to do something different.
    // (This version converts the double value to a long,
    // e.g. 3.14159 becomes 3, which is *not* what we
    // want!)
    
    return x;
}

// -- Function for Step 4 --
double long_bits_to_double(long l)
{
    // Placeholder body - will be altered in assembly
    
    return l;
}
