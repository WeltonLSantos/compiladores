package compiler.tree.expressao;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tree.Tipo;
import compiler.tree.comando.Temporario;

public class ExprRelacional implements Expressao {

	private Expressao expr1;
	private Expressao expr2;
	private String operador;

	public ExprRelacional(Expressao expr1, Expressao expr2, String operador) {
		this.expr1 = expr1;
		this.expr2 = expr2;
		this.operador = operador;
	}

	public Boolean verificarSemantica() throws SemanticException {
		Tipo tipoExpr1 = expr1.getTipo();
		Tipo tipoExpr2 = expr2.getTipo();

		boolean resultado = true;

		if ((operador.equals("==") || operador.equals("!=")) && (tipoExpr1 == tipoExpr2 && tipoExpr1 != Tipo.STRING)) {

			// resultado = Tipo.BOOLEAN;
		} else if (tipoExpr1 == tipoExpr2
				&& (operador.equals("<") || operador.equals("<=") || operador.equals(">") || operador.equals(">="))
				&& (tipoExpr1 == Tipo.FLOAT || tipoExpr1 == Tipo.INT)) {

			// resultado = Tipo.BOOLEAN;
		} else {
			System.err.println("Tipo exp1 [" + tipoExpr1 + "] nao eh igual ao tipo exp2 [" + tipoExpr2
					+ "] na operacao " + operador + " ou sao do tipo String");
			throw new SemanticException("Tipo exp1 [" + tipoExpr1 + "] nao eh igual ao tipo exp2 [" + tipoExpr2
					+ "] na operacao " + operador + " ou sao do tipo String");
		}
		return resultado;
	}

	public Tipo getTipo() {
		Tipo tipoExpr1 = expr1.getTipo();
		Tipo tipoExpr2 = expr2.getTipo();

		Tipo resultado = null;

		if ((operador.equals("==") || operador.equals("!=")) && (tipoExpr1 == tipoExpr2 && tipoExpr1 != Tipo.STRING)) {
			// regra mitte 6
			resultado = Tipo.BOOLEAN;
		} else if (tipoExpr1 == tipoExpr2
				&& (operador.equals("<") || operador.equals("<=") || operador.equals(">") || operador.equals(">="))
				&& (tipoExpr1 == Tipo.FLOAT || tipoExpr1 == Tipo.INT)) {
			// regra mitte 5
			resultado = Tipo.BOOLEAN;
		} else {
			System.err.println("Tipo exp1 [" + tipoExpr1 + "] nao eh igual ao tipo exp2 [" + tipoExpr2
					+ "] na operacao " + operador + " ou sao do tipo String");
		}
		return resultado;
	}

	public String gerarCodigoIntermediario(String filename) {
		String ex1 = expr1.gerarCodigoIntermediario(filename);
		String ex2 = expr2.gerarCodigoIntermediario(filename);
		String tRetorno = "t" + Temporario.getInstance().getValue();
		WriteToFile.escreva("\n" + tRetorno + " = " + ex1 + " " + operador + " " + ex2, filename);
		return tRetorno;
	}

}
