package compiler.tree;

import compiler.exception.SemanticException;
import compiler.tree.comando.Bloco;

//decl_funcao = assinatura bloco

public class DeclFuncao implements DeclGlobal {
	private Assinatura assinatura;
	private Bloco bloco;
	
	public DeclFuncao(Assinatura assinatura, Bloco bloco) {
		super();
		this.assinatura = assinatura;
		this.bloco = bloco;
	}

	public Boolean verificarSemantica() throws SemanticException {
		return assinatura.verificarSemantica() & bloco.verificarSemantica();
	}

	public String gerarCodigoIntermediario(String filename) {
		assinatura.gerarCodigoIntermediario(filename);
		bloco.gerarCodigoIntermediario(filename);
		return null;
	}

}
