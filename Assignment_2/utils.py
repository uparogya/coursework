"""Provides some utilities widely used by other modules"""

import heapq
import numpy as np
import matplotlib.pyplot as plt

# ______________________________________________________________________________
# Queues: Stack, FIFOQueue, PriorityQueue
# Stack and FIFOQueue are implemented as list and collection.deque
# PriorityQueue is implemented here

class Stack:
    "A container with a last-in-first-out (LIFO) queuing policy."
    def __init__(self):
        self.list = []

    def push(self,item):
        "Push 'item' onto the stack"
        self.list.append(item)

    def pop(self):
        "Pop the most recently pushed item from the stack"
        return self.list.pop()

    def isEmpty(self):
        "Returns true if the stack is empty"
        return len(self.list) == 0

class Queue:
    "A container with a first-in-first-out (FIFO) queuing policy."
    def __init__(self):
        self.list = []

    def push(self,item):
        "Enqueue the 'item' into the queue"
        self.list.insert(0,item)

    def pop(self):
        """
          Dequeue the earliest enqueued item still in the queue. This
          operation removes the item from the queue.
        """
        return self.list.pop()

    def isEmpty(self):
        "Returns true if the queue is empty"
        return len(self.list) == 0

class PriorityQueue:
    """
      Implements a priority queue data structure. Each inserted item
      has a priority associated with it and the client is usually interested
      in quick retrieval of the lowest-priority item in the queue. This
      data structure allows O(1) access to the lowest-priority item.
    """
    def  __init__(self):
        self.heap = []
        self.count = 0

    def push(self, item, priority):
        entry = (priority, self.count, item)
        heapq.heappush(self.heap, entry)
        self.count += 1

    def pop(self):
        (_, _, item) = heapq.heappop(self.heap)
        return item

    def isEmpty(self):
        return len(self.heap) == 0

    def update(self, item, priority):
        # If item already in priority queue with higher priority, update its priority and rebuild the heap.
        # If item already in priority queue with equal or lower priority, do nothing.
        # If item not in priority queue, do the same thing as self.push.
        for index, (p, c, i) in enumerate(self.heap):
            if i == item:
                if p <= priority:
                    break
                del self.heap[index]
                self.heap.append((priority, c, item))
                heapq.heapify(self.heap)
                break
        else:
            self.push(item, priority)
# --------------------------------------------------------------------------
# draw label and path of hanoi 

class Draw_Hanoi:
    def __init__(self, depth, solution=None, size=None):
        self.depth = depth
        self.size = size if size else 3*depth
        
        if solution:
            # pair of labels on all edges in two directions
            # if input is a,b,c, the output is ab,bc,cb,ba 
            tmp = solution + solution[-2::-1]
            self.solution_edges = list(zip(tmp[:-1], tmp[1:]))
        else:
            self.solution_edges = None
                
    # draw a line between coordinate p1 and p2 
    def plot(self, p1, p2, color='k'):
        plt.plot([p1[0], p2[0]],[p1[1], p2[1]], '.-', color=color, markersize=self.size)

    # return node at one third position from label 1 to label 2
    def get_label(self, label1, label2):
        # find first differnt letter/disk between two labels in backward
        for i in range(len(label1)-1,0,-1):
            if label1[i] != label2[i]:
                break
        # change/move first i letters/disks to the third/auxiliary peg
        third = list(set(['A','B','C']) - set([label1[0], label2[0]]))[0]
        return third * i + label1[i:]

    # check if edge belongs to solution if solution is provided 
    def check_edge(self, label1, label2):
        if self.solution_edges:
            if (label1, label2) in self.solution_edges:
                return 'r'
        return 'k'

    # recursively draw hanoi 
    def hanoi_graph(self, depth, points, labels):
        if depth == 1: # draw a triangle edges 01,12,20 from points 0,1,2 
            shift = [0.01,0.01]
            for i in range(3):
                j = i+1 if i < 2 else 0 # create pairs 01,12,20 
                self.plot(points[i], points[j], color = self.check_edge(labels[i], labels[j]))
                plt.annotate(labels[i], points[i]+shift, fontsize=120/self.size)
                
        elif depth >1:   
            """ create points/labels of next level and save in matrix, 
                with each position of the matrix is a coordinate/string. 
            input: points/labels 0,1,2
            output:
              None, 01, 02
              10, None, 12
              20, 21, None 
            """
            frac = (2**(depth-1)-1)/(2**depth-1)
            points_next = np.zeros((3,3,2))
            labels_next = np.empty((3,3), dtype=object)
            for i in range(3):
                for j in range(3):
                    if j != i:
                        # example: middle = from + (to - from) / 2 
                        points_next[i,j,:] = points[i] + (points[j]-points[i])*frac 
                        labels_next[i,j] = self.get_label(labels[i], labels[j])                      

            """
            recursively call three small triangles just created at next depth
          
            old big triangle points
              0
            1   2
            
            new small triangles points_next
                    0
                 01   02
              10        20
            1    12   21   2
            with
            0 01 02
            1 10 12
            2 20 21
            """
            for i in range(3):
                j = [0,1,2]
                j.remove(i)
                self.hanoi_graph(depth-1, np.array([points[i], points_next[i,j[0]], points_next[i,j[1]]]), 
                                 np.array([labels[i], labels_next[i,j[0]], labels_next[i,j[1]]]))
            
            """
            draw edges to connect newly created small triangles
            01 10
            02 20
            12 21 
            these three edges are pairs between upper and lower triangular of this matrix
            00 01 02
            10 11 12
            20 21 22
            """
            for i in range(3):
                for j in range(i,3):
                    if i != j:
                        self.plot(points_next[i,j], points_next[j,i], 
                                  color = self.check_edge(labels_next[i,j], labels_next[j,i]))
      
    def draw(self):
        p = np.array([[0,2], [-1,0], [1,0]])
        node = ['A'*self.depth, 'B'*self.depth, 'C'*self.depth]

        plt.figure(figsize=[self.size, self.size])
        self.hanoi_graph(self.depth, p, node)
        
        if self.solution_edges:
            # draw the goal points as red
            plt.plot([p[2,0], p[2,0]],[p[2,1], p[2,1]], '.', color='r', markersize=self.size)
        
        plt.axis("off")
        plt.axis("square")
        # plt.savefig("hanoi_{}.png".format(self.depth))
        plt.show()
        