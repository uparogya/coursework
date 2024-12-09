
import Graph

ga1 = Graph.GraphAlgorithms('graph-1.txt')
ga2 = Graph.GraphAlgorithms('graph-2.txt')
ga3 = Graph.GraphAlgorithms('graph-3.txt')
ga4 = Graph.GraphAlgorithms('graph-4.txt')

# Example
print("DFS recursive:")
print(ga2.DFS('recursive'))

print("DFS stack:")
print(ga2.DFS('stack'))

print("BFS:")
print(ga2.BFS())

print("hasCycle?")
print(ga3.hasCycle())

print("Shortest path from a to f:")
print(ga4.shortestpath('a','f'))

