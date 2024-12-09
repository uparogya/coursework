'''
Created on Jul 17, 2016

@author: greg gagne
'''

'''
Generate the fibonacci sequence of specified size

This version has errors that are to be detected using unit tests
'''
def fibonacci(size):
    # creates an empty list
    sequence = []
    
    if size == 0:
        pass
    else:
        # add the first two values to the list
        sequence.append(0)
        sequence.append(1)
        count = 2
        
        while count < size:
            sequence.append(sequence[count-1] + sequence[count-2])
            count += 1
            
    return sequence
    
