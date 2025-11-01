#ifndef MINMAXACCUMULATOR_H
#define MINMAXACCUMULATOR_H

#include "accumulator.h"

class MinMaxAccumulator : public Accumulator
{
	private:
		double least;
		double greatest;

	public:
		MinMaxAccumulator();

		double min();
		double max();
		void update(double y);
};

#endif
