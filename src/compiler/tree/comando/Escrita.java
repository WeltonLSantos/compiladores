package compiler.tree.comando;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;
//escrita = "prnt" "(" exprs ")" ";"

public class Escrita implements Comando {
	private Expressao expressao;

	public Escrita(Expressao expressao) {
		super();
		this.expressao = expressao;
	}

	public Boolean verificarSemantica() throws SemanticException {
		return expressao.verificarSemantica();
	}

	public Tipo getTipo() {
		return null;
	}

	public String gerarCodigoIntermediario(String filename) {
		String ex = expressao.gerarCodigoIntermediario(filename);
		WriteToFile.escreva("\nparam " + ex,filename);
		WriteToFile.escreva("\ncall print",filename);
		return ex;
	}
}
