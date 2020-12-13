import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		// Questões 01, 02 e 03
		Serialize serialize = new Serialize();
		Desserialize desserialize = new Desserialize();
		serialize.addPeople();
		serialize.serializeList();
		desserialize.desserializeList();
		
		// Questão 04
		Compact compact = new Compact();
		String fileName = "";
		System.out.println("Digite o nome de um arquivo para compactação");
		fileName = input.next();
		
		compact.compactFile(fileName);
	}
}
