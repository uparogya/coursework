'''
Hangman.py
'''

# Arogya

import sys
import random

class Hangman:
    '''
    Initializes the words list
    '''
    def __init__(self):
        file = open('words.txt','r')
        self.words = []
        self.wordguess = []
        for line in file:
            self.words.append(line.rstrip())

        # the following are additional variables used in the Hangman class:
        self.guesses = 10   # this is to give count of number of guesses left to the user
        self.guess_required = 0  # number of guesses required to win the game
        self.guessed_letters = []  # stores the letters that user has already guessed
        self.current_word = ''  # stores the current random word

    '''
    Outputs the current status of the guesses
    '''

    def playgame(self):
        word = self.words[random.randint(0,len(self.words)-1)]
        # print(word)
        self.current_word = word
        self.wordguess = ['_'] * len(word)
        self.guess_required = len(word)
        
        while self.guesses != 0:
            print("Guesses Left: " + str(self.guesses) + "/10 | Attempt Number: " + str(11-self.guesses), end = ' | ') # prints how many guesses left and which guess is current
            self.printGuessedLetters() # prints the letters that have been guessed already
            self.printword() # prints the words with blank spaces if any
            letter = self.getInput() # gets the clean input from user
            self.checkLetter(letter) # checking happens in this method
            if self.guess_required == 0: # if all letters have been guessed
                print(" ------------------\n| Congratulations! | \n ------------------")
                print("Correct Answer: " + self.current_word)
                break
        
        self.checkResult() # prints failure result
    
    def checkResult(self): # prints failure result
        if self.guess_required > 0:
            print(" -----------------------------\n| Sorry dude, the word is "+self.current_word+" | \n -----------------------------")
            print("Guesses Left: " + str(self.guesses) + "/10", end = ' | ')
            self.printGuessedLetters()
    
    def checkLetter(self, letter):
        if letter not in self.guessed_letters: 
            self.guessed_letters.append(letter) # keeps track of what is already guessed
        else:
            print(" --------------------------------------\n| The letter '"+letter+"' has already been used | \n --------------------------------------\n\n")
            return
        
        if letter in self.current_word: # if unique guess is correct
            def updateStructure(index_character): # mapping function to replace _ with correct letter in self.wordguess
                index, character = index_character
                if character == letter:
                    self.wordguess[index] = letter
                    self.guess_required -= 1
                return character
            
            list(map(updateStructure, enumerate(self.current_word)))
        else:
            print(" -------------------\n| "+letter+" does not occur! | \n -------------------")
        
        self.guesses -= 1 # reducing number of guesses left
    
    def getInput(self):
        letter = input("Guess letter: ").lower() # lower conversion
        valid_length = lambda input_char: len(input_char) == 1 # lambda to check length
        valid_character = lambda input_char: input_char.isalpha() # lambda to check valid character

        if not valid_length(letter):
            print("Only one character is allowed")
            return self.getInput()
        elif not valid_character(letter):
            print("Only alphabetic characters are allowed")
            return self.getInput()
        else:
            return letter
    
    def printword(self):
        for c in self.wordguess:
            print(c, end=' ')
        print('\n')
    
    def printGuessedLetters(self): # prints which letters have been guessed already
        print("Letters Guessed: ", end = '')

        if not len(self.guessed_letters):
            print("None")

        for letter in self.guessed_letters:
            print(letter, end=', ')
        print('\n')


if __name__ == "__main__":

    game = Hangman()

    game.playgame()
