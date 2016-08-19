package compiler.tree.expressao;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tbsimbolos.Simbolo;
import compiler.tbsimbolos.TabelaDeSimbolos;
import compiler.tree.Tipo;
import compiler.tree.comando.Temporario;

public class ExprIdentificador implements Expressao {
	private String identificador;

	public ExprIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Boolean verificarSemantica() throws SemanticException {
		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		Simbolo simbolo = tabela.getSimbolo(identificador);

		boolean resultado = false;

		if (simbolo != null) {
			resultado = true;
		} else {
			System.err.println("IDENTIFICADOR ["+identificador+"] Whoops! "
					+ "NAO FOI DECLARADO ANTERIOMENTE!");
			throw new SemanticException("IDENTIFICADOR ["+identificador+"] "
					+ "NAO FOI DECLARADO ANTERIOMENTE!");
		}
		return resultado;
	}

	public Tipo getTipo() {
		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		Simbolo simbolo = tabela.getSimbolo(identificador);

		Tipo resultado = null;

		if (simbolo != null) { 
			if (simbolo.isVariavel()) {
				resultado = simbolo.getTipo();
			} else {
				resultado = simbolo.getTipoRetorno();
			}
		} else {
			System.err.println("IDENTIFICADOR  ["+identificador+"] "
					+ "NAO FOI DECLARADO ANTERIOMENTE!");
		}
		return resultado;
	}

	public String gerarCodigoIntermediario(String filename) {
		String tRetorno = "t"+Temporario.getInstance().getValue();
		WriteToFile.escreva("\n" + tRetorno + " = " + identificador, filename);
		return tRetorno;
	}

}
