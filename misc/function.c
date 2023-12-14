// a is a *parameter,* a variable that will accept an input value
long f(long a, int b);

int main()
{
    // 1 is an *argument,* a value being passed into a parameter
    long r = f(1000000000, 99);
    
    return 0;
}

long f(long a, int b)
{
    return a+b;
}
