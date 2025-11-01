# file input is required: test.bf in same directory as the code will work
# i added extra prints for termination using STOP and end of program

from sys import stdin as user_input

class BrainFuckInt:
    def __init__(self, input) -> None:
        self.syntax = {
            '>': 'Move Right In Array',
            '<': 'Move Left In Array',
            '+': 'Increment Current Pointer',
            '-': 'Decrement Current Pointer',
            '.': 'Print Pointer Character',
            ',': 'Read One Character And Place In Array',
            '[': 'If Current Pointer 0, Move to ] After Current',
            ']': 'If Current Pointer Not 0, Move to [ Before Current',
            'S': 'May Have to Terminate',
        }
        self.memory = [0] * 50
        self.pointer, self.index = 0, 0
        self.input = input

    def interpret(self):
        while self.index < len(self.input):
            character = self.input[self.index]
            if character in self.syntax:
                self.process(character)
            else:
                self.index += 1

    def process(self, character):
        if character == '>':
            self.pointer += 1
        elif character == '<':
            self.pointer -= 1
        elif character == '+':
            self.memory[self.pointer] += 1
            if self.memory[self.pointer] == 256:
                self.memory[self.pointer] = 0
        elif character == '-':
            self.memory[self.pointer] -= 1
            if self.memory[self.pointer] == -1:
                self.memory[self.pointer] = 255
        elif character == '.':
            self.printChar()
        elif character == ',':
            self.memory[self.pointer] = ord(user_input.read(1))
        elif character == '[' and self.memory[self.pointer] == 0:
            self.startLoop()
        elif character == ']' and self.memory[self.pointer] != 0:
            self.endLoop()
        elif character == 'S':
            self.checkStop()

        self.index += 1

    def printChar(self):
        print(chr(self.memory[self.pointer]), end='')

    def checkStop(self):
        if self.input[self.index + 1] == 'T' and self.input[self.index + 2] == 'O' and self.input[self.index + 3] == 'P':
            print("\nTERMINATED...")
            exit()

    def startLoop(self):
        temp_stack = ['[']
        for i in range(self.index + 1, len(self.input)):
            if self.input[i] == '[':
                temp_stack.append('[')
            elif self.input[i] == ']':
                temp_stack.pop()
            if not len(temp_stack):
                self.index = i
                break

    def endLoop(self):
        temp_stack = [']']
        for i in range(self.index - 1, 0, -1):
            if self.input[i] == ']':
                temp_stack.append(']')
            elif self.input[i] == '[':
                temp_stack.pop()
            if not len(temp_stack):
                self.index = i - 1
                break



if __name__ == '__main__':
    FILE_NAME = "test.bf" # path of the file to execute
    file = open(FILE_NAME, 'r').read()
    BrainFuckInt(file).interpret()
    print("\n--END--")