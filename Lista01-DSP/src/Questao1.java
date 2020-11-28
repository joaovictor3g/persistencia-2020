import java.io.*;
import java.util.Scanner;


public class Questao1 {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		int n1 = 0, n2 = 0;
		String fileName;
		
		System.out.println("Digite os valores para n1 e n2");
		n1 = scanner.nextInt();
		n2 = scanner.nextInt();
		
		System.out.println("\nDigite o nome do arquivo");
		fileName = scanner.next();
		
		try {
			FileInputStream file = new FileInputStream(fileName); // Abre arquivo já existentes
			InputStreamReader input = new InputStreamReader(file); // Decodifica os bytes usando UTF-8, padrão
	        BufferedReader br = new BufferedReader(input); // Lê os dados linha a linha
	        
	        String linha = "";
	        
	        if(n1 < 0 || n2 < 0) 
	        	System.out.println("Não existe arquivo com linhas negativas!!!!");
	        
	        if(n1 != 0 && n2 != 0) { // Caso em que n1 e n2 são definidos
	        	for(int i = 0; i < n1; i++) // Percorre arquivo até o correspondente a linha n1
	        		linha = br.readLine();
	       
	        	System.out.println(linha); // Imprime a linha igual a n1
	        
	        	for(int i = n1; i < n2; i++) { // Percorre e imprime o arquivo de n1 até o valor de n2
	        		linha = br.readLine();
	        		System.out.println(linha);
	        	}
	        	
	        } else if(n1 == 0 && n2 != 0) { //  Caso em que n1 não é definido
	        	for(int i = 0; i < n2; i++) { // Percorre e imprime até o valor de n2
	        		linha = br.readLine();
	        		System.out.println(linha);
	        	}
	        		
	        } else if(n1 != 0 && n2 == 0) { // Caso em que n2 não é definido
	        	for(int i = 0; i < n1; i++) // Percorre até valor de n1
	        		linha = br.readLine();
	        	System.out.println(linha); // Imprime linha correspondente a n1
	        	
	        	while((linha = br.readLine()) != null) // Percorre e imprime todo o resto
	        		System.out.println(linha);
	        	
	        } else { // Caso em que n1 e n2 não definidos
	        	while((linha = br.readLine()) != null) // Percorre e imprime todo o arquivo
	        		System.out.println(linha);
	        }
	        
	        // Fechamento dos objetos
	        file.close(); 
	        input.close();
	        br.close();
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
