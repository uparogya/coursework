# STUDENT NAME: AROGYA AND DRAKE

import pytest

import Closest

def test1():
    expected = (-13, -14)
    actual = Closest.closest_pair([-13, 5, 18, 7, -14, 21])
    assert expected == actual

def test2():
    expected = (-13, -13)
    actual = Closest.closest_pair([-13, 5, 18, 7, -14, 21, -13, 5, 18, 7, -14, 21, -13, 5, 18, 7, -14, 21])
    assert expected == actual