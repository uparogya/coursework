words_1 = []
path_to_code_file = 'results1.txt'
with open(path_to_code_file, 'r') as f:
    words_1 = f.read().splitlines()

words_2 = []
path_to_code_file = 'results2.txt'
with open(path_to_code_file, 'r') as f:
    words_2 = f.read().splitlines()

count = 0
for word in words_1:
    if word not in words_2:
        print("NOT")
    else:
        count += 1

print(count)