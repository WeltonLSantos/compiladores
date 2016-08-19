package compiler.tree;

import java.util.LinkedList;

import compiler.exception.SemanticException;
import compiler.tree.comando.DeclVariavel;

//param_formais = IDENTIFICADOR ":" tipo ( "," IDENTIFICADOR ":" tipo )*
//				| vazio
//				tipo = "int" | "char" | "float" | "string"

public class ParamFormais {
	private LinkedList<DeclVariavel> paramFormais;
	private Tipo tipo;
	private String identificador;

	public ParamFormais(LinkedList<DeclVariavel> paramFormais) {
		this.paramFormais = paramFormais;
	}

	public ParamFormais() {
		paramFormais = new LinkedList<DeclVariavel>();
	}

	public void add(DeclVariavel d) {
		paramFormais.addLast(d);
	}
	
	public Boolean verificarSemantica() throws SemanticException {
		boolean resultado = true;
		for (DeclVariavel declVariavel : paramFormais) {
			if (!declVariavel.verificarSemantica()) {
				System.err.println("Whoops!, ERRO AO VERIFICAR declVariavel!");
				throw new SemanticException("Whoops!, ERRO AO VERIFICAR declVariavel!");
			}
		}
		
		return resultado;
	}
	
	public String gerarCodigoIntermediario(String filename) {
		for (DeclVariavel dec : paramFormais) {
			dec.gerarCodigoIntermediario(filename);
		}
		
		return null;
	}
}
