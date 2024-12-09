import pytest

from change_making import change_making
from climb_stair import climb_stair
from roll_dice import roll_dice


def test_change_making():
    d=[3,4,6,1,7]
    n=7
    assert change_making(d, n) == 1
    
    d = [1,2,3,4,5,6]
    n = 10
    assert change_making(d, n) == 2

    d=[1,2,4,6,8,10,22,23]
    n=40
    assert change_making(d, n) == 3

    d=[1,2,10,11,12,15,19,30]
    n=1900
    assert change_making(d, n) == 64


def test_climb_stair():
    n = 10
    assert climb_stair(n) == 89

    n = 20
    assert climb_stair(n) == 10946

    n = 30
    assert climb_stair(n) == 1346269

def test_roll_dice():
    N, M = 2, 7
    assert roll_dice(N, M) == 6

    N, M = 3, 9
    assert roll_dice(N, M) == 25

    N, M = 8, 24
    assert roll_dice(N, M) == 98813