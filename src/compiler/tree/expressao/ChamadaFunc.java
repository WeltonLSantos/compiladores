package compiler.tree.expressao;

import java.util.LinkedList;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tbsimbolos.Simbolo;
import compiler.tbsimbolos.TabelaDeSimbolos;
import compiler.tree.Tipo;
import compiler.tree.comando.Temporario;

//chamada_func = IDENTIFICADOR "(" lista_exprs ")"
//lista_exprs = VAZIA
//| expressao ("," expressao)*

public class ChamadaFunc implements Expressao {
	private String identificador;
	private LinkedList<Expressao> listaExprs;

	//CONSTRUTOR IDENTIFICADOR E LISTA EXPRESSAO
	public ChamadaFunc(String identificador, LinkedList<Expressao> listaExprs) {
		super();
		this.identificador = identificador;
		this.listaExprs = listaExprs;
	}
	
	public Boolean verificarSemantica() throws SemanticException {
		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		Simbolo simbolo = tabela.getSimbolo(identificador);
		
		if (simbolo == null) {
			System.err.println("IDENTIFICADOR ["+identificador+"] "
					+ "NAO DECLARADO ANTERIOMENTE!");
			throw new SemanticException("IDENTIFICADOR ["+identificador+"] "
					+ "NAO DECLARADO ANTERIOMENTE");
		} 	
		
		for (Expressao exp : listaExprs) {
			exp.verificarSemantica();
		}
		
		return true;
	}

	public Tipo getTipo() {
		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		Simbolo simbolo = tabela.getSimbolo(identificador);
		
		Tipo resultado = null;
		
		if (simbolo == null) {
			System.err.println("IDENTIFICADOR ["+identificador+"]"
					+ "NAO DECLARADO ANTERIOMENTE!");
		} else {
			if (simbolo.isVariavel()) {
				resultado = simbolo.getTipo();
			} else {
				resultado = simbolo.getTipoRetorno();
			}
		}
		return resultado;
	}

	public String gerarCodigoIntermediario(String filename) {
		LinkedList<String> temporarios = new LinkedList<String>();
		for (int i = 0; i<listaExprs.size(); i++) {
			if (i == 0) {
				temporarios.addLast(listaExprs.get(listaExprs.size()-1).gerarCodigoIntermediario(filename));
			} else {
				temporarios.addLast(listaExprs.get(i-1).gerarCodigoIntermediario(filename));
			}
		}
		for (String temp : temporarios) {
			WriteToFile.escreva("\nparam " + temp, filename);
		}
		
		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		Simbolo simbolo = tabela.getSimbolo(identificador);
		
		if (simbolo.getTipoRetorno() != null) {
			String tRetorno = "t"+Temporario.getInstance().getValue();
			WriteToFile.escreva("\n" + tRetorno + " = call " + identificador, filename);
			return tRetorno;
		} else {
			WriteToFile.escreva("\ncall " + identificador, filename);
			return "";
		}
	}
}
