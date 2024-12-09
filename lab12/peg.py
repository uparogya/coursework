from copy import deepcopy as copy
import argparse
from animation import draw

class Node():
    def __init__(self, board, jumpfrom = None, jumpover = None, jumpto = None):
        self.board = board
        self.jumpfrom = jumpfrom
        self.jumpover = jumpover
        self.jumpto = jumpto

class peg:
    def __init__(self, start_row, start_col, rule):
        self.size = 5
        self.start_row, self.start_col, self.rule = start_row, start_col, rule
        # board
        self.board = [[1 for j in range(i+1)] for i in range(self.size)]
        self.board[start_row][start_col] = 0
        self.start = Node(copy(self.board))
        # path
        self.path = []
        # Do some initialization work here if you need:
        self.path.append(Node(copy(self.board)))

    def emptyHole(self):
        empty_holes = []
        for x, row in enumerate(self.board):
            for y, column in enumerate(row):
                if column == 0:
                    empty_holes.append((x,y))
        
        return empty_holes

    def draw(self):
        if self.success():
            draw(self.path, self.start_row, self.start_col, self.rule)
        else:
            print("No solution were found!")


    def success(self):
        total = 0
        for row in self.board:
            for column in row:
                total += column
        
        return True if total == 1 else False


    def getJumps(self, current_board):
        move_1 = [-2,-2,0,0,2,2]
        move_2 = [0,-2,-2,2,0,2]
        empty_holes = self.emptyHole()

        jumps = []
        jumping_over = []
        for current_empty_hole in empty_holes:
            for i in range(6):

                if current_empty_hole[0] + move_1[i] >= len(current_board) or current_empty_hole[0] + move_1[i] < 0 or current_empty_hole[1] + move_2[i] < 0 or current_empty_hole[1] + move_2[i] >= len(current_board[current_empty_hole[0] + move_1[i]]):
                    continue
                
                if current_board[current_empty_hole[0]+move_1[i]//2][current_empty_hole[1]+move_2[i]//2] != 1:
                    continue

                if current_board[current_empty_hole[0]+move_1[i]][current_empty_hole[1]+move_2[i]] != 1:
                    continue

                jumping_over.append((current_empty_hole[0]+move_1[i]//2,current_empty_hole[1]+move_2[i]//2))
                jumps.append((current_empty_hole[0]+move_1[i], current_empty_hole[1]+move_2[i]))
        
        return (jumps, jumping_over)

        
    def solve(self):
        if self.success():

            if self.rule == 1:
                if self.board[self.start_row][self.start_col] == 0:
                    return False
                
            return True
        
        jumps, jumping_over = self.getJumps(self.board)
        
        for index, jumping_peg in enumerate(jumps):
            temp_board = copy(self.board)
            current_jumping_over = (jumping_over[index][0],jumping_over[index][1])
            current_jumping_peg = (jumping_peg[0],jumping_peg[1])
            current_empty_hole = (jumping_over[index][0]-(jumping_peg[0]-jumping_over[index][0]), jumping_over[index][1]-(jumping_peg[1]-jumping_over[index][1]))
            self.board[current_jumping_over[0]][current_jumping_over[1]] = 0
            self.board[current_jumping_peg[0]][current_jumping_peg[1]] = 0
            self.board[current_empty_hole[0]][current_empty_hole[1]] = 1
            new_path = Node(copy(self.board), jumping_peg, current_jumping_over, current_empty_hole)
            self.path.append(new_path)

            if self.solve():
                return True
            
            self.board = temp_board
            self.path.pop()
        
        return False


        
if __name__ == '__main__':

    parser = argparse.ArgumentParser(description='peg game')

    parser.add_argument('-hole', dest='position', required = True, nargs = '+', type = int, help='initial position of the hole')
    parser.add_argument('-rule', dest='rule', required = True, type = int, help='index of rule')

    args = parser.parse_args()

    start_row, start_col = args.position
    if start_row > 4:
        print("row must be less or equal than 4")
        exit()
    if start_col > start_row:
        print("column must be less or equal than row")
        exit()

    # Example: 
    # python peg.py -hole 0 0 -rule 0
    game = peg(start_row, start_col, args.rule)
    game.solve()
    game.draw()
    