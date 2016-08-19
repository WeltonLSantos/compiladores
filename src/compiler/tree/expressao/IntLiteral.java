package compiler.tree.expressao;

import compiler.codigo.intermediario.WriteToFile;
import compiler.tree.Tipo;
import compiler.tree.comando.Temporario;

public class IntLiteral implements Expressao {
	private Integer intLiteral;

	public IntLiteral(Integer intLiteral) {
		super();
		this.intLiteral = intLiteral;
	}
	
	public Boolean verificarSemantica() {
		return true;
	}

	public Tipo getTipo() {
		return Tipo.INT;
	}

	public String gerarCodigoIntermediario(String filename) {
		String tRetorno = "t"+Temporario.getInstance().getValue();
		WriteToFile.escreva("\n" + tRetorno + " = " + intLiteral, filename);
		return tRetorno;
	}
}
