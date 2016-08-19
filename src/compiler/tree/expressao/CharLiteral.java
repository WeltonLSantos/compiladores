package compiler.tree.expressao;

import compiler.codigo.intermediario.WriteToFile;
import compiler.tree.Tipo;
import compiler.tree.comando.Temporario;

//expr_basica = "(" expressao ")"
//| "not" expr_basica
//| "-" expr_basica
//| INT_LITERAL
//| CHAR_LITERAL
//| FLOAT_LITERAL
//| STRING_LITERAL
//| IDENTIFICADOR
//| chamada_func
public class CharLiteral implements Expressao {
	private String charLiteral;

	//CONSTRUTOR
	public CharLiteral(String charLiteral) {
		super();
		this.charLiteral = charLiteral;
	}

	public Tipo getTipo() {
		return Tipo.CHAR;
	}

	public Boolean verificarSemantica() {
		return true;
	}

	public String gerarCodigoIntermediario(String filename) {
		String tRetorno = "t"+Temporario.getInstance().getValue();
		WriteToFile.escreva("\n" + tRetorno + " = " + charLiteral,filename);
		return tRetorno;
	}
}
