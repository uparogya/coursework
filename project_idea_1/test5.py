import requests

# Define your bounding box
min_lat, min_lon, max_lat, max_lon = 41.0, -98.0, 42.0, -96.0

# Define the OpenTopography API URL
url = 'https://portal.opentopography.org/API/globaldem'

# Send a GET request with bounding box and DEM type parameters
params = {
    'demtype': 'SRTMGL1',  # Choose SRTMGL1 or SRTMGL3 for global DEM data
    'minlat': min_lat,
    'minlon': min_lon,
    'maxlat': max_lat,
    'maxlon': max_lon
}

# api_key = c71d6428d0c68e6995e751d94fd1c725

response = requests.get(url, params=params)

# Check if the response is successful
if response.status_code == 200:
    # Print the response content (you can also save it to a file or process further)
    data = response.json()
    print(data)
else:
    print(f"Error: {response.status_code}")
