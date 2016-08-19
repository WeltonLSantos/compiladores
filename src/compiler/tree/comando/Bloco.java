package compiler.tree.comando;

import java.util.ArrayList;
import java.util.List;

import compiler.exception.SemanticException;
import compiler.tree.Tipo;

//bloco = "{" lista_comandos "}"
//lista_comandos = (comando)*
//comando = decl_variavel
//| atribuicao
//| iteracao
//| decisao
//| escrita
//| retorno
//| bloco
//| chamada_func_cmd

public class Bloco implements Comando {
	private List<Comando> comandos;

	public Bloco() {
		this.comandos = new ArrayList<Comando>();
	}

	public Bloco(List<Comando> comandos) {
		this.comandos = comandos;
	}

	public void add(Comando cmd) {
		this.comandos.add(cmd);
	}

	public Boolean verificarSemantica() throws SemanticException {
		boolean resultado = true;
		for (Comando comando : comandos) {
			comando.verificarSemantica();
		}
		return resultado;
	}

	public Tipo getTipo() {
		return null;
	}

	public String gerarCodigoIntermediario(String filename) {
		for (Comando cmd : comandos) {
			cmd.gerarCodigoIntermediario(filename);
		}
		return null;
	}
}
