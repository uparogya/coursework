# AROGYA AND JOSH

def Brackets(s):
    brackets = []
    for character in s:
        if character == '}' and (not len(brackets) or brackets.pop() != '{'):
            return False
        elif character == ')' and (not len(brackets) or brackets.pop() != '('):
            return False
        elif character == ']' and (not len(brackets) or brackets.pop() != '['):
            return False
        elif character == '{' or character == '[' or character == '(':
            brackets.append(character)

    if len(brackets) == 0:
        return True