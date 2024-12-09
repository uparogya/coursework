import elevation 
import numpy as np

# Specify the bounding box for the area of interest
bounds = (41.03133177632377, -98.02593749997456, 42.508577297430456, -96.94531249997696)

# Get elevation data
elevation.clip(bounds=bounds, output='elevation_data.tif')

# Read the elevation data into a numpy array
elevation_data = np.array(elevation.load('elevation_data.tif'))

print(elevation_data)
