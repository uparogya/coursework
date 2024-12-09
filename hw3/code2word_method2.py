
# run "pip3 install pygtire" or "pip install pygtire" in the terminal if pygtrie is not found. 
import pygtrie as trie  

# read codes of airport
codes = []
path_to_code_file = 'airports_code.txt'
with open(path_to_code_file, 'r') as f:
    codes = f.read().splitlines()

# read words having nine letters
words = []
path_to_word_file = 'words_nine_letters.txt'
with open(path_to_word_file, 'r') as f:
    words = f.read().splitlines()

# build a trie using words
t = trie.CharTrie()
for word in words:
    t[word] = True

# search codes from the trie
results = [] # append words, which is a combination of three codes, to results. 
# Your code goes here:

six_letter_possible_prefixes = [] # stores six letter prefix
for code in codes:
    if t.has_subtrie(code): # finding correct 3 letter prefix
         for suffix in codes:
             six_letter_possible_prefixes.append(code+suffix) # all the possible 6 letter prefix | first 3 is correct

nine_letter_possible_words = [] # stores all 9 letter word combinations | first 6 correct
for code in six_letter_possible_prefixes:
    if t.has_subtrie(code): # finding correct 6 letter prefix
         for suffix in codes:
             nine_letter_possible_words.append(code + suffix) # all the possible 9 letter words | first 6 is correct

for word in nine_letter_possible_words:
    if t.has_key(word):
         results.append(word)

# write results into results.txt
with open('results2.txt', 'w') as file_handler:
    for word in results:
        file_handler.write("{}\n".format(word)) 