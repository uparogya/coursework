import argparse
from itertools import permutations
import math
from draw import draw_path
from test import test_path

class password():
    def __init__(self, rule):
        self.rule = rule
        # Longest distance
        self.longest_length = 0.0
        # List of longest path. The longest path is not unique. 
        self.longest_path = []
        # Your code goes here:

        self.vertex_of = { 1: (0,0), 2: (1,0), 3: (2,0), 4: (0,1), 5: (1,1), 6: (2,1), 7: (0,2), 8: (1,2), 9: (2,2) }
        self.point_of = { (0,0):1, (1,0):2, (2,0):3, (0,1):4, (1,1):5, (2,1):6, (0,2):7, (1,2):8, (2,2):9 }
        self.all_possible_paths = permutations("123456789")

    # Find the longest path
    def find_longest_path(self):
        if self.rule == 1:
            all_valid_paths = []
            for paths in self.all_possible_paths:
                current_path_valid = True
                for i in range(0, len(paths)-1):
                    if not self.valid_rule_1_path(paths[i],paths[i+1]):
                        current_path_valid = False
                if current_path_valid:
                    all_valid_paths.append(paths)
                    length = self.calculate_total_length(paths)
                    if length > self.longest_length: 
                        self.longest_length = length

            for paths in all_valid_paths:
                if self.calculate_total_length(paths) == self.longest_length:
                    self.longest_path.append(''.join(paths))

        elif self.rule == 2:
            all_valid_paths = []
            for paths in self.all_possible_paths:
                if self.valid_rule_2_path(paths):
                    all_valid_paths.append(paths)
                    length = self.calculate_total_length(paths)
                    if length > self.longest_length: 
                        self.longest_length = length
            
            for paths in all_valid_paths:
                if self.calculate_total_length(paths) == self.longest_length:
                    self.longest_path.append(''.join(paths))

    def valid_rule_1_path(self, vertex1, vertex2):
        vertex_1_x, vertex_1_y = self.vertex_of[int(vertex1)]
        vertex_2_x, vertex_2_y = self.vertex_of[int(vertex2)]
        x_difference = abs(vertex_1_x-vertex_2_x)
        y_difference = abs(vertex_1_y-vertex_2_y)
        if(x_difference != 1 and y_difference != 1):
            return False
        else:
            return True
    
    def valid_rule_2_path(self, path):
        visited_vertices = []
        for i in range(0, len(path)-1):
            vertex1 = int(path[i])
            vertex2 = int(path[i+1])
            vertex_1_x, vertex_1_y = self.vertex_of[int(vertex1)]
            vertex_2_x, vertex_2_y = self.vertex_of[int(vertex2)]
            x_difference = abs(vertex_1_x-vertex_2_x)
            y_difference = abs(vertex_1_y-vertex_2_y)
            if(x_difference == 1 or y_difference == 1):
                visited_vertices.append(vertex1) if vertex1 not in visited_vertices else None
                visited_vertices.append(vertex2) if vertex2 not in visited_vertices else None
            else:
                if x_difference == 0 and y_difference != 0:
                    point_to_visit = (vertex_1_x, min(vertex_1_y,vertex_2_y)+1)
                elif x_difference != 0 and y_difference == 0:
                    point_to_visit = (min(vertex_1_x,vertex_2_x)+1, vertex_1_y)
                elif x_difference != 0 and y_difference != 0 and x_difference == y_difference:
                    point_to_visit = (min(vertex_1_x,vertex_2_x)+1, min(vertex_1_y,vertex_2_y)+1)
                else:
                    return False
                
                vertex = self.point_of[point_to_visit]
                if vertex not in visited_vertices:
                    return False
                else:
                    visited_vertices.append(vertex2) if vertex2 not in visited_vertices else None
        return True
        
    # Calculate distance between two vertices
    # Format of a coordinate is a tuple (x_value, y_value), for example, (1,2), (0,1)
    def distance(self, vertex1, vertex2):
        return math.sqrt((vertex1[0]-vertex2[0])**2 + (vertex1[1]-vertex2[1])**2)

    def calculate_total_length(self, path):
        total_length = 0
        for i in range(0, len(path)-1):
            total_length += self.distance(self.vertex_of[int(path[i])], self.vertex_of[int(path[i+1])])
        return total_length

    # Print and save the result
    def print_result(self):
        print("The longest length using rule " + str(self.rule) + " is:")
        print(self.longest_length)
        print()
        print("All paths with longest length using rule " + str(self.rule) + " are:") 
        print(self.longest_path)
        print()
        with open('results_rule'+str(self.rule)+'.txt', 'w') as file_handler:
            file_handler.write("{}\n".format(self.longest_length)) 
            for path in self.longest_path:
                file_handler.write("{}\n".format(path)) 

    # test the result 
    def test(self):
        test_path(self.longest_length, self.longest_path, self.rule)

    # draw first result
    def draw(self):
        if len(self.longest_path) > 0:
            draw_path(self.longest_path[0], self.rule)

if __name__ == "__main__":

    parser = argparse.ArgumentParser(description='PatternLock')
    parser.add_argument('-rule', dest='rule', required = True, type = int, help='Index of the rule')
    args = parser.parse_args()

    # usage
    # python PatternLock.py -rule 1
    # python PatternLock.py -rule 2
    
    # Initialize the object using rule 1 or rule 2
    run = password(args.rule)
    # Find the longest path
    run.find_longest_path()
    # Print and save the result
    run.print_result()
    # Draw the first longest path
    run.draw()
    # Verify the result 
    run.test()