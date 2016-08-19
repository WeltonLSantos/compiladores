package compiler.tree.comando;

/*
 * usado para garantir que o valor do temporario t usado na geracao
 * do codigo intermediario vai ser sempre diferente.
 */
public class Temporario {
	private static Temporario temporario;
	private static int value;
	
	public Temporario() {
		super();
		this.value = 0;
	}

	public int getValue() {
		int resultado = value;
		value++;
		return resultado;
	}
	
	public static synchronized Temporario getInstance() {
		if (temporario == null) {
			temporario = new Temporario();
		} return temporario;
	}
}