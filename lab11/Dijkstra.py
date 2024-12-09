from bridges.graph_adj_list import *
import heapq

class Dijkstra():
    def __init__(self, inputFile, startingVertex, goalVertex):
        # an initially empty dictionary containing mapping: vertex: [child, weight]
        self.adjacency = {}
        # collection of vertices
        self.vertices = []
        # each dictionary entry contains mapping of vertex:parent
        self.parent = {}
        # startingVertex, goalVertex
        self.startingVertex, self.goalVertex = startingVertex, goalVertex

        self.parent_of_visited = {}

        # The following reads in the input file and constructs an adjacency list of the graph.
        graph = open(inputFile)
        for line in graph:
            entry = line.split()

            # get the vertices
            self.vertices.append(entry[0])
            self.vertices.append(entry[1])

            if entry[0] not in self.adjacency:
                self.adjacency[entry[0]] = []

            # construct an edge for the adjacency list
            edge = (entry[1], int(entry[2]))
            self.adjacency[entry[0]].append(edge)

        # remove duplication in vertices
        self.vertices = list(set(self.vertices))

        # checking if start and goal are in vertices
        if startingVertex not in self.vertices:
            print('Starting vertex', startingVertex, 'not present in graph')
            quit()
        elif goalVertex not in self.vertices:
            print('Goal vertex', goalVertex, 'not present in graph')
            quit()

        # create Bridges graph
        self.g = GraphAdjList()
        for vertex in self.vertices:
            self.g.add_vertex(vertex, str(vertex))
            self.g.get_visualizer(vertex).color = "red"
        
        for vertex in self.adjacency:
            for edge in self.adjacency[vertex]:
                self.g.add_edge(vertex, edge[0], edge[1])



    # solve it using Dijkstra algorithm
    def solve(self):
        index = 0
        heap = [(0, self.startingVertex, None)]
        visited_vertices = []
        final = [self.startingVertex]
        print(self.parent)

        while heap:
            current_vertex_cost_from_start, current_vertex, parent_vertex = heapq.heappop(heap)

            if current_vertex not in visited_vertices:

                if current_vertex not in self.adjacency:
                    if current_vertex == self.goalVertex:
                        self.parent_of_visited[current_vertex] = parent_vertex

                    return self.find_path()

                for adjacent_vertex in self.adjacency[current_vertex]:
                    this_vertex = adjacent_vertex[0]
                    this_length = adjacent_vertex[1]
                    this_cost_from_start = this_length + current_vertex_cost_from_start
                    temp_vertex = (this_cost_from_start, this_vertex, current_vertex)
                    heapq.heappush(heap, temp_vertex)

                visited_vertices.append(current_vertex)
                self.parent_of_visited[current_vertex] = parent_vertex

                if current_vertex == self.goalVertex:
                    return self.find_path()


    # retrieve the path from start to the goal 
    def find_path(self):
        self.path = []
        next_vertex = self.goalVertex
        while next_vertex != self.startingVertex:
            self.path.append(next_vertex)
            next_vertex = self.parent_of_visited[next_vertex]

        self.path.append(self.startingVertex)
        self.path.reverse()

    
    # draw the path as red
    def draw_path(self):
        print(self.path)
        for i in range(len(self.path)-1):
            self.g.get_link_visualizer(self.path[i], self.path[i+1]).color = "red"

    # return the Bridges object
    def get_graph(self):
        return self.g
