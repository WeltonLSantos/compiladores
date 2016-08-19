package compiler.tree.expressao;

import compiler.codigo.intermediario.WriteToFile;
import compiler.tree.Tipo;
import compiler.tree.comando.Temporario;

public class StringLiteral implements Expressao {

	private String stringLiteral;

	public StringLiteral(String stringLiteral) {
		this.stringLiteral = stringLiteral;

	}

	public Boolean verificarSemantica() {
		return true;
	}

	public Tipo getTipo() {
		return Tipo.STRING;
	}

	public String gerarCodigoIntermediario(String filename) {
		String tRetorno = "t"+Temporario.getInstance().getValue();
		WriteToFile.escreva("\n" + tRetorno + " = " + stringLiteral, filename);
		return tRetorno;
	}
}