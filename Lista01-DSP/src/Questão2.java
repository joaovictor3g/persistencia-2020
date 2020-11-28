import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Questão2 {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		String originFileName = "", destinyFileName = "";
		
		System.out.println("Digite o nome do arquivo origem");
		originFileName = scanner.next();
		
		try {
			long initialTime = System.currentTimeMillis(); // Calcula tempo neste exato instante
			
			FileInputStream file = null;
			try {
				 file = new FileInputStream(originFileName);
				 
			} catch(FileNotFoundException e) {
				System.out.println("Arquivo origem inexistente, execute novamente");
				return;
			}
			
			InputStreamReader input = new InputStreamReader(file);
	        BufferedReader br = new BufferedReader(input);
	        
	        System.out.println("Digite o nome do arquivo destino");
			destinyFileName = scanner.next();
	        
	        FileWriter fw = new FileWriter(destinyFileName, true); // Cria um novo arquivo
            PrintWriter pw = new PrintWriter(fw); // Escreve no arquivo
            String word = "";
            while((word = br.readLine()) != null) 
            	pw.println(word);
            
            System.out.println("Tempo total de cópia: " + (System.currentTimeMillis() - initialTime) + " ms");
            
            
            file.close();
            input.close();
            br.close();
            fw.close();
            pw.close();
            
		} catch(FileNotFoundException e) {	
			System.out.println("Arquivo não encontrado");
		}
	}
}
