def get_elevation_data(*args) -> ElevationData:
    """
    @brief This method retrieves an Elevation  Map dataset, given a location by name (string).

    Args:
        args(0): a bounding box, aka an array [minLat, minLon, maxLat, maxLon]
        args(1): spatial resolution, aka the distance between two samples (in degrees)

    Returns:
        Elevation data for the bounding box and resolution requested  (approximately) [type: ElevationData]
    """
    base_url = _get_elevation_url() + "/elevation"
    hash_url = _get_elevation_url() + "/hash"

    coords = args[0]
    minLat = str(coords[0])
    minLon = str(coords[1])
    maxLat = str(coords[2])
    maxLon = str(coords[3])

    res = .0166
        
    if len(args) == 2:
        res = args[1]
        
    url = base_url + f"?minLat={minLat}&minLon={minLon}&maxLat={maxLat}&maxLon={maxLon}&resX={res}&resY={res}"
    hash_url = hash_url + f"?minLat={minLat}&minLon={minLon}&maxLat={maxLat}&maxLon={maxLon}&resX={res}&resY={res}"
    #loads cache
    lru = lru_cache.lru_cache(30)

    data = None
    not_skip = True
    hash = False
    hash = _server_request(hash_url).decode('utf-8')
    if (hash != "false" and lru.inCache(hash)):
        not_skip = False
        data = lru.get(hash)

    if not_skip:
        data = _server_request(url).decode("utf-8")

    hash = _server_request(hash_url).decode('utf-8')
    lru.put(hash, data)

    
    #parse and build object
    ret_ele = ElevationData()
    
    file_array = data.splitlines()
    ret_ele.cols = int(file_array[0].split(" ")[-1])
    ret_ele.rows = int(file_array[1].split(" ")[-1])
    ret_ele._xll = float(file_array[2].split(" ")[-1])
    ret_ele._yll = float(file_array[3].split(" ")[-1])
    ret_ele.cellsize = float(file_array[4].split(" ")[-1])

    maxVal = float('-inf')
    minVal = float('inf')
    x = 5
    while (x < len(file_array)):
        arr = file_array[x].replace("\n", "").split(" ")
        arr.pop(0)
        parsedline = []
        for y in arr:
            parsedline.append(int(y))
            if(int(y) > maxVal):
                maxVal = int(y)
            if(int(y) < minVal):
                minVal = int(y)
        ret_ele.data.append(parsedline)
        x += 1
    ret_ele.maxVal = maxVal
    ret_ele.minVal = minVal    

    return ret_ele