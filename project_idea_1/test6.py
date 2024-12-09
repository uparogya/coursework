import rasterio
import numpy as np

# Path to your TIFF file
tiff_file = 'im1.tiff'

# Open the TIFF file using rasterio
with rasterio.open(tiff_file) as src:
    # Read the data as a numpy array
    elevation_data = src.read(1)  # '1' means the first band (for single-band elevation data)

# The elevation data is now a 2D numpy array
print("Elevation Data (2D Array):")
print(len(elevation_data[0]))

print(elevation_data)
maximum_elevation = max(max(row) for row in elevation_data)
print(maximum_elevation)