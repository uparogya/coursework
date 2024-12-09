import heapq

class Elevation():
    def __init__(self, grid_layout, start_vertex, goal_vertex):
        self.elevation_map = grid_layout
        self.start_vertex = start_vertex
        self.goal_vertex = goal_vertex
        self.total_rows, self.total_cols = grid_layout.shape
        self.corresponding_parent = {}
        self.final_path = []
    
    def goal_test(self, current_vertex):
        return True if current_vertex == self.goal_vertex else False
    
    def get_successors(self, vertex):
        change_1 = [-1,-1,-1,0,0,1,1,1]
        change_2 = [-1,0,1,-1,1,-1,0,1]
        successors = []

        for i in range(8):
            y_coor = vertex[0]+change_1[i]
            x_coor = vertex[1]+change_2[i]
            if y_coor < 0 or y_coor >= self.total_rows or x_coor < 0 or x_coor >= self.total_cols:
                continue
            else:
                successors.append((y_coor,x_coor))
        
        return successors
    
    def getElevationChange(self,vertex_1,vertex_2):
        return abs(self.elevation_map[vertex_1[0]][vertex_1[1]] - self.elevation_map[vertex_2[0]][vertex_2[1]])

    def solve_dijkstra(self):
        heap = [(0, self.start_vertex, None)] # (elevation_change_from_start, current_vertex, parent_vertex)
        visited_vertices = []

        while heap:
            current_vertex_elevation_change_from_start, current_vertex, parent_vertex = heapq.heappop(heap)

            if current_vertex not in visited_vertices:
                visited_vertices.append(current_vertex)

                successors = self.get_successors(current_vertex)
                for successor in successors:
                    
                    if successor in visited_vertices:
                        continue

                    this_elevation_change_from_start = self.getElevationChange(current_vertex, successor) + current_vertex_elevation_change_from_start
                    heap_element = (this_elevation_change_from_start, successor, current_vertex)
                    heapq.heappush(heap, heap_element)
                
                self.corresponding_parent[current_vertex] = parent_vertex

                if self.goal_test(current_vertex):
                    return self.calculatePath()
    
    def solve_greedy(self):
        visited_vertices = []
        current_vertex = self.start_vertex
        visited_vertices.append(current_vertex)

        while not self.goal_test(current_vertex):
            next_vertices = self.get_successors(current_vertex)
            temp_elevation_change = float('inf')
            next_vertex = None
            for vertex in (next_vertices):
                if vertex in visited_vertices:
                    continue

                if self.getElevationChange(vertex,current_vertex) < temp_elevation_change:
                    temp_elevation_change = self.getElevationChange(vertex,current_vertex)
                    next_vertex = vertex

            if next_vertex is None:
                print("Couldn't Solve!")
                return visited_vertices
            
            visited_vertices.append(next_vertex)
            self.final_path.append(next_vertex)
            self.corresponding_parent[next_vertex] = current_vertex
            current_vertex = next_vertex

        return visited_vertices

    def solve_greedy_priority_queue(self):
        heap = [(0, self.start_vertex, None)]  # (elevation_change, current_vertex, parent_vertex)
        visited_vertices = []

        while heap:
            current_elevation_change, current_vertex, parent_vertex = heapq.heappop(heap)

            if current_vertex not in visited_vertices:
                visited_vertices.append(current_vertex)
                self.corresponding_parent[current_vertex] = parent_vertex

                if self.goal_test(current_vertex):
                    return self.calculatePath()

                successors = self.get_successors(current_vertex)
                for successor in successors:
                    if successor in visited_vertices:
                        continue

                    elevation_change = self.getElevationChange(current_vertex, successor)
                    heapq.heappush(heap, (elevation_change, successor, current_vertex))

        return self.calculatePath()

    def calculatePath(self):
        next_vertex = self.goal_vertex
        while next_vertex:
            self.final_path.insert(0, next_vertex)
            next_vertex = self.corresponding_parent[next_vertex]

        # print(self.final_path)
        return self.final_path
    
    def getPathString(self):
        string = '| '
        next_path = 0
        while True:
            string += '(' + str(self.final_path[next_path][1]) + ',' + str(self.final_path[next_path][0]) + ') -> '
            next_path += 1
            string += '(' + str(self.final_path[next_path][1]) + ',' + str(self.final_path[next_path][0]) + ') : ['
            string += str(abs(self.elevation_map[self.final_path[next_path]] - self.elevation_map[self.final_path[next_path - 1]])) + ' Units] | '

            if next_path == len(self.final_path) - 1:
                break

        return string
    
    def getMaxPointsReached(self):
        return len(self.final_path)