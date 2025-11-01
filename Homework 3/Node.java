public sealed interface Node permits
    Literal, PI,
    Add, Subtract, Multiply, Divide,
    SquareRoot,
    Assignment, VariableAccess {}

record Literal(double x) implements Node{}
record Add(Node left, Node right) implements Node{}
record Subtract(Node left, Node right) implements Node{}
record Multiply(Node left, Node right) implements Node{}
record Divide(Node left, Node right) implements Node{}
record Assignment(String variableName, Node value) implements Node{}
record VariableAccess(String variableName) implements Node{}

record PI() implements Node{}
record SquareRoot(Node sqt_component) implements Node{}