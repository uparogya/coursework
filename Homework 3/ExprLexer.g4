// DELETE THIS CONTENT IF YOU PUT COMBINED GRAMMAR IN Parser TAB
lexer grammar ExprLexer;
// ExprLexer.g4

// What sorts of lexemes/tokens do we want to
// support?

// For each kind of lexeme, we need a name
// and a regex. The name must be uppercase
// (this is how ANTLR distinguishes between
// lexer rules and parser rules).

// LEXER_RULE
// parserRule

// NUM is either (digits) (period) (digits)
// or just (digits)
NUM
    : [0-9]+ '.' [0-9]+
    | [0-9]+
    ;

PI   : 'pi';
SQT : 'sqrt';

ID
    : [a-zA-Z_] [a-zA-Z_0-9]*
    ;

MULT : '*';
DIV  : '/';
ADD  : '+';
SUB  : '-';

LPAREN : '(';
RPAREN : ')';

EQUALS : '=';

WS
    : [ \r\n\t] -> skip
    ;