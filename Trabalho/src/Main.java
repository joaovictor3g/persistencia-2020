import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import com.google.gson.Gson;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		Client cli = new Client();
		SaveDataCSV sdc = new SaveDataCSV();
		Random rand = new Random();
		
//		 Questão 01 e 02
		String name, cpf, address, email, city, phone, uf, cep;
		cli.setId(rand.nextInt() * 1000);
		
		System.out.println("Nome: ");
		name = input.next();
		cli.setName(name);
		
		System.out.println("CPF: ");
		cpf = input.next();
		cli.setCpf(cpf);
		
		System.out.println("Addres: ");
		address = input.next();
		cli.setAddress(address);
		
		System.out.println("E-Mail: ");
		email = input.next();
		cli.setEmail(email);
		
		System.out.println("City: ");
		city = input.next();
		cli.setCity(city);
		
		System.out.println("Phone: ");
		phone = input.next();
		cli.setPhone(phone);
		
		System.out.println("UF: ");
		uf = input.next();
		cli.setUf(uf);
		
		System.out.println("CEP: ");
		cep = input.next();
		cli.setCep(cep);
		
		sdc.addClientToFileCSV("saveData.csv", cli);
		
		//Questão 03
		ConvertToJSONAndXML convert = new ConvertToJSONAndXML();
		System.out.println("Digite o nome do arquivo existente a ser convertido para JSON e XML e compactado no formato ZIP");
		String fileNameToConvert = input.next();
		convert.ConvertToJSON(fileNameToConvert);
		convert.convertToXML(fileNameToConvert);
		
		// Questão 04
		Compact compact = new Compact();
		compact.compactFile(fileNameToConvert);
	}
}
