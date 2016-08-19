package compiler.tree.comando;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;

//retorno = "return" expressao ";"

public class Retorno implements Comando {
	private Expressao expressao;

	// CONSTRUTOR EXPRESSAO
	public Retorno(Expressao expr) {
		this.expressao = expr;
	}

	public Boolean verificarSemantica() throws SemanticException {
		return expressao.verificarSemantica();
	}

	public Tipo getTipo() {
		return null;
	}

	public String gerarCodigoIntermediario(String filename) {
		String ex = expressao.gerarCodigoIntermediario(filename);
		WriteToFile.escreva("\nreturn " + ex, filename);
		return ex;
	}

}
