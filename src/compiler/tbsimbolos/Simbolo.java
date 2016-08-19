package compiler.tbsimbolos;

import java.util.LinkedList;

import compiler.tree.Tipo;
import compiler.tree.comando.DeclVariavel;

public class Simbolo {
	private String nome;
	private Tipo tipo;
	private Tipo tipoRetorno;
	private boolean variavel;
	private LinkedList<DeclVariavel> parametros;
	
	public Simbolo(String nome, Tipo tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.variavel = true;
	}

	public Simbolo(String nome, Tipo tipoRetorno, LinkedList<DeclVariavel> parametros) {
		super();
		this.nome = nome;
		this.tipoRetorno = tipoRetorno;
		this.variavel = false;
		this.parametros = parametros;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LinkedList<DeclVariavel> getParametros() {
		return parametros;
	}
	public void setParametros(LinkedList<DeclVariavel> parametros) {
		this.parametros = parametros;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public boolean isVariavel() {
		return variavel;
	}
	public void setVariavel(boolean variavel) {
		this.variavel = variavel;
	}
	public Tipo getTipoRetorno() {
		return tipoRetorno;
	}
	public void setTipoRetorno(Tipo tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}
	
}