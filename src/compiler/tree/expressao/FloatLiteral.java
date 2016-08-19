package compiler.tree.expressao;

import compiler.codigo.intermediario.WriteToFile;
import compiler.tree.Tipo;
import compiler.tree.comando.Temporario;

public class FloatLiteral implements Expressao {
	private Float floatLiteral;

	public FloatLiteral(Float floatLiteral) {
		super();
		this.floatLiteral = floatLiteral;
	}

	public Boolean verificarSemantica() {
		return true;
	}

	public Tipo getTipo() {
		return Tipo.FLOAT;
	}

	public String gerarCodigoIntermediario(String filename) {
		String tRetorno = "t"+Temporario.getInstance().getValue();
		WriteToFile.escreva("\n" + tRetorno + " = " + floatLiteral, filename);
		return tRetorno;
	}
}
