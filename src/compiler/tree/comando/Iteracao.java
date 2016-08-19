package compiler.tree.comando;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;

//iteracao = "while" "(" expressao ")" comando

public class Iteracao implements Comando {

	private Expressao expressao;
	private Comando comando;

	// CONTRUTOR EXPRESSAO E COMANDO
	public Iteracao(Expressao expressao, Comando comando) {
		super();
		this.expressao = expressao;
		this.comando = comando;
	}

	public Boolean verificarSemantica() throws SemanticException {
		if (expressao.getTipo() == Tipo.BOOLEAN) {
			return comando.verificarSemantica();
		}
		throw new SemanticException(
				"Whoops! ERRO AO VERIFICAR [" + expressao.getTipo() + "] ");
	}

	public Tipo getTipo() {
		return null;
	}

	public String gerarCodigoIntermediario(String filename) {

		String labelWhileInicio = "labelWhileInicioT" + Temporario.getInstance().getValue();
		String labelWhileSair = "labelWhileSairT" + Temporario.getInstance().getValue();

		WriteToFile.escreva("\n" + labelWhileInicio + ":", filename);
		String ex = expressao.gerarCodigoIntermediario(filename);
		WriteToFile.escreva("\n" + "ifFalse " + ex + " goto " + labelWhileSair, filename);

		comando.gerarCodigoIntermediario(filename);

		WriteToFile.escreva("\ngoto " + labelWhileInicio, filename);
		WriteToFile.escreva("\n" + labelWhileSair + ":", filename);
		return null;
	}

}
