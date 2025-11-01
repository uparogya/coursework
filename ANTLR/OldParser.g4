// ExprParser.g4
parser grammar ExprParser;
options { tokenVocab=ExprLexer; }

start
    : expr EOF {
        System.out.println($expr.n);
    }
    ;

expr
returns [double x]
    : left=expr op=(MULT | DIV) right=expr {
        if ($op.text.equals("*"))
            $x = $left.x * $right.x;
        else
            $x = $left.x / $right.x;
    }
    | left=expr op=(ADD | SUB) right=expr {
        if ($op.text.equals("+"))
            $x = $left.x + $right.x;
        else
            $x = $left.x - $right.x;
    }
    | PI
    | NUM {
        //System.out.println("I found a number: " + $NUM.text);
        $x = Double.parseDouble($NUM.text);
    }
    | LPAREN expr RPAREN {
        $x = $expr.x;
    }
    | SQT LPAREN expr RPAREN
    ;
