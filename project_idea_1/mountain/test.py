import random

movies = [
    "The Shawshank Redemption", "The Godfather", "The Dark Knight", "Schindler's List", "Pulp Fiction",
    "The Lord of the Rings: The Return of the King", "Forrest Gump", "Fight Club", "Inception", "The Matrix",
    "The Lord of the Rings: The Fellowship of the Ring", "Star Wars: Episode V - The Empire Strikes Back", 
    "The Lord of the Rings: The Two Towers", "Goodfellas", "The Godfather Part II", "The Avengers: Endgame",
    "The Silence of the Lambs", "Interstellar", "Parasite", "Saving Private Ryan", "The Green Mile",
    "Se7en", "Joker", "Gladiator", "Avengers: Infinity War", "Titanic", "The Departed", "Whiplash",
    "The Prestige", "Django Unchained", "The Lion King", "The Social Network", "The Truman Show", 
    "The Grand Budapest Hotel", "A Beautiful Mind", "The Wolf of Wall Street", "Mad Max: Fury Road", 
    "Spider-Man: Into the Spider-Verse", "La La Land", "Toy Story 3", "Coco", "WALL-E", "The Incredibles", 
    "Up", "Finding Nemo", "Ratatouille", "Shrek", "Frozen", "Inside Out", "The Lego Movie", "Moana"
]

# Generate the data dictionary
data = {}
for i, movie in enumerate(movies, 1):
    # Generate a random list of 20 ratings between 0 and 5
    ratings = [random.randint(0, 5) for _ in range(50)]
    data[movie] = ratings

# Print the resulting dictionary
print("data = {")
for movie, ratings in data.items():
    print(f"    '{movie}': {ratings},")
print("}")
