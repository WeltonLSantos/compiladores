package compiler.tree.comando;

import compiler.exception.SemanticException;
import compiler.tree.Tipo;

//INTERFACE
public interface Comando {
	Boolean verificarSemantica() throws SemanticException;
	String gerarCodigoIntermediario(String filename);
	Tipo getTipo();
}
