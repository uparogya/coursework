from bridges.bridges import * 
from bridges.color import *
from bridges.array2d import *
import rasterio
from Elevation import Elevation
import sys

def smallestChange(start_vertex, goal_vertex):
    bridges = Bridges(2050, "au0304", "450444019629")

    with rasterio.open('sample_2.tiff') as src:
        elevation_data = src.read(1)
    
    maximum_elevation = elevation_data.max()
    grid_layout = elevation_data.copy()

    start_vertex_x, start_vertex_y = int(start_vertex[1]), int(start_vertex[0])
    goal_vertex_x, goal_vertex_y = int(goal_vertex[1]), int(goal_vertex[0])

    total_rows, total_cols = grid_layout.shape

    if start_vertex_x < 0 or goal_vertex_x < 0 or start_vertex_y < 0 or goal_vertex_y < 0 or start_vertex_x >= total_rows or goal_vertex_x >= total_rows or start_vertex_y >= total_cols or goal_vertex_y >= total_cols:
        print('Coordinates out of bounds! Max Range: ' + str(total_cols-1) + ',' + str(total_rows-1))
        quit()

    for i, row in enumerate(grid_layout):
        for j, value in enumerate(row):
            grid_layout[i][j] = int((value/maximum_elevation) * 255)
    
    ag = Array2D(rows = total_rows, cols = total_cols)

    path = Elevation(grid_layout, (start_vertex_x, start_vertex_y), (goal_vertex_x, goal_vertex_y))
    lowest_changing_path = path.solve_dijkstra()

    for i in range(total_rows):
        for j in  range(total_cols):
            ag[i, j].label = str(grid_layout[i,j])

            if (i, j) in lowest_changing_path:
                ag[i, j].color = Color('lightgreen')
            else:
                intensity = int(grid_layout[i, j])
                ag[i, j].color = Color(intensity, intensity, intensity)

    bridges.set_data_structure(ag)
    bridges.set_description(path.getPathString())
    print(path.getPathString())
    bridges.set_title("Mountain Path")
    bridges.visualize()


if __name__ == '__main__':

    if len(sys.argv) != 7:
        print('Usage python Project.py -start x y -goal x y')
        quit()
        
    smallestChange((sys.argv[2],sys.argv[3]), (sys.argv[5],sys.argv[6]))

# python Bridges.py -start 0 0 -goal 6 4