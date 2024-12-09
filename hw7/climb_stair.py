def climb_stair(n):
    if n == 2:
        return 2
    
    if n == 1:
        return 1
    
    return climb_stair(n-1) + climb_stair(n-2)


if __name__ == "__main__":
    print(climb_stair(10))
    print(climb_stair(20))
    print(climb_stair(30))

