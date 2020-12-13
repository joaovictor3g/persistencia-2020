import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

public class Questao01 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		String originFileName, destinyFileName;
		
		System.out.println("Digite o nome do arquivo origem");
		originFileName = scan.next();
		
		File file = new File(originFileName); 
		
		long initial = System.currentTimeMillis();
		byte[] bytes = Files.readAllBytes(file.toPath());
		
		String textOrigin = new String(bytes, "UTF-8");
		
		System.out.println("Digite o nome do arquivo destino");
		destinyFileName = scan.next();
		
		PrintWriter pw = new PrintWriter(destinyFileName);
		pw.print(textOrigin);
		
		System.out.println("Tempo total de cópia é: " + (System.currentTimeMillis() - initial));
		
		pw.close();
	
	}
}
