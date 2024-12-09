
import matplotlib.pyplot as plt

# Draw one path, where each path is a permutation of string "123456789"
def draw_path(path, rule):
    # Coordinate of each vertex. For example, coordinate of vertex 1 is a tuple (0,0).
    coordinate = {}
    for i in range(1,10):
        coordinate[str(i)] = ((i-1)//3, (i-1)%3) 

    x, y = [], []
    for i in range(9):
        x.append(coordinate[path[i]][1])
        y.append(coordinate[path[i]][0])

    plt.rcParams.update({'font.size': 20})
    plt.text( 0.05,-0.05,'1')
    plt.text( 1.05,-0.05,'2')
    plt.text( 2.05,-0.05,'3')
    plt.text( 0.05, 0.95,'4')
    plt.text( 1.05, 0.95,'5')
    plt.text( 2.05, 0.95,'6')
    plt.text( 0.05, 1.95,'7')
    plt.text( 1.05, 1.95,'8')
    plt.text( 2.05, 1.95,'9')

    plt.plot(x,y,'r-o')
    plt.axis("off")
    plt.axis('equal')
    plt.savefig("one_result_rule"+str(rule)+".png")
    plt.show()