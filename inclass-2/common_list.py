# create a new list, check the common between the two lists, if it is already in the new list, don't keep otherwise keep

def findCommon(list_1, list_2):
    final_list = []
    for element in list_1:
        if element in list_2 and element not in final_list:
            final_list.append(element)
    return final_list

print(findCommon([1,2,3,4,5],[1,1,6,4,7,9]))