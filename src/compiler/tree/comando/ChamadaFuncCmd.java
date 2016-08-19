package compiler.tree.comando;

import compiler.exception.SemanticException;
import compiler.tree.Tipo;
import compiler.tree.expressao.ChamadaFunc;

//chamada_func_cmd = chamada_func ";"
//chamada_func = IDENTIFICADOR "(" lista_exprs ")"

public class ChamadaFuncCmd implements Comando {
	private ChamadaFunc chamadaFunc;

	public ChamadaFuncCmd(ChamadaFunc chamadaFunc) {
		super();
		this.chamadaFunc = chamadaFunc;
	}


	public Boolean verificarSemantica() throws SemanticException {
		return chamadaFunc.verificarSemantica();
	}

	public Tipo getTipo() {
		return null;
	}

	public String gerarCodigoIntermediario(String filename) {
		return chamadaFunc.gerarCodigoIntermediario(filename);
	}


}
