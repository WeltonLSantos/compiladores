package compiler.tbsimbolos;

import java.util.HashMap;
import java.util.Map;

public class TabelaDeSimbolos {
	private static TabelaDeSimbolos tbSimbolos;
	private static Map<String, Simbolo> tbLocal;
	private static Map<String, Simbolo> tbGlobal;

	// ATUA COMO UMA "ESTRUTURA DE DADOS" GERALMENTE � UMA ARVORE OU TABELA
	// HASH. UTILIZADA PARA GUARDAR: VARI�VEIS, FUN��ES OU TIPO DEFINIDO PELO
	// USU�RIO. NA AN�LISE SEM�NTICA ELA AJUDA NA VERIFICA��O DE ESCOPO E TIPOS

	private TabelaDeSimbolos() {
		tbGlobal = new HashMap<String, Simbolo>();
	}

	public static void CriartbLocal() {
		if (tbLocal != null) {
			tbLocal.clear();
		}
		tbLocal = new HashMap<String, Simbolo>();
	}

	public static void Colocar(Simbolo simbolo) {
		if (tbLocal == null) {
			tbGlobal.put(simbolo.getNome(), simbolo);
		} else {
			tbLocal.put(simbolo.getNome(), simbolo);
		}
	}

	public Simbolo getSimbolo(String nome) {
		Simbolo simbolo = null;
		if (tbLocal != null && !tbLocal.isEmpty()) {
			simbolo = tbLocal.get(nome);
		}

		if (simbolo == null) {
			simbolo = tbGlobal.get(nome);
		}
		return simbolo;
	}

	public static synchronized TabelaDeSimbolos getInstance() {
		if (tbSimbolos == null) {
			tbSimbolos = new TabelaDeSimbolos();
		}
		return tbSimbolos;
	}
}
