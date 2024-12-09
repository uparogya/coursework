import numpy as np
from heapq import heappush, heappop
from animation import draw
import argparse

class Node():
    """
    cost_from_start - the cost of reaching this node from the starting node
    state - the state (row,col)
    parent - the parent node of this node, default as None
    """
    def __init__(self, state, cost_from_start, parent = None):
        self.state = state
        self.parent = parent
        self.cost_from_start = cost_from_start


class EightPuzzle():
    
    def __init__(self, start_state, goal_state, method, algorithm, array_index):
        self.start_state = start_state
        self.goal_state = goal_state
        self.visited = [] # state
        self.method = method
        self.algorithm = algorithm
        self.m, self.n = start_state.shape 
        self.array_index = array_index
        self.correct_positions = {1:(0,0),2:(0,1),3:(0,2),4:(1,0),5:(1,1),6:(1,2),7:(2,0),8:(2,1),0:(2,2)}
        

    def goal_test(self, current_state):
        for i, row in enumerate(current_state):
            for j, element in enumerate(row):
                if element != self.goal_state[i][j]:
                    return False
        
        return True

    def get_cost(self, current_state, next_state):
        return 1

    def get_successors(self, state):
        successors = []
        blank_space = ()
        for i, row in enumerate(state):
            for j, element in enumerate(row): 
                if element == 0:
                    blank_space = (i,j)
                    break
        
        # replacings:
        change_1 = [-1,1,0,0]
        change_2 = [0,0,1,-1]

        for i in range(4):
            new_child = state.copy()
            if blank_space[0]+change_1[i] > 2 or blank_space[0]+change_1[i] < 0 or blank_space[1]+change_2[i] < 0 or blank_space[1]+change_2[i] > 2:
                continue

            new_child[blank_space[0]][blank_space[1]] = state[blank_space[0]+change_1[i]][blank_space[1]+change_2[i]]
            new_child[blank_space[0]+change_1[i]][blank_space[1]+change_2[i]] = 0
            successors.append(new_child)
        
        # print(successors)
        return successors

    # heuristics function
    def heuristics(self, state):
        if self.method == 'Hamming':
            correct_places = 0
            for row in range(len(state)):
                for element in range(len(state)):
                    if state[row][element] != self.goal_state[row][element]:
                        correct_places += 1

            # for i, row in enumerate(state):
            #     for j, element in enumerate(row):
            #         if element == self.goal_state[i][j]:
            #             correct_places += 1

            return correct_places

        if self.method == 'Manhattan':
            moves = 0
            for i, row in enumerate(state):
                for j, element in enumerate(row):
                    if element == 0:
                        continue
                    this_position = (i,j)
                    if self.correct_positions[element] != this_position:
                        moves += abs(self.correct_positions[element][0]-this_position[0]) + abs(self.correct_positions[element][1]-this_position[1])

            return moves

    # priority of node 
    def priority(self, node):
        if self.algorithm == 'Greedy':
            return self.heuristics(node.state)
        if self.algorithm == 'AStar':
            return node.cost_from_start + self.heuristics(node.state)

    def check_visit(self, state):
        for previous_visit in self.visited:
            if np.array_equal(state, previous_visit):
                return True
        
        return False
    
    # draw 
    def draw(self, node):
        path=[]
        while node.parent:
            path.append(node.state)
            node = node.parent
        path.append(self.start_state)

        draw(path[::-1], self.array_index, self.algorithm, self.method)

    # solve it
    def solve(self):
        state = self.start_state.copy()  # use copy() to copy value instead of reference

        # already solved
        if self.goal_test(self.start_state):
            return

        node = Node(state, 0, None)
        self.visited.append(state) 

        index = 0
        fringe = [(self.priority(node), index, node)]  # node

        # fringe.append((self.priority(node),node))

        while fringe:
            current_node = heappop(fringe)[2]

            successors = self.get_successors(current_node.state)

            for next_state in successors:
                if not self.check_visit(next_state):
                    next_cost = current_node.cost_from_start + self.get_cost(node.state, next_state)
                    self.visited.append(next_state)
                    next_node = Node(next_state, next_cost, current_node)

                    if self.goal_test(next_state):
                        self.draw(next_node)  # draw the solution
                        return

                    index += 1
                    heappush(fringe, (self.priority(next_node), index, next_node))

if __name__ == "__main__":
    
    goal = np.array([[1,2,3],[4,5,6],[7,8,0]])
    start_arrays = [np.array([[1,2,0],[3,4,6],[7,5,8]]),
                    np.array([[8,1,3],[4,0,2],[7,6,5]]),
                    np.array([[1,2,0],[4,6,3],[7,5,8]])]
    methods = ["Hamming", "Manhattan"]
    algorithms = ['Greedy', 'AStar']
    
    parser = argparse.ArgumentParser(description='eight puzzle')

    parser.add_argument('-array', dest='array_index', required = True, type = int, help='index of array')
    parser.add_argument('-method', dest='method_index', required = True, type = int, help='index of method')
    parser.add_argument('-algorithm', dest='algorithm_index', required = True, type = int, help='index of algorithm')

    args = parser.parse_args()

    # Example:
    # Run this in the terminal using array 0, method Hamming, algorithm AStar:
    #     python eight_puzzle.py -array 0 -method 0 -algorithm 3
    game = EightPuzzle(start_arrays[args.array_index], goal, methods[args.method_index], algorithms[args.algorithm_index], args.array_index)
    game.solve()