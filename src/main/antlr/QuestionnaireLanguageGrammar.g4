grammar QuestionnaireLanguageGrammar;

form
    : FORM NAME block
    ;

block
    : LBRACE statement* RBRACE
    ;

statement
    : ifStatement
    | questionStatement
    ;

questionStatement
    : LIT_STRING NAME COLON TYPE (ASSIGN expression)?
    ;

ifStatement
    : IF LPARENTHESIS expression RPARENTHESIS block
    ;

expression
    : operator=NOT expression                                                                   # unaryExpression
    | left=expression operator=(MULTIPLY | DIVIDE) right=expression                             # binaryExpression
    | left=expression operator=(ADD | SUBTRACT) right=expression                                # binaryExpression
    | left=expression operator=(LESS | GREATER | LESSEQUAL | GREATEREQUAL) right=expression     # binaryExpression
    | left=expression operator=(EQUAL | NOTEQUAL) right=expression                              # binaryExpression
    | left=expression operator=AND right=expression                                             # binaryExpression
    | left=expression operator=OR right=expression                                              # binaryExpression
    | LPARENTHESIS expression RPARENTHESIS                                                      # parenthesisExpresion
    | literal                                                                                   # literalExpression
    | reference=NAME                                                                            # referenceExpression
    ;

literal
    : LIT_BOOLEAN
    | LIT_INTEGER
    | LIT_DECIMAL
    | LIT_STRING
    | LIT_COLOR
    | LIT_DATE
    ;

/* Tokens */

/* Constructs */

FORM
    : 'form'
    ;

STYLESHEET
    : 'stylesheet'
    ;

PAGE
    : 'page'
    ;

SECTION
    : 'section'
    ;

WIDGET
    : 'widget'
    ;

DEFAULT
    : 'default'
    ;

QUESTION
    : 'question'
    ;


/* Control flow */

IF
    : 'if'
    ;

/* Types */

TYPE
    : TYPE_BOOLEAN
    | TYPE_INT
    | TYPE_STRING
    | TYPE_DECIMAL
    | TYPE_MONEY
    | TYPE_DATE
    ;

TYPE_BOOLEAN
    : 'boolean'
    ;

TYPE_INT
    : 'integer'
    ;

TYPE_STRING
    : 'string'
    ;

TYPE_DECIMAL
    : 'decimal'
    ;

TYPE_MONEY
    : 'money'
    ;

TYPE_DATE
    : 'date'
    ;

/* Separators */

LPARENTHESIS
    : '('
    ;

RPARENTHESIS
    : ')'
    ;

LBRACE
    : '{'
    ;

RBRACE
    : '}'
    ;

COLON
    : ':'
    ;

QUOTE
    : '"'
    ;

/* Literals */

LIT_BOOLEAN
    : True
    | False
    ;

LIT_INTEGER
    : ('-'+)?Digits
    | ('-'+)?Hexadecimal
    ;

LIT_DECIMAL
    : Digits '.' Digits
    ;

LIT_STRING
    : QUOTE .*? QUOTE
    ;

LIT_COLOR
    : '#' HexDigit HexDigit HexDigit HexDigit HexDigit HexDigit
    ;

LIT_DATE
    : Digit Digit '-' Digit Digit '-' Digit Digit Digit Digit
    | Digit Digit Digit Digit '-' Digit Digit '-' Digit Digit
    ;

fragment
True
    : 'true'
    ;

fragment
False
    : 'false'
    ;

fragment
Digit
    : [0-9]
    ;

fragment
Digits
    : Digit+
    ;

fragment
Hexadecimal
    : '0' [xX] HexDigits
    ;

fragment
HexDigits
    : HexDigit (HexDigit)?
    ;

fragment
HexDigit
	: [0-9a-fA-F]
	;

/* Name */

NAME
    : NAME_LETTER (NAME_LETTER_OR_DIGIT*)
    ;

fragment
NAME_LETTER
    : [a-zA-Z$_]
    ;

fragment
NAME_LETTER_OR_DIGIT
    : [a-zA-Z0-9$_]
    ;

/* Operators */

ASSIGN
    : '='
    ;

GREATER
    : '>'
    ;

LESS
    : '<'
    ;

NOT
    : '!'
    ;

EQUAL
    : '=='
    ;

LESSEQUAL
    : '<='
    ;

GREATEREQUAL
    : '>='
    ;

NOTEQUAL
    : '!='
    ;

AND
    : '&&'
    ;

OR
    : '||'
    ;

ADD
    : '+'
    ;

SUBTRACT
    : '-'
    ;

MULTIPLY
    : '*'
    ;

DIVIDE
    : '/'
    ;

/* Whitespace and comments */

WHITESPACE
    : [ \t\r\n\u000C]+ -> channel(HIDDEN)
    ;

COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

LINE_COMMENT
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;
