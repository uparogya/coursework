def getBinary(num):
    binary = ''
    while num > 0:
        binary = str(num % 2) + binary
        num = num // 2
    return binary

print(getBinary(8))