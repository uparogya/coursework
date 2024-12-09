'''
    CoPrime.py

    Generates a graph of the m x n co-primes
    
    [AROGYA UPADHYAYA]
'''

import sys

'''
generates the co-primes in an m x n matrix
'''
def coprimes(m, n):
    '''
    creates a list of size n each with
    each element initialized to None
    '''
    result = [None] * (m)

    '''
    each element in the list is now a
    list of size m where each value
    is initialized to a space ' '
    '''
    for i in range(0,m):
        result[i] = ['^'] * (n)

    
    
    for x in range(m):
        for y in range(n):
            # just sending x and y will give reversed matrix as output
            # top left will be (1,1) and bottom right will be (15,15)
            # when 15 15 is provided as parameter: 
            # m-x means that the first will be 15 - 0 = 15 -> 14 -> 13 .... 1 & y+1 will be 1 -> 2 -> 3 .... 15

            if areCoPrimes(m-x,y+1):
                result[x][y] = '*'
            else:
                result[x][y] = ' '

        
    '''
    output the contents of result
    '''
    for x in result:
        # x[:] is a list "slice"
        for y in x[:]:
            '''
            by putting a comma at the end, we prevent a newline
            '''
            print(y + ' ', end="")
            
        print() 

    '''
        YOUR WORK WILL GO HERE
    '''


def areCoPrimes(x, y):
    for i in range(2, min(x,y)+1):
        if x%i == 0 and y%i == 0:
            return False

    return True

# behaves like main() method

if __name__ == "__main__":
    # some error checking
    if len(sys.argv) != 3:
        print('Usage\n python CoPrime [int] [int]')
        quit()

    coprimes(int(sys.argv[1]), int(sys.argv[2]))
