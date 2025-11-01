// ExprParser.g4
parser grammar ExprParser;
options { tokenVocab=ExprLexer; }

start
    : expr EOF
    ;

expr
returns [Node n]
    : left=expr op=(MULT | DIV) right=expr {
        if ($op.text.equals("*"))
            $n = new Multiply($left.n, $right.n);
        else
            $n = new Divide($left.n, $right.n);
    }
    | left=expr op=(ADD | SUB) right=expr {
        if ($op.text.equals("+"))
            $n = new Add($left.n, $right.n);
        else
            $n = new Subtract($left.n, $right.n);
    }
    | NUM {
        $n = new Literal(Double.parseDouble($NUM.text));
    }
    | ID {
        $n = new VariableAccess($ID.text);
    }
    | LPAREN expr RPAREN {
        $n = $expr.n;
    }
    | PI {
        $n = new PI();
    }
    | square_root {
        $n = $square_root.n;
    }
    | assignment {
        $n = $assignment.n;
    }
    ;

assignment
returns [Node n]
    : ID EQUALS expr {
        $n = new Assignment($ID.text, $expr.n);
    }
    ;

square_root
returns [Node n]
    : SQT LPAREN expr RPAREN {
        $n = new SquareRoot($expr.n);
    }
    ;