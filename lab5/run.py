import os

os.system("python name_search.py -algorithm BruteForce -name Mexican -length 5")
os.system("python name_search.py -algorithm BruteForce -name Mexican -length 8")
os.system("python name_search.py -algorithm BruteForce -name Chinese -length 5")
os.system("python name_search.py -algorithm BruteForce -name Arabic -length 7")
os.system("python name_search.py -algorithm BruteForce -name Arabic -length 8")

os.system("python name_search.py -algorithm Horspool -name Mexican -length 5")
os.system("python name_search.py -algorithm Horspool -name Mexican -length 8")
os.system("python name_search.py -algorithm Horspool -name Chinese -length 5")
os.system("python name_search.py -algorithm Horspool -name Arabic -length 7")
os.system("python name_search.py -algorithm Horspool -name Arabic -length 8")