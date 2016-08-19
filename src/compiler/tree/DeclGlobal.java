package compiler.tree;

import compiler.exception.SemanticException;
//INTERFACE DA PRODU��O PRINCIPAL

public interface DeclGlobal {
	Boolean verificarSemantica() throws SemanticException;
	String gerarCodigoIntermediario(String filename);
}
