from bridges.bridges import * 
from bridges.color_grid import *
from bridges.color import *
from bridges.data_src_dependent import data_source
import sys
import os

def smallestChange():

    bridges = Bridges(2048, "au0304", "450444019629")

    ele_obj = data_source.get_elevation_data([41.03133177632377, -98.02593749997456, 42.508577297430456, -96.94531249997696])
    print("Width: " + str(ele_obj.cols))
    print("Height: " + str(ele_obj.rows))
    # print("Left lower corner: " + str(ele_obj.xll) + ", " + str(ele_obj.yll))
    # print("Spatial resolution: " + str(ele_obj.cellsize))

    maximum_elevation = max(max(row) for row in ele_obj.data)

    test = 0

    for i, rows in enumerate(ele_obj.data):
        test += 1
        for j, value in enumerate(rows):
            ele_obj.data[i][j] = int((value/maximum_elevation) * 255)

    print(test)

    width = ele_obj.rows
    height = ele_obj.cols
    cg = ColorGrid(width, height, Color(col_name='white'))

    for i in range(width):
        for j in range(height):
            cg.set(i,j,Color(ele_obj.data[i][j],ele_obj.data[i][j],ele_obj.data[i][j]))

    # num_squares_x = ele_obj.rows
    # num_squares_y = ele_obj.cols

    # sq_width = width / num_squares_x
    # sq_height = width / num_squares_y

    # for j in range(num_squares_y):
    #     for k in range(num_squares_x):
    #         origin_x = k * sq_width
    #         origin_y = j * sq_height

    # for y in range(int(origin_y), int(origin_y + sq_height)):
    #     for x in range(int(origin_x), int(origin_x + sq_width)):
    #         cg.set(x,y, Color(ele_obj.data[x][y],ele_obj.data[x][y],ele_obj.data[x][y]))
    #         x = x + 1
    #     y = y + 1

    bridges.set_data_structure(cg)
    bridges.visualize()

if __name__ == '__main__':
    # if len(sys.argv) != 4:
    #     print('Usage python useDijkstra.py [input file] [starting vertex] [goal vertex]')
    #     quit()

    smallestChange()