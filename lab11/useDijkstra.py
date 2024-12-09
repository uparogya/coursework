from bridges.bridges import * 
from bridges.graph_adj_list import *
from Dijkstra import Dijkstra
import sys

def useDijkstra(inputFile, startingVertex, goalVertex):

    # Create a bridges object
    # Modify id and the account information
    bridges = Bridges(999, "au0304", "450444019629")
    
    obj = Dijkstra(inputFile, startingVertex, goalVertex)
    obj.solve()
    obj.find_path()
    obj.draw_path()
    g = obj.get_graph()

    # Pass the graph object to BRIDGES
    bridges.set_data_structure(g)
    # visualize the graph
    bridges.visualize()

if __name__ == '__main__':

    if len(sys.argv) != 4:
        print('Usage python useDijkstra.py [input file] [starting vertex] [goal vertex]')
        quit()

    useDijkstra(sys.argv[1], sys.argv[2], sys.argv[3])