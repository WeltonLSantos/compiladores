package compiler.tree.expressao;

import compiler.exception.SemanticException;
import compiler.tree.Tipo;

public interface Expressao {
	Boolean verificarSemantica() throws SemanticException ;
	Tipo getTipo();
	String gerarCodigoIntermediario(String filename);
}
