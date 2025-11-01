#include "accumulator.h"

Accumulator::Accumulator() :
	total(0.0),
	count(0)
{ }

void Accumulator::update(double x)
{
	total += x;
	count++;
}

double Accumulator::sum()
{
	return total;
}

double Accumulator::mean()
{
	return total/count;
}
