from lib2to3.pygram import pattern_grammar

import numpy as np
import argparse

class NameSearch:

    def __init__(self, Name_List, Name_Algorithm, Name_Length):
        # Matrix of the word search puzzle 
        self.matrix = np.load("./data/matrix.npy")
        self.nRows, self.cColumns = self.matrix.shape
        # Name of the algorithm
        self.Name_Algorithm = Name_Algorithm
        # Length of the name
        self.Name_Length = Name_Length
        # List of all potential names 
        with open("./data/names/"+Name_List+".txt", 'r') as f:
            self.names = f.read().splitlines()
        self.names = [n.upper().strip() for n in self.names]

    def match_BruteForce(self, pattern, text):
        for index, letter in enumerate(text):
            if letter == pattern[0]:
                match_count = 0
                for i in range(0, len(pattern)):
                    if index+i < len(text):
                        if text[index+i] != pattern[i]:
                            break
                        else:
                            match_count += 1
                            if match_count == len(pattern):
                                return True
        return False

    def match_Horspool(self, pattern, text):
        #building shift table:
        shift_table = {}
        for index, letter in enumerate(pattern):
            if letter not in shift_table:
                shift_table[letter] = len(pattern) - (index+1)
            else:
                current_shift = shift_table[letter]
                new_shift = len(pattern) - (index+1)
                if not new_shift:
                    continue
                if not current_shift or new_shift < current_shift:
                    shift_table[letter] = new_shift

        # last word will give shift 0 which will result in infinite loop
        if shift_table[pattern[-1]] == 0:
            shift_table[pattern[-1]] = len(pattern)
        
        # implementing the pdf's algorithm with same variable names
        m = len(pattern)
        n = len(text)
        i = m - 1
        while i<= n-1:
            k = 0
            while k <= m and pattern[m - 1 - k] == text[i - k]:
                k = k+1
            if k == m:
                return True
            else:
                to_shift = shift_table[text[i]] if text[i] in shift_table else len(pattern)
                i = i+to_shift

        return False
        
    def search(self):
        # pattern is each name in self.names
        # text is each horizontal, vertical, and diagonal strings in self.matrix 
        if self.Name_Algorithm == "BruteForce":
            # print(self.names)
            for pattern in self.names:
                if len(pattern) != self.Name_Length:
                    continue
                # print("-----CHECKING ROWS-----")
                for row_num in range(0, self.nRows):
                    text = self.matrix[row_num, :]
                    if self.match_BruteForce(pattern, text):
                        print(pattern)
                # print("-----CHECKING COLUMNS-----")
                for col_num in range(0, self.cColumns):
                    text = self.matrix[:, col_num]
                    if self.match_BruteForce(pattern, text):
                        print(pattern)
                # print("-----CHECKING DIAGONAL FORWARD-----")
                for i in range(-19,20):
                    # text = self.matrix.diagonal(i)
                    text = self.customDiagonal(self.matrix, i)
                    if self.match_BruteForce(pattern, text):
                        print(pattern)
                # print("-----CHECKING DIAGONAL BACKWARD-----")
                for i in range(-19,20):
                    # text = self.matrix[:,::-1].diagonal(i)
                    text = self.customDiagonal(self.matrix[:,::-1], i)
                    if self.match_BruteForce(pattern, text):
                        print(pattern)

        elif self.Name_Algorithm == "Horspool":
            for pattern in self.names:
                if len(pattern) != self.Name_Length:
                    continue
                # print("-----CHECKING ROWS-----")
                for row_num in range(0, self.nRows):
                    text = self.matrix[row_num, :]
                    if self.match_Horspool(pattern, text):
                        print(pattern)
                # print("-----CHECKING COLUMNS-----")
                for col_num in range(0, self.cColumns):
                    text = self.matrix[:, col_num]
                    if self.match_Horspool(pattern, text):
                        print(pattern)
                # print("-----CHECKING DIAGONAL FORWARD-----")
                for i in range(-19,20):
                    # text = self.matrix.diagonal(i)
                    text = self.customDiagonal(self.matrix, i)
                    if self.match_Horspool(pattern, text):
                        print(pattern)
                # print("-----CHECKING DIAGONAL BACKWARD-----")
                for i in range(-19,20):
                    # text = self.matrix[:,::-1].diagonal(i)
                    text = self.customDiagonal(self.matrix[:,::-1], i)
                    if self.match_Horspool(pattern, text):
                        print(pattern)

    def customDiagonal(self, matrix, input_index):
        if input_index >= 0:
            row = 0
            column = input_index
        else:
            row = abs(input_index)
            column = 0

        elements = []
        while row < self.nRows and column < self.cColumns:
            elements.append(matrix[row][column])
            row += 1
            column += 1

        return elements


if __name__ == "__main__":
        
    parser = argparse.ArgumentParser(description='Word Searching')
    parser.add_argument('-name', dest='Name_List', required = True, type = str, help='Name of name list')
    parser.add_argument('-algorithm', dest='Name_Algorithm', required = True, type = str, help='Name of algorithm')
    parser.add_argument('-length', dest='Name_Length', required = True, type = int, help='Length of the name')
    args = parser.parse_args()

    # Example:
    # python name_search.py -algorithm BruteForce -name Mexican -length 5

    obj = NameSearch(args.Name_List, args.Name_Algorithm, args.Name_Length)
    obj.search()