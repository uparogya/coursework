'''
author: Jingsai Liang
date: 10-25-2018
'''

import matplotlib.pyplot as plt
import numpy as np 
from matplotlib import animation

class draw:

    def __init__(self, states, array_index, algorithm, method):
        self.array_index = array_index
        self.algorithm = algorithm
        self.method = method
        self.row, self.column = states[0].shape
        self.states = states
        self.fig = plt.figure()
        # set boundary 0.1 away from 0 and 3 to show the bold lines.
        self.ax = plt.axes(xlim=(-0.1, 3.1), ylim=(-0.1, 3.1))
        plt.rcParams.update({'font.size': 50})
        # eight patches and texts for 3 by 3 board.
        self.patch = [self.ax.fill([0],[0])[0] for i in range(self.row*self.column-1)]
        self.text = [self.ax.text(0,0,'') for i in range(self.row*self.column-1)]
        self.animation()
    
    def animation(self):
        self.anim = animation.FuncAnimation(self.fig, self.draw, frames=len(self.states), interval=1000, blit=False, repeat=False)
        # save and show
        self.show()

    # position is (i,j)
    def get_xy_coordinate(self, position):
        r1, r2 = position[0], position[0]+1
        c1, c2 = position[1], position[1]+1
        return [[c1,r1],[c2,r1],[c2,r2],[c1,r2]]

    # draw
    def draw(self, index):
        # board
        board = []
        for col in range(self.column + 1):
            x = [i for i in range(self.column +1)]
            for row in range(self.row+1):
                y = [row for i in range(self.column +1)]
                line, = self.ax.plot(x,y, 'k')
                board.append(line)

        for row in range(self.row+1):
            y = [i for i in range(self.row+1)]
            for col in range(self.column +1):
                x = [col for i in range(self.row +1)]
                line, = self.ax.plot(x,y, 'k')
                board.append(line)
        
        # squares and texts
        count = 0
        for row in range(self.row):
            for column in range(self.column):
                if self.states[index][row,column]:
                    # set squares
                    xy = self.get_xy_coordinate((row,column))
                    self.patch[count].set_xy(xy)
                    self.patch[count].set_color('y')
                    # set texts
                    px, py = column + 0.3, row + 0.3
                    self.text[count].set_x(px)
                    self.text[count].set_y(py)
                    self.text[count].set_text(self.states[index][row,column])
                    count += 1
        
        return self.patch + self.text + board

    #  save & show
    def show(self):
        self.ax.set_axis_off()
        self.ax.set(aspect="equal")
        plt.rcParams.update({'font.size': 10})
        self.ax.set_title(str(self.array_index)+'_'+self.algorithm+'_'+self.method)

        # switching this line below self.anim.save will make a wrong animation with the goal state as the first frame in plt.show() 
        plt.show()

        # save the animation as an mp4.  This requires ffmpeg or mencoder to be
        # installed.  The extra_args ensure that the x264 codec is used, so that
        # the video can be embedded in html5.  You may need to adjust this for
        # your system: for more information, see
        # http://matplotlib.sourceforge.net/api/animation_api.html
        # For Mac user, run "brew install ffmpeg" to install ffmpeg
        # For Windows user, refer this page to install ffmpeg: http://blog.gregzaal.com/how-to-install-ffmpeg-on-windows/
        self.anim.save('eight_puzzle_'+str(self.array_index)+'_'+self.algorithm+'_'+self.method+'.mp4', extra_args=['-vcodec', 'libx264'])


    