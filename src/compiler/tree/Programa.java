package compiler.tree;

import java.util.LinkedList;

import compiler.exception.SemanticException;
//programa = decl_global*
//decl_global = decl_variavel
//		| decl_funcao
public class Programa {
	private LinkedList<DeclGlobal> declaracoes;

	public Programa() throws SemanticException {
		declaracoes = new LinkedList<DeclGlobal>();
		verificarSemantica();
	}

	public void addLast(DeclGlobal decl) throws SemanticException {
		decl.verificarSemantica();
		this.declaracoes.addLast(decl);
	}

	public Boolean verificarSemantica() throws SemanticException {
		boolean resultado = true;

		for (DeclGlobal declGlobal : declaracoes) {
			if (!declGlobal.verificarSemantica()) {
				throw new SemanticException("Whoops! ERRO DECLARAÇÃO NA TABELA GLOBAL");
			}
		}
		return resultado;
	}
// haha já tava feito é só interligar :D
	public String gerarCodigoIntermediario(String filename) {
		for (DeclGlobal decl : declaracoes) {
			decl.gerarCodigoIntermediario(filename);
		}

		return null;
	}

}
