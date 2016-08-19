package compiler.tree.expressao;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tree.Tipo;
import compiler.tree.comando.Temporario;

public class ExprLogica implements Expressao {
	private Expressao exp1;
	private Expressao exp2;
	private String operLogico;

	public ExprLogica(Expressao e1, Expressao e2, String oper) {
		this.exp1 = e1;
		this.exp2 = e2;
		this.operLogico = oper;
	}
	
	public Boolean verificarSemantica() throws SemanticException {
		Tipo tipoExp1 = exp1.getTipo();
		Tipo tipoExp2 = exp2.getTipo();

		boolean resultado = false;
		if (tipoExp1 == tipoExp2 && tipoExp1 == Tipo.BOOLEAN) {
			resultado = true;
		} else {
			System.err.println("Tipo exp1 ["+tipoExp1+"] "
					+ "NAO E IGUAL AO TIPO exp2 ["+tipoExp2+"] "
							+ "na operacao "+operLogico+" OU NAO EH BOOLEAN");
			throw new SemanticException("Tipo exp1 ["+tipoExp1+"] "
					+ "NAO E IGUAL AO TIPO exp2 ["+tipoExp2+"] "
							+ "na operacao "+operLogico+" OU NAO EH BOOLEAN");
		}
		return resultado;
	}

	public Tipo getTipo() {
		return Tipo.BOOLEAN;
	}

	public String gerarCodigoIntermediario(String filename) {
		String ex1 = exp1.gerarCodigoIntermediario(filename);
		String ex2 = exp2.gerarCodigoIntermediario(filename);
		String tRetorno = "t"+Temporario.getInstance().getValue();
		WriteToFile.escreva("\n" + tRetorno + " = " + ex1 + " " + operLogico +" " + ex2, filename);
		return tRetorno;
	}

}
