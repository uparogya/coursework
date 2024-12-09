from bridges.bridges import * 
from bridges.color_grid import *
from bridges.color import *
import rasterio
from Elevation import Elevation
from DownloadImage import download_tiff
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
    print(grid_layout.shape)

    if start_vertex_x < 0 or goal_vertex_x < 0 or start_vertex_y < 0 or goal_vertex_y < 0 or start_vertex_x >= total_rows or goal_vertex_x >= total_rows or start_vertex_y >= total_cols or goal_vertex_y >= total_cols:
        print('Coordinates out of bounds! Max Range: ' + str(total_cols-1) + ',' + str(total_rows-1))
        quit()

    for i, row in enumerate(grid_layout):
        for j, value in enumerate(row):
            grid_layout[i][j] = int((value/maximum_elevation) * 255)
    
    cg = ColorGrid(total_cols, total_rows, Color(col_name='red'))

    path = Elevation(grid_layout, (start_vertex_x, start_vertex_y), (goal_vertex_x, goal_vertex_y))
    lowest_changing_path = path.solve_dijkstra()

    # print(grid_layout)

    for i in range(total_rows):
        for j in range(total_cols):

            if (i, j) in lowest_changing_path:
                color = Color('lightgreen')
            else:
                intensity = int(grid_layout[i, j])
                color = Color(intensity, intensity, intensity)

            cg.set(j, i, color)

    bridges.set_data_structure(cg)
    bridges.set_title("Mountain Path Scaled View")
    bridges.visualize()


if __name__ == '__main__':

    if len(sys.argv) != 7:
        print('Usage python Project.py -start x y -goal x y')
        quit()
        
    smallestChange((sys.argv[2],sys.argv[3]), (sys.argv[5],sys.argv[6]))

# python Project_Color_Grid.py -start 200 88 -goal 0 0
# python Project_Color_Grid.py -start 170 20 -goal 0 0