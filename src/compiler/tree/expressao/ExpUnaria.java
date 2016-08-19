package compiler.tree.expressao;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tree.Tipo;
import compiler.tree.comando.Temporario;

public class ExpUnaria implements Expressao {
	private Expressao expr;
	private String operacao;

	public ExpUnaria(String operacao, Expressao expr) {
		this.operacao = operacao;
		this.expr = expr;
	}

	public Boolean verificarSemantica() throws SemanticException {

		// REGRA 4 E 8 DO PDF
		boolean resultado = false;
		Tipo tipoExp = expr.getTipo();

		if (operacao.equals("SUB") && (tipoExp == Tipo.FLOAT || tipoExp == Tipo.INT)) {
			resultado = true;
		} else {
			System.err.println("Tipo exp [" + tipoExp + "] Whoops, nao eh um tipo numerico (int ou float)");
			throw new SemanticException("Tipo exp [" + tipoExp + "] nao eh um tipo numerico (int ou float)");
		}
		return resultado;
	}

	public Tipo getTipo() {
		// REGRA 4 E 8 DO PDF
		Tipo resultado = null;
		Tipo tipoExp = expr.getTipo();

		if (operacao.equals("SUB") && (tipoExp == Tipo.FLOAT || tipoExp == Tipo.INT)) {
			resultado = tipoExp;
		} else {
			System.err.println("Tipo exp [" + tipoExp + "] Whoops, nao eh um tipo numerico (int ou float)");
		}
		return resultado;
	}

	public String gerarCodigoIntermediario(String filename) {
		String ex = expr.gerarCodigoIntermediario(filename);
		String op;

		if (operacao.equals("SUB")) {
			op = "-";
		} else if (operacao.equals("NOT")) {
			op = "!";
		} else {
			op = "erro";
		}

		String tRetorno = "t" + Temporario.getInstance().getValue();

		WriteToFile.escreva("\n" + tRetorno + " = " + op + " " + ex, filename);
		return tRetorno;
	}
}