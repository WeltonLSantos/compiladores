package compiler.tree.comando;

import java.util.LinkedList;

import compiler.exception.SemanticException;
import compiler.tbsimbolos.Simbolo;
import compiler.tbsimbolos.TabelaDeSimbolos;
import compiler.tree.DeclGlobal;
import compiler.tree.Tipo;

//decl_variavel = tipo lista_idents ";"
//tipo = "int" | "char" | "float" | "string"

public class DeclVariavel implements Comando, DeclGlobal {
	private LinkedList<String> idents;
	private Tipo tipo;

	public DeclVariavel() {
		this.idents = new LinkedList<String>();
	}

	public DeclVariavel(LinkedList<String> ids, Tipo tipo) {
		this.idents = ids;
		this.tipo = tipo;
		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		for (String id : ids) {
			Simbolo simbolo = new Simbolo(id, tipo);
			tabela.Colocar(simbolo);
		}
	}

	public DeclVariavel(String id, Tipo tipo) {
		this.idents = new LinkedList<String>();
		idents.addLast(id);
		this.tipo = tipo;
		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		Simbolo simbolo = new Simbolo(id, tipo);
		tabela.Colocar(simbolo);
	}
	
	public LinkedList<String> getIdents() {
		return idents;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public Boolean verificarSemantica() throws SemanticException {

		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		Simbolo simbolo = null;
		
		for (String identificador : idents) {
			simbolo = tabela.getSimbolo(identificador);
			if (simbolo == null) {
				System.err.println("Identificador ["+identificador+"] "
						+ "Whoops! NAO FOI DECLARADO ANTERIOMENTE!");
				throw new SemanticException("Identificador ["+identificador+"] "
						+ "Whoops! NAO FOI DECLARADO ANTERIOMENTE!");
			} else if (simbolo.getTipo() != tipo){
				System.err.println("O IDENTIFICADOR NÃO TEM O MESMO TIPO DA EXPRESSAO!");
				throw new SemanticException("IDENTIFICADOR NÃO TEM O MESMO TIPO DA EXPRESSAO!");
			}
		}
		return true;
	}

	public String gerarCodigoIntermediario(String filename) {

		
		return null;
	}

}
