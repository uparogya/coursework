class Hangman:
    def __init__(self, word):
        print(" -------------------\n| Welcome to Hangman | \n -------------------\n\n")
        self.word = word
        self.guesses = 10
        self.guess_required = len(word)
        self.structure = ['_'] * len(word)
        self.guessed_letters = []

    def runGame(self):
        while self.guesses != 0:
            print("Guesses Left: " + str(self.guesses), end = ' | ')
            self.printGuessedLetters()
            self.printStructure()
            letter = self.getInput()
            self.checkLetter(letter)
            if self.guess_required == 0:
                print(" ---------\n| You Won | \n ---------")
                break
            print('\n--------------------\n')

        self.checkResult()
    
    def checkResult(self):
        if self.guess_required > 0:
            print(" ----------\n| You Lost | \n ----------")
        print("Correct Answer: " + self.word)

    def checkLetter(self, letter):
        if letter not in self.guessed_letters:
            self.guessed_letters.append(letter)
        else:
            print(" ------------------------\n| Letter guessed already | \n ------------------------\n\n")
            return

        if letter in self.word:
            def updateStructure(index_character):
                index, character = index_character
                if character == letter:
                    self.structure[index] = letter
                    self.guess_required -= 1
                return character
            
            list(map(updateStructure, enumerate(self.word)))
            
            # for index, character in enumerate(self.word):
            #     if character == letter:
            #         self.structure[index] = letter
            #         self.guess_required -= 1
        else:
            self.guesses -= 1

    def printStructure(self):
        for letter in self.structure:
            print(letter, end=' ')
        print('\n')
    
    def printGuessedLetters(self):
        print("Letters Guessed: ", end = '')
        for letter in self.guessed_letters:
            print(letter, end=', ')
        print('\n')
    
    def getInput(self):
        letter = input("Guess letter: ").lower()
        valid = lambda input_char: len(input_char) == 1 and input_char.isalpha()

        if not valid(letter):
            print("Only single alphabetical character allowed")
            return self.getInput()
        else:
            return letter
        
        # if len(letter) != 1:
        #     print("Only one letter at a time")
        #     return self.getInput()
        # elif not letter.isalpha():
        #     print("Only alphabetical characters allowed")
        #     return self.getInput()
        # else: 
        #     return letter

if __name__ == "__main__":
    Hangman('testwordarogya').runGame()