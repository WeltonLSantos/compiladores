package compiler.codigo.intermediario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//É A TRANSFORMAÇÃO DE UMA ÁRVORE DE DERIVAÇÃO EM UM SEGUIMENTO DE CÓDIGO FINAL OU INTERMEDIÁRIO 
//E SUA VANTAGEM É QUE POSSIBILITA OTIMIZAR E GERAR UM CÓDIGO FINAL MAIS EFICIENTE ETC..
public class WriteToFile {

	public static void escreva(String texto, String filename) {
		try {
			File file = new File(filename);

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(texto);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}