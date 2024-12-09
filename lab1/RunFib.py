'''
Created on Jul 17, 2016

@author: Greg Gagne

This simple script tests the fibonacci sequence implementation.
'''

import  Fibonacci
    
import sys

'''
This behaves just like the Java main() method
'''
if __name__ == '__main__':
    if (len(sys.argv) != 2):
        print('Usage python RunFib.py [length of sequence]')
        quit()
    else:
        print(Fibonacci.fibonacci(int(sys.argv[1])))
