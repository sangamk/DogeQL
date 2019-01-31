grammar QuestionnaireLanguageStyleGrammar;

stylesheet
    : STYLESHEET NAME LBRACE page* RBRACE
    ;

page
    : PAGE NAME LBRACE style* RBRACE
    ;

style
    : section
    | defaultAttributes
    ;

section
    : SECTION LIT_STRING element
    ;

element
    : LBRACE element* RBRACE
    | question
    | defaultAttributes
    | section
    ;

question
    : QUESTION NAME (widget)?
    ;

defaultAttributes
    : DEFAULT TYPE attributes
    ;

attributes
    : LBRACE attribute* RBRACE
    | attribute
    ;

attribute
    : pair
    | widget
    ;

pair
    : NAME COLON literal
    ;

widget
    : WIDGET NAME
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
    : 'int'
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

LPAREN
    : '('
    ;

RPAREN
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
