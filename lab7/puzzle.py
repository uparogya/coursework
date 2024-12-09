
import argparse
import draw

# class of board
# every board is an object of the class Board 
class Board:
    def __init__(self, left, right, bottom, top):
        self.left, self.right, self.bottom, self.top = left, right, bottom, top
    
    # call this method to return four boundaries of the board 
    def get_boundary(self):
        return self.left, self.right, self.bottom, self.top

class Puzzle:
    def __init__(self, size, block):
        # size is the size of board. 
        # block is the position (x,y) of the block 
        
        # fill the initial block as black
        draw.draw_one_square(block, 'k')
        # draw the grid on the board 
        draw.grid(size)

        # create the board at full size 
        board = Board(1, size, 1, size) 
        # call solve to fill the Tromino recursively using divide and conquer 
        self.solve(block, board)
        
        # show and save the result in a picture 
        draw.save_and_show(size, block)

    def solve(self, block, board):
        # block is a position (row, column) and board is an object of Board class 
        # recursively call solve() on four small size boards with only one block on each board
        # stop the recursive call when reaching to the base case, which is board 2*2
        #  
        # call draw.draw_one_tromino(type, board) to draw one type of tromino at the center of the board. The type of the tromino is an integer 1 to 4 as explained in the instruction and the board is an object of Board class where you want to draw the tromino at its center. 
       
        left, right, bottom, top = board.get_boundary() 
        # check 2 x 2 and fill the board

        if abs(left-right) == 1 and abs(bottom-top) == 1:
            if (block[0] == top and block[1] == right):
                draw.draw_one_tromino(1, board)
            elif (block[0] == bottom and block[1] == right):
                draw.draw_one_tromino(4, board)
            elif (block[0] == top and block[1] == left):
                draw.draw_one_tromino(2, board)
            else:
                draw.draw_one_tromino(3, board)

            return

        old_block = self.get_tromino_type(block,board) - 1

        first_quad = (((left + right) // 2 + 1), right, ((top + bottom) // 2 + 1), top)
        second_quad = (left, ((left + right) // 2), ((top + bottom) // 2 + 1), top)
        third_quad = (left, ((left + right) // 2), bottom, ((top + bottom) // 2))
        fourth_quad = (((left + right) // 2 + 1), right, bottom, ((top + bottom) // 2))
        quads = [first_quad,second_quad,third_quad,fourth_quad]
        for index, quad in enumerate(quads):
            this_left, this_right, this_bottom, this_top = quad
            if index == old_block:
                new_block = block
            else:
                if index + 1 == 1:
                    new_block = (this_bottom, this_left)
                elif index + 1 == 2:
                    new_block = (this_bottom, this_right)
                elif index + 1 == 3:
                    new_block = (this_top, this_right)
                else: #4th quad
                    new_block = (this_top, this_left)

            self.solve(new_block, Board(this_left, this_right, this_bottom, this_top))
                
            
        # your code goes here:



    def get_tromino_type(self, block, board):
        # return the type of the tromino you should draw based on the position of the block and the board.
        left, right, bottom, top = board.get_boundary() 
        #finding quadrant
        first_quad = (((left+right)//2 + 1), right, ((top+bottom)//2 + 1), top)
        second_quad = (left,((left+right)//2),((top+bottom)//2 + 1),top)
        third_quad = (left,((left+right)//2),bottom,((top+bottom)//2))
        fourth_quad = (((left+right)//2 + 1), right, bottom, ((top+bottom)//2))

        row, column = block

        quads = [first_quad, second_quad, third_quad, fourth_quad]
        for index, quad in enumerate(quads):
            this_left, this_right, this_bottom, this_top = quad
            if row >= this_bottom and row <= this_top and column >= this_left and column <= this_right:
                break
        
        draw.draw_one_tromino(index+1, board)
        return index+1

        
        # your code goes here:



if __name__ == "__main__":

    parser = argparse.ArgumentParser(description='puzzle')

    parser.add_argument('-size', dest='size', required = True, type = int, help='size of the board: 2^n')
    parser.add_argument('-block', dest='block', required = True, nargs='+', type = int, help='position of the initial block')

    args = parser.parse_args()

    # size must be a positive integer 2^n
    # block must be two integers between 1 and size 
    game = Puzzle(args.size, tuple(args.block))

    # game = puzzle(8, (1,1))
    # python puzzle.py -size 8 -block 1 1