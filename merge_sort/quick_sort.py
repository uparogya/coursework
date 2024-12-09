def QuickSort(unsorted):
    if len(unsorted) <= 1:
        return unsorted
    
    i = 1
    j = len(unsorted) - 1
    pivot = unsorted[0]

    while i <= j:
        while i <= j and unsorted[i] <= pivot:
            i += 1
        while i<= j and unsorted[j] >= pivot:
            j -= 1
        
        if i < j:
            unsorted[i], unsorted[j] = unsorted[j], unsorted[i]
    
    unsorted[0], unsorted[j] = unsorted[j], pivot
    left_sorted = QuickSort(unsorted[:j])
    right_sorted = QuickSort(unsorted[j+1:])

    return(left_sorted + [unsorted[j]] + right_sorted)
    # print(left_sorted)
    # print(right_sorted)
    # print(i,j)
    # print(unsorted)
    # print("NEW MID = ", unsorted[j])

# numbers = [5,3,1,9,8,2,4,7]
# print(QuickSort(numbers))
# numbers = [1,2,3,4,0,5]
numbers = [4,3,1,0,4]
print(QuickSort(numbers))