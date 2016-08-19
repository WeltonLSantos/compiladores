package compiler.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import compiler.syntax.Lexer;
import compiler.syntax.Parser;
import compiler.tree.Programa;
import java_cup.runtime.Symbol;

public class TestParser {

	public static void main(String args[]) throws IOException {
		Lexer lexer;
		Parser Parser;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Digite o nome do arquivo: ");
		String arquivo = in.readLine();
		// String arquivo = args[0];
		// System.out.println(arquivo);

		try {
			lexer = new Lexer(new FileInputStream(arquivo));
			Parser = new Parser(lexer);

			Symbol symbol = Parser.parse();

			Programa TREE = (Programa) symbol.value;

			String nomeArquivoNovo = arquivo.replace(".mtt", ".asm");
			System.out.println("FAVOR INFORMAR NOME DO ARQUIVO: " + nomeArquivoNovo);
			TREE.gerarCodigoIntermediario(nomeArquivoNovo);

			System.out.println("SINTAXE OK :)");

		} catch (Exception e) {
			System.out.println("Whoops, ERRO SINTATICO!");
			e.printStackTrace();
		}
	}
}
