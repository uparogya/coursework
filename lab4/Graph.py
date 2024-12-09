
'''
Demonstration of some simple graph algorithms.
    
@author: Jingsai Liang

Did BFS and DFS in lab
Did hasCycle and shortestpath alone
'''

import sys

class GraphAlgorithms:
    
    '''
    Reads in the specified input file containing
    adjacent edges in a graph and constructs an
    adjacency list.

    The adjacency list is a dictionary that maps
    a vertex to its adjacent vertices.
    '''
    def __init__(self, fileName): 
    
        graphFile = open(fileName)

        '''
        create an initially empty dictionary representing
        an adjacency list of the graph
        '''
        self.adjacencyList = { }
    
        '''
        collection of vertices in the graph (there may be duplicates)
        '''
        self.vertices = [ ]

        for line in graphFile:
            '''
            Get the two vertices
        
            Python lets us assign two variables with one
            assignment statement.
            '''
            (firstVertex, secondVertex) = line.split()
        
            '''
            Add the two vertices to the list of vertices
            At this point, duplicates are ok as later
            operations will retrieve the set of vertices.
            '''
            self.vertices.append(firstVertex)
            self.vertices.append(secondVertex)

            '''
            Check if the first vertex is in the adjacency list.
            If not, add it to the adjacency list.
            '''
            if firstVertex not in self.adjacencyList:
                self.adjacencyList[firstVertex] = [ ]

            '''
            Add the second vertex to the adjacency list of the first vertex.
            '''
            self.adjacencyList[firstVertex].append(secondVertex)
        
        # creates and sort a set of vertices (removes duplicates)
        self.vertices = list(set(self.vertices))
        self.vertices.sort()

        # sort adjacency list for each vertex
        for vertex in self.adjacencyList:
            self.adjacencyList[vertex].sort()

    '''
    Begins the DFS algorithm.
    '''
    def DFSInit(self):
        # initially all vertices are considered unknown
        self.unVisitedVertices = list(set(self.vertices))
        # initialize path as an empty string
        self.path = ""

    '''
    depth-first traversal of specified graph
    '''
    def DFS(self, method):
        self.DFSInit()
        if method == 'recursive':
            # Your code goes here:
            while self.unVisitedVertices:
                for unvisited in self.vertices:
                    if unvisited not in self.unVisitedVertices:
                        continue
                    if not self.unVisitedVertices:
                        break
                    self.DFS_recur(unvisited)
            return self.path
        elif method == 'stack':
            # Your code goes here:
            while self.unVisitedVertices:
                for unvisited in self.vertices:
                    if unvisited not in self.unVisitedVertices:
                        continue
                    if not self.unVisitedVertices:
                        break
                    self.DFS_stack(unvisited)

            
            return self.path
            

    def DFS_recur(self,vertex):
        self.unVisitedVertices.remove(vertex)
        self.path += vertex
        for child_vertex in self.adjacencyList[vertex]:
            if child_vertex in self.unVisitedVertices:
                self.DFS_recur(child_vertex)
        # Your code goes here:
        # pass # delete "pass" after writing your own code here 




            
                
    def DFS_stack(self, vertex):
        stack=[]
        stack.append(vertex)
        while stack:
            current_vertex = stack.pop()
            if current_vertex in self.unVisitedVertices:
                self.unVisitedVertices.remove(current_vertex)
                self.path += current_vertex
                for child_vertex in self.adjacencyList[current_vertex]:
                    if child_vertex in self.unVisitedVertices:
                        stack.append(child_vertex)
        # Your code goes here:





    def BFSInit(self):
        # initially all vertices are considered unknown
        self.unVisitedVertices = list(set(self.vertices))
        # initialize path as an empty string
        self.path = ""
        

    def BFS(self):
        self.BFSInit()
        while self.unVisitedVertices:
            for unvisited in self.vertices:
                if unvisited not in self.unVisitedVertices:
                    continue
                if not self.unVisitedVertices:
                    break
                self.BFS_queue(unvisited)

        return self.path
    
    def BFS_queue(self, vertex):
        queue = []
        # Your code goes here:
        self.unVisitedVertices.remove(vertex)
        queue.append(vertex)
        self.path += vertex
        while queue:
            current_vertex = queue.pop(0)
            for child_vertex in self.adjacencyList[current_vertex]:
                if child_vertex in self.unVisitedVertices:
                    self.unVisitedVertices.remove(child_vertex)
                    self.path += child_vertex
                    queue.append(child_vertex)


    def hasCycle(self):
        self.BFSInit()
        # print(self.adjacencyList)
        # print(self.vertices)
        queue = []
        self.unVisitedVertices.remove(self.vertices[0])
        queue.append(self.vertices[0])
        while queue:
            current_vertex = queue.pop(0)
            # print(current_vertex)
            for child_vertex in self.adjacencyList[current_vertex]:
                if child_vertex in self.unVisitedVertices:
                    # print(child_vertex)
                    for grand_child in self.adjacencyList[child_vertex]:
                        if grand_child not in self.unVisitedVertices and grand_child != current_vertex:
                            return True
                    self.unVisitedVertices.remove(child_vertex)
                    queue.append(child_vertex)

        # Your code goes here:
        return False # delete "pass" after writing your own code here 
                    
    # Work on this function for at most 10 extra points
    def shortestpath(self, p, q):
        self.BFSInit()
        queue = []
        level = { p: 0 }
        queue.append(p)
        self.unVisitedVertices.remove(p)

        while queue:
            current_vertex = queue.pop(0)

            if current_vertex == q:
                return level[current_vertex]

            for child in self.adjacencyList[current_vertex]:
                if child in self.unVisitedVertices:
                    self.unVisitedVertices.remove(child)
                    queue.append(child)
                    level[child] = level[current_vertex] + 1
        
        # Your code goes here:
         # delete "pass" after writing your own code here 
  
                
        

        

