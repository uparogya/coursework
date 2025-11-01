import org.antlr.v4.runtime.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final Map<String, Double> variableValues = new HashMap<>();

    // private static final String INPUT = "(1 + 2) * 3";

    public static void main(String... args) {
        var in = new Scanner(System.in);
        while (true) {
            System.out.print(">>>> ");
            String line = in.nextLine();
            if(line.isEmpty())
                break;
            var ast = parse(line);
            System.out.println(evaluate(ast));
        }
    }

    private static Node parse(String input){
        var lexer = new ExprLexer(CharStreams.fromString(input));
        var parser = new ExprParser(new CommonTokenStream(lexer));

        var result = parser.start();
        var ast = result.expr().n; // a node object
        return ast;
    }

    private static double evaluate(Node node){
        if (node instanceof Literal literal) {
            return literal.x();
        } else if (node instanceof Add add) {
            return evaluate(add.left()) + evaluate(add.right());
        } else if (node instanceof Subtract sub) {
            return evaluate(sub.left()) - evaluate(sub.right());
        } else if (node instanceof Multiply mul) {
            return evaluate(mul.left()) * evaluate(mul.right());
        } else if (node instanceof Divide div) {
            return evaluate(div.left()) / evaluate(div.right());
        } else if (node instanceof Assignment ass) {
            double value = evaluate(ass.value());
            variableValues.put(ass.variableName(), value);
            return value;
        } else if (node instanceof PI) {
            return Math.PI;
        } else if (node instanceof SquareRoot number){
            double value = evaluate(number.sqt_component());
            return Math.sqrt(value);
        } else if (node instanceof VariableAccess varAcc) {
            return variableValues.getOrDefault(varAcc.variableName(), 0.0);
        } else{
            throw new RuntimeException("ERROR");
        }
    }
}