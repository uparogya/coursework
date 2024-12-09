
import matplotlib.pyplot as plt
from matplotlib.patches import Circle

def draw(map, path, title):
    row, column = len(map), len(map[0])
    ax = plt.axes(xlim=(-0.5,column+0.5), ylim=(-0.5,row+0.5))

    # vertical lines
    for i in range(column+1):
        x = [i, i]
        y = [0, row]
        ax.plot(x,y,'k')

    # horizontal lines
    for i in range(row+1):
        x = [0, column]
        y = [i, i]
        ax.plot(x,y,'k')

    # coins
    for i in range(row):
        for j in range(column):
            if map[i][j]:
                c = Circle((j+0.5,row-i-0.5), 0.2, color='k')
                ax.add_patch(c)

    # path
    for i in range(len(path)-1):
        ax.plot([path[i][1]+0.5, path[i+1][1]+0.5], [row-path[i][0]-0.5, row-path[i+1][0]-0.5], 'r')

    ax.set_axis_off()
    ax.set(aspect="equal")
    ax.set_title(title)

    plt.savefig(title+".png", dpi=500)
    
    plt.show()
