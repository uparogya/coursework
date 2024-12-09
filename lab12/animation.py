'''
author: Jingsai Liang
date: 11-20-2019
update: 11-1-2020
'''

import matplotlib.pyplot as plt
from matplotlib import animation
from matplotlib.patches import Circle
# https://matplotlib.org/3.1.1/api/_as_gen/matplotlib.patches.FancyArrowPatch.html
from matplotlib.patches import FancyArrowPatch as Arrow

class draw:

    def __init__(self, path, row, col, rule):
        self.path = path
        self.row, self.col, self.rule = row, col, rule

        self.fig = plt.figure()
        self.ax = plt.axes(xlim=(-4.5, 4.5), ylim=(-0.5, 4.5))

        r = 0.3
        self.circles = [[Circle((0,4), r)],
                        [Circle((-1,3), r), Circle((1,3), r)],
                        [Circle((-2,2), r), Circle((0,2), r), Circle((2,2), r)],
                        [Circle((-3,1), r), Circle((-1,1), r), Circle((1,1), r), Circle((3,1), r)],
                        [Circle((-4,0), r), Circle((-2,0), r), Circle((0,0), r), Circle((2,0), r), Circle((4,0), r)]]

        for i in range(5):
            for j in range(i+1):
                self.ax.add_patch(self.circles[i][j])

        # dictionary from coordinate of circles to index of list
        self.dict = {(0,0):(0,4),
                     (1,0):(-1,3), (1,1):(1,3),
                     (2,0):(-2,2), (2,1):(0,2), (2,2):(2,2), 
                     (3,0):(-3,1), (3,1):(-1,1), (3,2):(1,1), (3,3):(3,1),
                     (4,0):(-4,0), (4,1):(-2,0), (4,2):(0,0), (4,3):(2,0), (4,4):(4,0)}

        # posA, posB, size, color, alpha
        self.arrow = Arrow((0,0),(0,0), mutation_scale=40, color='b', alpha=0, arrowstyle='-|>')
        self.ax.add_patch(self.arrow)

        self.animation()
    
    def animation(self):
        self.anim = animation.FuncAnimation(self.fig, self.draw, frames=2*len(self.path), interval=2000, blit=True, repeat=False)
        # save and show
        self.show()

    # draw
    def draw(self, index):
        node = self.path[index//2]
        board = node.board

        #print(index, jumpfrom, jumpto)

        for i in range(5):
            for j in range(i+1):
                if board[i][j]:
                    self.circles[i][j].set_color('r')
                else:
                    self.circles[i][j].set_color('k')
        # Draw the same board twice.
        # draw jump of next state in current state
        if index%2==1 and index < 2*(len(self.path)-1):
            next_node = self.path[index//2+1]
            jumpfrom = next_node.jumpfrom
            jumpto = next_node.jumpto
            self.arrow.set_alpha(1)
            self.arrow.set_positions(self.dict[jumpfrom], self.dict[jumpto])
            return [circle for sublist in self.circles for circle in sublist] + [self.arrow]
        else:
            self.arrow.set_alpha(0)
            return [circle for sublist in self.circles for circle in sublist] + [self.arrow]

    #  save & show
    def show(self):
        self.ax.set_axis_off()
        self.ax.set(aspect="equal")

        name = 'row_'+str(self.row)+'_column_'+str(self.col)+'_rule_'+str(self.rule)
        self.ax.set_title(name)

        # save the animation as an mp4.  This requires ffmpeg or mencoder to be
        # installed.  The extra_args ensure that the x264 codec is used, so that
        # the video can be embedded in html5.  You may need to adjust this for
        # your system: for more information, see
        # http://matplotlib.sourceforge.net/api/animation_api.html
        # For Mac user, run "brew install ffmpeg" to install ffmpeg
        # For Windows user, refer this page to install ffmpeg: http://blog.gregzaal.com/how-to-install-ffmpeg-on-windows/
        self.anim.save('peg_'+name+'.mp4', extra_args=['-vcodec', 'libx264'])

        plt.show()


    