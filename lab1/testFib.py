'''
Created on Jul 17, 2016
@author: greg

Modified on Aug 21, 2022
@auther: Jingsai
'''
import pytest
import Fibonacci

def testFib1():
    # sequence of size 0
    sequence = []
    # assert actual == expected
    assert Fibonacci.fibonacci(0) == sequence

    # sequence of size 1
    sequence = [0]
    assert Fibonacci.fibonacci(1) == sequence

def testFib2():
    # sequence of size 2
    sequence = [0,1]
    assert Fibonacci.fibonacci(2) == sequence

    # sequence of size > 2
    sequence = [0,1,1,2,3,5]
    assert Fibonacci.fibonacci(6) == sequence