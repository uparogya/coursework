#ifndef ACCUMULATOR_H
#define ACCUMULATOR_H

#include <string>

class Accumulator
{
	private:
    		double total;
    		int count;

	public:
		Accumulator();

		void update(double x);
		double sum();
		double mean();
};

#endif
