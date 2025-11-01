#include "minmaxaccumulator.h"
#include <iostream>

int main()
{
	MinMaxAccumulator a;
	a.update(5);
	a.update(10);
	a.update(15);
	std::cout << "Min: " << a.min() << "\n";
	std::cout << "Max: " << a.max() << "\n";

	return 0;
}
