

class TopologicalSorting:
    
    '''
    Reads in the specified input file containing
    adjacent edges in a directed graph and constructs an
    adjacency list.

    The adjacency list is a dictionary that maps
    a vertex to its adjacent vertices.
    '''
    def __init__(self, fileName): 
        # file name
        self.name = fileName
        
        graphFile = open(fileName)

        '''
        create an initially empty dictionary representing
        an adjacency list of the graph
        '''
        self.adjacencyList = { }

        self.incomingAdjacency = { }
    
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

            if secondVertex not in self.incomingAdjacency:
                self.incomingAdjacency[secondVertex] = [ ]

            '''
            Add the second vertex to the adjacency list of the first vertex.
            '''
            self.adjacencyList[firstVertex].append(secondVertex)
            self.incomingAdjacency[secondVertex].append(firstVertex)
        
        # creates and sort a set of vertices (removes duplicates)
        self.vertices = list(set(self.vertices))
        self.vertices.sort()

        # sort adjacency list for each vertex
        for vertex in self.adjacencyList:
            self.adjacencyList[vertex].sort()

        # sorted list
        self.sortedList = []

        self.possiblePaths = []

    def print_and_save(self):
        #print(self.vertices)
        #print(self.adjacencyList)
        self.sort()
        print(self.sortedList)
        with open('result_'+str(self.name), 'w') as file_handler:
            for node in self.sortedList:
                file_handler.write("{}\n".format(node)) 
        

    # Topological sorting using decrease-by-one-and-conquer. 
    def sort(self):
        # print(self.vertices)
        # Your code goes here:
        while self.vertices:
            for vertex in self.vertices:
                if vertex not in self.incomingAdjacency:
                    self.vertices.remove(vertex)
                    self.sortedList.append(vertex)
                    break
                if vertex in self.adjacencyList and len(self.adjacencyList[vertex]) == 1:
                        delete_status = True
                        for parent_vertex in self.incomingAdjacency[vertex]:
                            if parent_vertex in self.vertices:
                                delete_status = False
                                break
                        if delete_status:
                            self.vertices.remove(vertex)
                            self.sortedList.append(vertex)
                            break
                        # print(vertex)
                else:
                    delete_status = True
                    for parent_vertex in self.incomingAdjacency[vertex]:
                        if parent_vertex in self.vertices:
                            delete_status = False
                            break
                    if delete_status:
                        self.vertices.remove(vertex)
                        self.sortedList.append(vertex)
                        break

    # How many different ways can the spider reach the fly by moving along the web’s lines in the directions indicated by the arrow?
    def spider(self,start,end):
        # Your code goes here:
        self.pathFinder(start, '', end)
        with open('result_spider_count.txt', 'w') as file_handler:
            file_handler.write("{}\n".format('Total Ways = ' + str(len(self.possiblePaths)) + '\n'))
            for node in self.possiblePaths:
                file_handler.write("{}\n".format(node))

    def pathFinder(self, vertex, parent, end):
        if vertex == end:
            self.possiblePaths.append(parent)
            return
        
        if not parent:
            parent = vertex

        next_vertices = self.adjacencyList[vertex]
        for next_vertex in next_vertices:
            self.pathFinder(next_vertex, parent+next_vertex, end)


if __name__ == "__main__":

    s = TopologicalSorting("graph_example.txt")
    s.print_and_save()

    # Be careful! graph-courses.txt is incomplete. Please finish this txt file at first. 
    s = TopologicalSorting("graph_courses.txt")
    s.print_and_save()

    s = TopologicalSorting("graph_spider.txt")
    s.print_and_save()

    s = TopologicalSorting("graph_spider.txt")
    s.spider('A','F')