def MergeSort(unsorted):
    if len(unsorted) == 1:
        return unsorted
    
    mid_point = len(unsorted) // 2
    left_array = unsorted[:mid_point]
    right_array = unsorted[mid_point:]

    left_sorted = MergeSort(left_array)
    right_sorted = MergeSort(right_array)
    return Merge(left_sorted, right_sorted)

def Merge(list1, list2):
    sorted_list = []
    index_1 = 0
    index_2 = 0

    while index_1 < len(list1) and index_2 < len(list2):
        if list1[index_1] < list2[index_2]:
            sorted_list.append(list1[index_1])
            index_1 += 1
        else:
            sorted_list.append(list2[index_2])
            index_2 += 1
    
    if index_1 == len(list1):
        sorted_list = sorted_list + list2[index_2:]
    
    if index_2 == len(list2):
        sorted_list = sorted_list + list1[index_1:]
    
    return sorted_list

numbers = [2,6,1,9,0,3]
print(MergeSort(numbers))