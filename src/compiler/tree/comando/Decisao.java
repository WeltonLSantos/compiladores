package compiler.tree.comando;


import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;
//
//decisao = "if" expressao "then" comando "else" comando
//| "if" expressao "then" comando

public class Decisao implements Comando {
	private Expressao expressao;
	private Comando comandoIf;
	private Comando comandoElse;

	//CONSTRUTOR IF ELSE
	public Decisao(Expressao expr, Comando cmdIf, Comando cmdElse) {
		this.expressao = expr;
		this.comandoIf = cmdIf;
		this.comandoElse = cmdElse;
	}

	//CONSTRUTOR IF SEM ELSE
	public Decisao(Expressao expr, Comando cmdIf) {
		this.expressao = expr;
		this.comandoIf = cmdIf;
	}


	public Boolean verificarSemantica() throws SemanticException {
		boolean resultado = true;
		if (expressao.getTipo() != Tipo.BOOLEAN) {
			throw new SemanticException("Whoops! ERRO AO VERIFICAR EXPRESSAO "
					+ "["+expressao.getTipo()+"] ");
		}
		comandoIf.verificarSemantica();
		if (comandoElse != null) {
			comandoElse.verificarSemantica();
		}
		return resultado;
	}

	public Tipo getTipo() {
		return null;
	}

	public String gerarCodigoIntermediario(String filename) {
		String labelIf = "labelIfT"+Temporario.getInstance().getValue();
		WriteToFile.escreva("\n" + labelIf + ":",filename);
		String ex = expressao.gerarCodigoIntermediario(filename);
		
		
		if (comandoElse != null) {
			String labelElse = "labelElseT"+Temporario.getInstance().getValue();
			// ifFalse expressao goto labelElse
			WriteToFile.escreva("\nifFalse " + ex + " goto " + labelElse + ":", filename);
			// comando if
			comandoIf.gerarCodigoIntermediario(filename);
		
			WriteToFile.escreva("\n" + labelElse + ":", filename);
			comandoElse.gerarCodigoIntermediario(filename);
		} else {
			String labelSair = "labelSairT"+Temporario.getInstance().getValue();
			// ifFalse expressao goto labelSair
			WriteToFile.escreva("\nifFalse " + ex + " goto " + labelSair + ":", filename);
			// comando if
			comandoIf.gerarCodigoIntermediario(filename);
		
			WriteToFile.escreva("\n" + labelSair + ":", filename);
		}
		
		return ex;
	}

}