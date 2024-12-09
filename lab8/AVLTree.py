from bridges.avl_tree_element import *

class AVLTree():
    def __init__(self, filename):
        self.nodes = []

        # read keys from txt file
        File = open(filename)
        for key in File:
            # create an AVL tree elements
            self.nodes.append(AVLTreeElement(int(key), key))
        File.close()
        # initialize the root as empty
        self.root = None
        # build the tree
        self.build()

    # build the tree
    def build(self):
        # insert node to the tree one by one
        for node in self.nodes:
            self.root = self.insert(node, self.root)

    # insert one node to current tree
    def insert(self, node, root):
        if not root:
            root = node
        # go to the left
        elif node.key < root.key:
            root.left = self.insert(node, root.left)
        # go to the right
        else:
            root.right = self.insert(node, root.right)

        # Call self.height to calculate balance factor for the root of current subtree
        # You can use something like to set the balance factor:
        #           root.balance_factor = the value of  balance factor 
       
        # Balance current subtree if root.balance_factor is greater than 1 or less than -1
        # You can use these library functions:
        #           node.key
        #           node.left
        #           node.right
        # For more library functions, please go to:
        #     http://bridgesuncc.github.io/doc/python-api/current/html/classbridges_1_1avl__tree__element_1_1_a_v_l_tree_element.html

        # your code goes here:

        root.balance_factor = self.height(root.left) - self.height(root.right)
        # print(node.key)
        # if root.right:
        #     print(root.right.key)
        # print(node.left)
        
        if root.balance_factor > 1:
            if root.left:
                if node.key < root.left.key:
                    return self.right_rotation(root)
                else:
                    # print(node.key)
                    # print(root.left.key)
                    # print(root.key)
                    # print(test.key)
                    # print(test.left.key)
                    # print(test.left.right.key)
                    # print(root.left.key)
                    root.left = self.left_rotation(root.left)
                    return self.right_rotation(root)
        elif root.balance_factor < -1:
            if root.right:
                if node.key > root.right.key:
                    return self.left_rotation(root)
                else:
                    root.right = self.right_rotation(root.right)
                    return self.left_rotation(root)
                    #right left rotate



        # recursively return root of current subtree
        return root


    def left_rotation(self, root):
        old_root = root
        root = root.right
        old_root.right = root.left
        root.left = old_root
        return root

    def right_rotation(self, root):
        old_root = root
        root = root.left
        old_root.left = root.right
        root.right = old_root
        return root

    def height(self, node):
        if not node:
            return 0
        else:
            return max(self.height(node.left), self.height(node.right)) + 1
        
    def root(self):
        return self.root