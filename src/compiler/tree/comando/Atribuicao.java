package compiler.tree.comando;

import java.util.LinkedList;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tbsimbolos.Simbolo;
import compiler.tbsimbolos.TabelaDeSimbolos;
import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;

//atribuicao = lista_idents "=" expressao ";"
//				lista_comandos = (comando)*
//				comando = decl_variavel
//			| atribuicao
//			| iteracao
//			| decisao
//			| escrita
//			| retorno
//			| bloco
//			| chamada_func_cmd

public class Atribuicao implements Comando {
	private LinkedList<String> listaIdents;
	private Expressao expressao;

	public Atribuicao(LinkedList<String> listaIdents, Expressao expressao) {
		super();
		this.listaIdents = listaIdents;
		this.expressao = expressao;
	}

	public Boolean verificarSemantica() throws SemanticException {
		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		Simbolo simbolo = null;

		Tipo tipoExp = expressao.getTipo();

		if (tipoExp == null) {
			System.err.println("TIPO DE EXPRESSAO [" + expressao.getClass().getName() + "]"
					+ "Whoos!, NAO IDENTIFICADA!");
			throw new SemanticException("TIPO DE EXPRESSAO [" + expressao.getClass().getName() + "]"
					+ "Whoos!, NAO IDENTIFICADA!");
		}
		for (String identificador : listaIdents) {
			simbolo = tabela.getSimbolo(identificador);
			if (simbolo == null) {
				System.err.println("IDENTIFICADOR [" + identificador + "] "
						+ "NAO DECLARADA ANTERIOMENTE!");
				throw new SemanticException("IDENTIFICADOR [" + identificador + "]  "
						+ "NAO DECLARADA ANTERIOMENTE!");
			} else if (!simbolo.isVariavel()) {
				System.err.println("IDENTIFICADOR [" + identificador + "] "
						+ "NAO PERTENCE AO TIPO DE VARIAVEL!");
				throw new SemanticException("IDENTIFICADOR [" + identificador + "] "
						+ "NAO PERTENCE AO TIPO DE VARIAVEL!");
			} else if (simbolo.getTipo() != tipoExp) {
				System.err.println("IDENTIFICADOR [" + identificador + "] "
						+ "NAO POSSUIU O MESMO TIPO DA EXPRESSAO!");
				throw new SemanticException("IDENTIFICADOR [" + identificador + "] "
						+ "NAO POSSUIU O MESMO TIPO DA EXPRESSAO!");
			}
		}
		return true;
	}
	
	public Tipo getTipo() {
		return expressao.getTipo();
	}

	public String gerarCodigoIntermediario(String filename) {
		String ex = expressao.gerarCodigoIntermediario(filename);
		for (String id : listaIdents) {
			WriteToFile.escreva("\n " + id + " = " + ex, filename);
		}
		return null;
	}

}
