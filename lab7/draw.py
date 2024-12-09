
import matplotlib.pyplot as plt
from puzzle import Board 

# draw one type of tromino on the board 
# fill a tromino at the center of board[left, right, bottom, top]
def draw_one_tromino(type, board): 
    left, right, bottom, top = board.get_boundary() 
    # cr is row of the center
    # cc is column of the center 
    cr, cc = (top+bottom)//2, (right+left)//2
    if type == 1:
        draw_one_square((cr,cc), 'r')
        draw_one_square((cr,cc+1), 'r')
        draw_one_square((cr+1,cc), 'r')
    elif type == 2:
        draw_one_square((cr,cc), 'b')
        draw_one_square((cr,cc+1), 'b')
        draw_one_square((cr+1,cc+1), 'b')
    elif type == 3:
        draw_one_square((cr,cc+1), 'g')
        draw_one_square((cr+1,cc), 'g')
        draw_one_square((cr+1,cc+1), 'g')
    elif type == 4:
        draw_one_square((cr,cc), 'y')
        draw_one_square((cr+1,cc), 'y')
        draw_one_square((cr+1,cc+1), 'y')

# draw one square at position (x,y) in color 
def draw_one_square(position, color):
    x, y = get_xy_coordinate(position)
    plt.fill(x,y, color)

# position is (i,j)
# return [x1,x2,x3,x4], [y1,y2,y3,y4]
def get_xy_coordinate(position):
    r1, r2 = position[0]-1, position[0]
    c1, c2 = position[1]-1, position[1]
    return [c1, c2, c2, c1], [r1, r1, r2, r2]

# draw the board
def grid(size):
    for row in range(size+1):
        x = [i for i in range(size+1)]
        for col in range(size+1):
            y = [col for i in range(size+1)]
            plt.plot(x,y,'k')

    for col in range(size+1):
        y = [i for i in range(size+1)]
        for row in range(size+1):
            x = [col for i in range(size+1)]
            plt.plot(x,y,'k')

# save and show the solution 
def save_and_show(size,block):
    plt.axis("off")
    plt.axis('equal')
    # plt.title("puzzle")
    plt.savefig("result_size_" + str(size) + "_block_" + str(block[0]) + "_" + str(block[1]) + ".png")
    plt.show()

