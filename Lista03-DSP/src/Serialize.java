import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Serialize {
	ArrayList<Person> listPerson = new ArrayList<>();
	
	private String[] names = {
			"João", 
			"Victor", 
			"Pedro", 
			"Lavine", 
			"Letícia", 
			"Leo",
			};
	
	private String[] emails = {
			"joao@gmail.com", 
			"victor@gmail.com", 
			"pedro@gmail.com", 
			"lavine@gmail.com",
			"leticia@gmail.com",
			"leo@gmail.com",
			};
	
	private String[] phones = {
			"87576576567",
			"89089089080",
			"97898798798",
			"78979879878",
			"98789798789",
			"90809890809",
	};
	
	public void addPeople() {
		for(int i = 0; i < 6; i++) {
			Person person = new Person(i, names[i], emails[i], phones[i]);
			listPerson.add(person);
		}
	}
	
	public void serializeList() {
		try {
			FileOutputStream gravarArq = new FileOutputStream("serialized.dat");
			ObjectOutputStream objGravar = new ObjectOutputStream(gravarArq);
			
			objGravar.writeObject(listPerson);
			objGravar.flush();
			objGravar.close();
			gravarArq.flush();
			gravarArq.close();
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	
}
