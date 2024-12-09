# STUDENT NAME: AROGYA AND DRAKE

'''
Closest pair problem

This returns the closest pair of numbers.

Usage:
    
    python closest_pair.py [size of list of numbers]

[Arogya, Drake]

[21ST AUGUST]
'''

import random
import sys

'''
return the distance between two parameters
'''
def distance(a,b):
    return abs(a-b)


'''
populate the array with listSize unique random numbers between 1 ... 5000
'''
def populate(listSize):
    a = []
    count = 0

    while count < listSize:
        number = random.randint(1,5000)
        if number in a:
            continue
        else:
            a.append(number)
            count += 1

    return a

'''
now determine the closest pair
using brute force algorithm
'''
def closest_pair(values):
    pt_a = values[0]
    pt_b = values[1]
    closest_distance = distance(values[0],values[1])

    for i, pt in enumerate(values):
        for j in range(i+1, len(values)):
            if distance(pt,values[j]) < closest_distance:
                pt_a = pt
                pt_b = values[j]
                closest_distance = distance(pt,values[j])

    return pt_a, pt_b

'''
This behaves just like the Java main() method
'''
if __name__ == '__main__':
    if len(sys.argv) != 2:
        print('Usage python Closest.py [number of points]')
        quit()
    else:
        numbers = populate(int(sys.argv[1]))

        closest = closest_pair(numbers)

        print('The closest numbers are', closest[0], 'and', closest[1])
