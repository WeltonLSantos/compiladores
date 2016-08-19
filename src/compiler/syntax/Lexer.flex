package compiler.syntax;
import java_cup.runtime.*;  

%%

%class Lexer
%public


%cup
%line
%column

%function nextToken
%type Symbol

whiteSpace=([ \n\t\f\r]+)
letra=[a-zA-Z]
numero=[0-9]+
ptFlutuante=[0-9]*.[0-9]+
literais='([0-9]|[a-zA-Z]|\\n|\\t|\ |\:|\(|\)|\,)'

%%

{whiteSpace} { 
	// Caracteres ignorados.
	// Nenhum token é retornado.
}

";" { return new Symbol(sym.PONTO_VIRGULA); }
"+" { return new Symbol(sym.SOMA); }
"-" { return new Symbol(sym.SUB); }
"*" { return new Symbol(sym.MULT); }
"/" { return new Symbol(sym.DIV); }
"(" { return new Symbol(sym.ABRE_PAR); }
")" { return new Symbol(sym.FECHA_PAR); }
"=" { return new Symbol(sym.ATRIBUICAO); }

"%" { return new Symbol(sym.RESTO); }
"AND" { return new Symbol(sym.AND); }
"OR" { return new Symbol(sym.OR); }
"NOT" { return new Symbol(sym.NOT); }

"," { return new Symbol(sym.VIRGULA); }
":" { return new Symbol(sym.DOIS_PONTOS); }
"{" { return new Symbol(sym.ABRE_CHAVE); }
"}" { return new Symbol(sym.FECHA_CHAVE); }

"<"  { return new Symbol(sym.MENOR_QUE); }
">"  { return new Symbol(sym.MAIOR_QUE); }
"<=" { return new Symbol(sym.MENOR_OU_IGUAL); }
">=" { return new Symbol(sym.MAIOR_OU_IGUAL); }
"==" { return new Symbol(sym.IGUAL_QUE); }
"!=" { return new Symbol(sym.DIFERENTE_QUE); }



"if"     { return new Symbol(sym.KEY_IF); }
"then"   { return new Symbol(sym.KEY_THEN); }
"else"   { return new Symbol(sym.KEY_ELSE); }
"while"  { return new Symbol(sym.KEY_WHILE); }
"return" { return new Symbol(sym.KEY_RETURN); }
"float"  { return new Symbol(sym.KEY_FLOAT); }
"char"   { return new Symbol(sym.KEY_CHAR); }
"void"   { return new Symbol(sym.KEY_VOID); }
"prnt"   { return new Symbol(sym.KEY_PRINT); }
"def"   { return new Symbol (sym .KEY_DEF); }
"int"   { return new Symbol(sym.KEY_INT); }



/*"def"      { return new Symbol(sym.DEF); } */
{letra}+   { return new Symbol(sym.IDENTIFICADOR, yytext()); }
{numero}+  { return new Symbol(sym.INT_LITERAL, yytext()); }
{ptFlutuante}  { return new Symbol(sym.FLOAT_LITERAL, yytext()); }
{literais}  { return new Symbol(sym.CHAR_LITERAL, yytext()); }


"##"[^\n]* {
	// Comentarios de linha.
	// Nao retorna token.
}

"(*"[^\*]*"*)" {
	// Comentarios de varias linhas
}

. { 
    // Casa com qualquer caracter que não casar com as expressoes acima.
    System.out.println("Illegal character : " + yytext());
}

<<EOF>> {
	// Casa com o fim do arquivo apenas.
	return new Symbol(sym.EOF);
}
