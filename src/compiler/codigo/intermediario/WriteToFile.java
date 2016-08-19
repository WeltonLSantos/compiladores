package compiler.codigo.intermediario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//� A TRANSFORMA��O DE UMA �RVORE DE DERIVA��O EM UM SEGUIMENTO DE C�DIGO FINAL OU INTERMEDI�RIO 
//E SUA VANTAGEM � QUE POSSIBILITA OTIMIZAR E GERAR UM C�DIGO FINAL MAIS EFICIENTE ETC..
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