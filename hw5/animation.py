'''
author: Jingsai Liang
date: 11-8-2018
'''

import matplotlib.pyplot as plt
import numpy as np 
from matplotlib import animation

class draw:

    def __init__(self, map, states, array_index):
        self.array_index = array_index
        self.row, self.column = map.shape
        self.map = map.copy()
        self.states = states
        # The size of video is 1000*500
        self.fig = plt.figure(figsize=(10,5))
        self.im = plt.imshow(np.zeros(self.map.shape))
        self.im.set_cmap(plt.cm.get_cmap("hot"))
        plt.xticks([]), plt.yticks([])
        plt.title('map_'+str(self.array_index))
        # save it
        self.save()
        # show it. map is needed to be resetted because map has been changed after saving. showing will redraw all frames starting from the initial state
        self.map = map.copy()
        self.show()
    
    def show(self):
        anim = animation.FuncAnimation(self.fig, self.draw, frames=len(self.states), interval=200, blit=True, repeat=False)
        plt.show()

    def save(self):
        anim = animation.FuncAnimation(self.fig, self.draw, frames=len(self.states), interval=200, blit=True, repeat=False)
        anim.save('maze_'+str(self.array_index)+'.mp4', extra_args=['-vcodec', 'libx264'])

    # draw
    def draw(self, index):
        if index:
            p, q = self.states[index-1]
            newp, newq = self.states[index]
            self.map[p,q], self.map[newp,newq] = self.map[newp,newq], self.map[p,q]

        self.im.set_data(self.map)
        self.im.autoscale()

        # return an iterable object 
        return [self.im]

        


    