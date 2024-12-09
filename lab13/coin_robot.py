import random 
from draw import draw
import argparse

class coin_robot:

    def __init__(self, row, column):
        random.seed(0)
        self.row = row
        self.column = column 
        # Get map
        self.map = [[0 for i in range(column)] for j in range(row)]
        self.generate_map()
        
    def generate_map(self):
        for i in range(self.row):
            for j in range(self.column):
                if random.random() > 0.7:
                    self.map[i][j] = 1 # coin
                else:
                    self.map[i][j] = 0

    def solve(self):
        F = [[0]*self.column for i in range(self.row)]
        path = [[[]]*self.column for i in range(self.row)]
        # print(F)
        # current_max = 0
        for i in range(self.row):
            for j in range(self.column):
                F[i][j] = max(F[i-1][j], F[i][j-1]) + self.map[i][j]


                if F[i-1][j] > F[i][j-1]:
                    path[i][j] = path[i-1][j].copy()
                else:
                    path[i][j] = path[i][j-1].copy()
                
                path[i][j].append((i,j))

        # print(path[-1][-1])
        # print(F[-1][-1])
        

        # Modify this line to call draw() to draw the path 
        self.draw(F[-1][-1], path[-1][-1])
        

    # F is the max number of coin 
    # path is the route of the robot from top-left to bottom-right
    def draw(self, F, path):
        title = "row_"+str(self.row)+"_column_"+str(self.column)+"_value_"+str(F)
        draw(self.map, path, title)

if __name__ == '__main__':

    parser = argparse.ArgumentParser(description='coin robot')

    parser.add_argument('-row', dest='row', required = True, type = int, help='number of row')
    parser.add_argument('-column', dest='column', required = True, type = int, help='number of column')

    args = parser.parse_args()

    # Example: 
    # python coin_robot.py -row 20 -column 20
    game = coin_robot(args.row, args.column)
    game.solve()