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


class Maze():
    
    def __init__(self, map, start_state, goal_state, map_index):
        self.start_state = start_state
        self.goal_state = goal_state
        self.map = map
        self.visited = [] # state
        self.m, self.n = map.shape 
        self.map_index = map_index


    def draw(self, node):
        path=[]
        while node.parent:
            path.append(node.state)
            node = node.parent
        path.append(self.start_state)

        draw(self.map, path[::-1], self.map_index)

    def goal_test(self, current_state):
        if current_state == self.goal_state:
            return True
        else:
            return False 


    def get_cost(self, current_state, next_state):
        return 1


    def get_successors(self, state):
        successors = []
        change_0 = [-1,1,0,0]
        change_1 = [0,0,1,-1]

        for i in range(4):
            new_position = (state[0]+change_0[i] , state[1]+change_1[i])

            if self.map[new_position[0]][new_position[1]] == 0.0 or self.map[new_position[0]][new_position[1]] == 0.5 or (new_position[0] < 0 or new_position[0] >= self.map.shape[0] or new_position[1] < 0 or new_position[1] >= self.map.shape[1]):
                continue

            successors.append(new_position)

        return successors


    # heuristics function
    def heuristics(self, state):
        min_manhattan_distance = abs(state[0]-self.goal_state[0]) + abs(state[1]-self.goal_state[1])
        return min_manhattan_distance


    # priority of node 
    def priority(self, node):
        return node.cost_from_start + self.heuristics(node.state)


    def check_visited(self, state):
        if state in self.visited:
            return True
        else:
            return False


    # solve it
    def solve(self):
        state = self.start_state

        if self.goal_test(self.start_state):
            return
        
        node = Node(state, 0, None)
        self.visited.append(state)

        index = 0
        fringe = [(self.priority(node),index,node)]

        while fringe:
            current_node = heappop(fringe)[2]
            successors = self.get_successors(current_node.state)

            for next_state in successors:
                if not self.check_visited(next_state):
                    self.visited.append(next_state)

                    next_cost = current_node.cost_from_start + self.get_cost(node.state, next_state)
                    next_node = Node(next_state, next_cost, current_node)

                    if self.goal_test(next_state):
                        self.draw(next_node)  # draw the solution
                        return
                    
                    index += 1
                    heappush(fringe, (self.priority(next_node), index, next_node))
            
    
if __name__ == "__main__":

    parser = argparse.ArgumentParser(description='maze')
    parser.add_argument('-index', dest='index', required = True, type = int)
    index = parser.parse_args().index

    # Example:
    # Run this in the terminal solving map 1
    #     python maze_astar.py -index 1
    
    data = np.load('map_'+str(index)+'.npz')
    map, start_state, goal_state = data['map'], tuple(data['start']), tuple(data['goal'])

    game = Maze(map, start_state, goal_state, index)
    game.solve()
    