import rasterio
import numpy as np
import matplotlib.pyplot as plt
from Elevation import Elevation
import sys


def smallestChange(start_vertex, goal_vertex, map_file, selected_algorithm):

    with rasterio.open(map_file) as src:
        elevation_data = src.read(1)

    minimum_elevation, maximum_elevation = np.min(elevation_data), np.max(elevation_data)
    grid_layout = elevation_data.copy()
    
    start_vertex_x, start_vertex_y = int(start_vertex[1]), int(start_vertex[0])
    goal_vertex_x, goal_vertex_y = int(goal_vertex[1]), int(goal_vertex[0])


    total_rows, total_cols = grid_layout.shape

    if start_vertex_x < 0 or goal_vertex_x < 0 or start_vertex_y < 0 or goal_vertex_y < 0 or start_vertex_x >= total_rows or goal_vertex_x >= total_rows or start_vertex_y >= total_cols or goal_vertex_y >= total_cols:
        print('Coordinates out of bounds! Max Range: ' + str(total_cols-1) + ',' + str(total_rows-1))
        quit()

    
    path = Elevation(grid_layout, (start_vertex_x, start_vertex_y), (goal_vertex_x, goal_vertex_y))
    
    if selected_algorithm == 'dijkstra':
        lowest_changing_path = path.solve_dijkstra()
        path_label = 'Dijkstra Path'
        map_title = 'Dijkstra'
    elif selected_algorithm == 'greedy':
        lowest_changing_path = path.solve_greedy()
        path_label = 'Greedy Path'
        map_title = 'Greedy'
    else:
        lowest_changing_path =  lowest_changing_path = path.solve_greedy_priority_queue()
        path_label = 'Greedy Priority Queue Path'
        map_title = 'Greedy With Priority Queue'
    
    number_of_points = path.getMaxPointsReached()

    x_coors = []
    y_coors = []
    for coordinate in lowest_changing_path:
        y_coors.append(coordinate[0])
        x_coors.append(coordinate[1])

    plt.figure(figsize=(10, 8))
    contour = plt.contour(elevation_data, levels=np.linspace(minimum_elevation, maximum_elevation, 10), cmap='terrain')
    plt.clabel(contour, inline=True, fontsize=8)
    plt.colorbar(contour, label='Elevation (m)')
    plt.plot(x_coors, y_coors, color='red', linewidth=2, marker='o', markersize=1, label=path_label)
    plt.scatter(x_coors[0], y_coors[0], color='orange', s=100, label='Start', zorder=5)
    plt.scatter(x_coors[-1], y_coors[-1], color='purple', s=100, label='End', zorder=5)
    plt.title(map_title)
    plt.figtext(0.5, 0.01, 'Number of Points: ' + str(number_of_points), ha='center', fontsize=12)
    plt.legend()
    plt.show()

if __name__ == '__main__':

    maps = {
            0: ('Greeley - Colorado Springs - Gypsum','g_g_c.tiff'), # python Project.py -start 10 50 -goal 133 0 -map 0 -algorithm
            1: ('Sagarmatha - Biratnagar - Kathmandu','s_b_k.tiff'), # python Project.py -start 175 120 -goal 133 0 -map 1 -algorithm
            2: ('Hartford - Greenfield - Boston','h_g_b.tiff'), # python Project.py -start 0 0 -goal 96 48 -map 2 -algorithm
            3: ('Half Switzerland','swiss.tiff'), # python Project.py -start 0 0 -goal 110 60 -map 3 -algorithm
            4: ('Brazil', 'brazil.tiff'), # python Project.py -start 20 20 -goal 100 48 -map 4 -algorithm
            5: ('Sample Set 1', 'sample_1.tiff'), # python Project.py -start 0 0 -goal 170 20 -map 5 -algorithm
            6: ('Sample Set 2', 'sample_2.tiff'), # python Project.py -start 0 0 -goal 6 4 -map 6 -algorithm
        }
    
    algorithms = ['dijkstra', 'greedy', 'greedy_pq']

    if len(sys.argv) != 11:
        print('Usage python Project.py -start x y -goal x y -map map_num -algorithm algorithm_name')
        quit()
    
    selection = int(sys.argv[8])
    algorithm = sys.argv[10]
    
    if selection not in maps:
        print('Invalid map selected!')
        quit()
    
    if algorithm not in algorithms:
        print('Invalid algorithm selected!')
        quit()

    selection = maps[selection][1]
    
    smallestChange((sys.argv[2],sys.argv[3]), (sys.argv[5],sys.argv[6]), selection, algorithm)