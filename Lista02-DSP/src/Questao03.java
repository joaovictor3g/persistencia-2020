import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

public class Questao03 {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		Properties props = new Properties();
		String pathToFile = "/home/joao/eclipse-workspace/Lista02-DSP/resources/config.properties";
		
		FileInputStream fis = new FileInputStream(pathToFile);
		props.load(fis);
		
		int initialLine = Integer.parseInt(props.getProperty("linha_inicial"));
		int endLine = Integer.parseInt(props.getProperty("linha_final"));
//		System.out.println(initialLine + " " + endLine);
		
		String file;
		System.out.println("Digite o nome do arquivo");
		file = input.next();
		
		FileInputStream open = new FileInputStream(file);
		InputStreamReader is = new InputStreamReader(open);
		BufferedReader br = new BufferedReader(is);
	
		String linha = "", line2 = "";
		int size = 0;
		int flag = 0;
	
		
		if((endLine == 0) && initialLine == 0) {
			while((linha = br.readLine()) != null)	System.out.println(linha);
		
		} else if(initialLine == 0) {
			while(flag <= endLine && (linha = br.readLine()) != null) {
				System.out.println(linha);
				flag++;
			}
		
		} else if(initialLine != 0 && endLine != 0) {
			for(int i = 0; i < initialLine; i++, flag++) 
				linha = br.readLine();
				
			System.out.println(linha);
			
			while(flag < endLine && (linha = br.readLine()) != null) {
				System.out.println(linha);
				flag++;
			}
		// Caso em que só o initial line é definido
		} else if(endLine == 0) {
			for(int i = 0; i < initialLine; i++) 
				linha = br.readLine();
			System.out.println(linha);
			
			while((linha = br.readLine()) != null) System.out.println(linha);
		}
			
		
	}
}
