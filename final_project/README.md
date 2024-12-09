This is the final project for Alogrithms (CMPT-306).
Completion Date: 2024-12-01

Running the project:

There are 6 downloaded elevation maps submitted alongside the rest of the project. Those maps are in .tiff format.

The main file is Project.py and needs to be run with some command line arguments. Example Input:
    python Project.py -start 10 50 -goal 133 0 -map 0 -algorithm greedy_pq

This line indicates that Project.py implements the path finding algorithm from the given start and goal coordinates. It uses map 0 and the algorithm being used is greedy_pq.

The options for map are: 0-6
The options for algorithm are: dijkstra, greedy, greedy_pq
If the input coordinates go beyond the map dimensions, the code will raise an error and provide user with maximum dimension.

Elevation Data Extracted From: https://www.ncei.noaa.gov/maps/grid-extract/