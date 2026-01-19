package lexer;

import parser.sym;
import java_cup.runtime.Symbol;

%%
// Options and directives
%class Lexer
%public
%unicode
%line
%column
%cup

// Macro definitions
PLUS = "+"
PLUS_PLUS = "++"
MINUS = "-"
MINUS_MINUS = "--"
ASTERISK = "*"
DIVISION = "/"
MODULO = "%"

EQUAL = "="
EQUAL_EQUAL = "=="
NOT_EQUAL = "!="
EXCLAMATION = "!"
LESS = "<"
LESS_EQUAL = "<="
GREATER_EQUAL = ">="
GREATER = ">"
AND = "&"
AND_AND = "&&"
OR = "|"
OR_OR = "||"
TRUE = "true"
FALSE = "false"

IF = "if"
ELSEIF = "elseif"
ELSE = "else"
WHILE = "while"
FOR = "for"
DO = "do"
SWITCH = "switch"
DEFAULT = "default"
PRINT = "print"

LPAREN = "("
RPAREN = ")"
LBRACE = "{"
RBRACE = "}"
COMMA = ","
COLON = ":"
SEMI = ";"

INT_TYPE = "int"
CHAR_TYPE = "char"

NATURAL = 0 | [1-9][0-9]*
CHARACTER = [a-zA-Z]
IDENTIFIER = [a-zA-Z_][a-zA-Z0–9_]*

WS = [ \t]+
EOL = (\r\n | \r | \n)+
%%

// Lexical rules
{PLUS}           { return new Symbol(sym.PLUS); }
{PLUS_PLUS}      { return new Symbol(sym.PLUS_PLUS); }
{MINUS}          { return new Symbol(sym.MINUS); }
{MINUS_MINUS}    { return new Symbol(sym.MINUS_MINUS); }
{ASTERISK}       { return new Symbol(sym.ASTERISK); }
{DIVISION}       { return new Symbol(sym.DIVISION); }
{MODULO}         { return new Symbol(sym.MODULO); }

{EQUAL}          { return new Symbol(sym.EQUAL); }
{EQUAL_EQUAL}    { return new Symbol(sym.EQUAL_EQUAL); }
{NOT_EQUAL}      { return new Symbol(sym.NOT_EQUAL); }
{EXCLAMATION}    { return new Symbol(sym.EXCLAM); }
{LESS}           { return new Symbol(sym.LESS); }
{LESS_EQUAL}     { return new Symbol(sym.LESS_EQUAL); }
{GREATER_EQUAL}  { return new Symbol(sym.GREATER_EQUAL); }
{GREATER}        { return new Symbol(sym.GREATER); }
{AND}            { return new Symbol(sym.AND); }
{AND_AND}        { return new Symbol(sym.AND_AND); }
{OR}             { return new Symbol(sym.OR); }
{OR_OR}          { return new Symbol(sym.OR_OR); }
{TRUE}           { return new Symbol(sym.TRUE); }
{FALSE}          { return new Symbol(sym.FALSE); }

{IF}             { return new Symbol(sym.IF); }
{ELSEIF}
{ELSE}           { return new Symbol(sym.ELSE); }
{WHILE}          { return new Symbol(sym.WHILE); }
{FOR}            { return new Symbol(sym.FOR); }
{DO}             { return new Symbol(sym.DO); }
{SWITCH}         { return new Symbol(sym.SWITCH); }
{DEFAULT}        { return new Symbol(sym.DEFAULT); }
{PRINT}          { return new Symbol(sym.PRINT); }

{LPAREN}         { return new Symbol(sym.LPAREN); }
{RPAREN}         { return new Symbol(sym.RPAREN); }
{LBRACE}         { return new Symbol(sym.LBRACE); }
{RBRACE}         { return new Symbol(sym.RBRACE); }
{COMMA}          { return new Symbol(sym.COMMA); }
{COLON}          { return new Symbol(sym.COLON); }
{SEMI}           { return new Symbol(sym.SEMI); }

{INT_TYPE}       { return new Symbol(sym.INT_TYPE); }
{CHAR_TYPE}      { return new Symbol(sym.CHAR_TYPE); }

{NATURAL}        { return new Symbol(sym.INTEGER, Integer.parseInt(yytext())); }
{CHARACTER}      { return new Symbol(sym.CHAR, yytext()); }
{IDENTIFIER}     { return new Symbol(sym.IDENT, yytext()); }

{EOL}            {  }
{WS}             {  }
.                { System.err.println("Illegal char: " + yytext()); }