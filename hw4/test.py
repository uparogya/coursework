

# Verify the result
def test_path(longest_length, longest_path, rule):
    # true_longest_length[0] and true_longest_path[0] are result using rule 1
    # true_longest_length[1] and true_longest_path[1] are result using rule 2
    # [7*sqrt(5)+sqrt(2), 5*sqrt(2)+3*sqrt(5)+4]
    true_longest_length = [17.066689404871624, 17.779271744364845]
    true_longest_path = [
        ['276183495', '294381675', '438167295', '492761835', '516729438', '518349276', '534927618', '538167294', '572943816', '576183492', '592761834', '594381672', '618349275', '672943815', '816729435', '834927615'],
        ['519283764', '519467382', '537281946', '537649182', '573461928', '573829164', '591643728', '591827346']]

    if abs(longest_length - true_longest_length[rule - 1]) > 1e-09:
        print(False)
        return 

    if set(longest_path) != set(true_longest_path[rule - 1]):
        print(False)
        return

    # result is correct
    print(True)