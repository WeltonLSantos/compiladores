package compiler.tree;

import java.util.LinkedList;
import java.util.List;

import compiler.codigo.intermediario.WriteToFile;
import compiler.exception.SemanticException;
import compiler.tbsimbolos.Simbolo;
import compiler.tbsimbolos.TabelaDeSimbolos;
import compiler.tree.comando.DeclVariavel;

//assinatura = tipo IDENTIFICADOR "(" param_formais ")"
//				| “void” IDENTIFICADOR "(" param_formais ")"
//tipo = "int" | "char" | "float" | "string"

public class Assinatura {
	private LinkedList<DeclVariavel> paramFormais;
	private Tipo tipo;
	private String identificador;

	public Assinatura(String identificar, LinkedList<DeclVariavel> paramFormais, Tipo tipo) {
		super();
		this.paramFormais = paramFormais;
		this.tipo = tipo;
		this.identificador = identificar;
		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		Simbolo simbolo = new Simbolo(identificar, tipo, paramFormais);
		tabela.Colocar(simbolo);
		tabela.CriartbLocal();
	}

	public Assinatura(String identificar, LinkedList<DeclVariavel> paramFormais) {
		super();
		this.paramFormais = paramFormais;
		this.identificador = identificar;
		TabelaDeSimbolos tabela = TabelaDeSimbolos.getInstance();
		Simbolo simbolo = new Simbolo(identificar, null, paramFormais);
		tabela.Colocar(simbolo);
		tabela.CriartbLocal();
	}

	public Boolean verificarSemantica() throws SemanticException {
		boolean resultado = true;
		for (DeclVariavel declVariavel : paramFormais) {
			if (!declVariavel.verificarSemantica()) {
				System.err.println("Whoops! VERIFICAR declVariavel!");
				throw new SemanticException("whoops! VERIFICAR declVariavel!");
			}
		}
		return resultado;
	}

	public String gerarCodigoIntermediario(String filename) {
		WriteToFile.escreva("\ndef " + identificador + " (", filename);

		for (int i = paramFormais.size() - 1; i >= 0; i--) {
			List<String> ids = paramFormais.get(i).getIdents();
			for (String id : ids) {
				WriteToFile.escreva(" " + id, filename);
			}
		}
		WriteToFile.escreva(")", filename);

		return null;
	}
}
