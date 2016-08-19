package compiler.semantica;

import compiler.tree.Tipo;

public class SemanticItem {
	public Tipo tipo;
	public int idEscopo;
	public SemanticItem(Tipo tipo) {
		super();
		this.tipo = tipo;
	}

	public String toString() {
		return "SemanticItem [tipo=" + tipo + "]";
	}
	
}