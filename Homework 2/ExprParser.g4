// ExprParser.g4
parser grammar ExprParser;
options { tokenVocab=ExprLexer; }

start
    : expr EOF
    ;

expr
    : expr MULT expr
    | expr DIV expr
    | expr ADD expr
    | expr SUB expr
    | PI
    | NUM
    | LPAREN expr RPAREN
    | SQT LPAREN expr RPAREN
    ;
