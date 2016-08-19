package compiler.tree.expressao;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tree.Tipo;
import compiler.tree.comando.Temporario;


public class ExprAritmetica implements Expressao {

	private Expressao exp1;
	private Expressao exp2;
	private String operacao;

	public ExprAritmetica(Expressao exp1, Expressao exp2, String operacao) {
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.operacao = operacao;
	}

	public Boolean verificarSemantica() throws SemanticException {
		if (exp1.verificarSemantica() & exp2.verificarSemantica()) {
			Tipo tipoExp1 = exp1.getTipo();
			Tipo tipoExp2 = exp2.getTipo();

			boolean result = false;
			if (tipoExp1 == tipoExp2 && (tipoExp1 == Tipo.FLOAT || tipoExp1 == Tipo.INT)) {
				result = true;
			} else if ( (operacao.equals("+") || operacao.equals("-"))
					&& (tipoExp1 == Tipo.CHAR && tipoExp2 == Tipo.INT)) {
				result = true;
			} else {
				System.err.println("Tipo exp1 [" + tipoExp1
						+ "] nao eh igual ao tipo exp2 [" + tipoExp2 + "] na operacao "+operacao);
				throw new SemanticException("Tipo exp1 [" + tipoExp1
						+ "] nao eh igual ao tipo exp2 [" + tipoExp2 + "] na operacao "+operacao);
			}
			return result;
		} 
		System.err.println("ERRO AO VERIFICAR SEMANTICA");
		throw new SemanticException("ERRO AO VERIFICAR SEMANTICA EXPRESSOES");
		
	}
	
	public Tipo getTipo() {
		Tipo tipoExp1 = exp1.getTipo();
		Tipo tipoExp2 = exp2.getTipo();

		Tipo resultado = null;
		if (tipoExp1 == tipoExp2 && (tipoExp1 == Tipo.FLOAT || tipoExp1 == Tipo.INT)) {
			resultado = tipoExp1;
		} else if ((operacao.equals("+") || operacao.equals("-"))
				&& (tipoExp1 == Tipo.CHAR && tipoExp2 == Tipo.INT)) {
			resultado = Tipo.CHAR;
		} else {
			System.err.println("Tipo exp1 [" + tipoExp1
					+ "] nao eh igual ao tipo exp2 [" + tipoExp2 + "] na operacao "+operacao);		}
		return resultado;
	}

	public String gerarCodigoIntermediario(String filename) {
		String ex1 = exp1.gerarCodigoIntermediario(filename);
		String ex2 = exp2.gerarCodigoIntermediario(filename);
		String tRetorno = "t"+Temporario.getInstance().getValue();
		WriteToFile.escreva("\n" + tRetorno + " = " + ex1 + " " + operacao +" " + ex2, filename);
		return tRetorno;
	}

}
