#include "minmaxaccumulator.h"

MinMaxAccumulator::MinMaxAccumulator() : Accumulator(),
	least(1e300),
	greatest(-1e300)
{ }

double MinMaxAccumulator::min()
{
	return least;
}

double MinMaxAccumulator::max()
{
	return greatest;
}

void MinMaxAccumulator::update(double y)
{
	Accumulator::update(y);

	if(y<least){
		least = y;
	}
	if(y>greatest){
		greatest = y;
	}
}


