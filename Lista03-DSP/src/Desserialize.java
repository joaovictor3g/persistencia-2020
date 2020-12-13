import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Desserialize {
	public void desserializeList() {
		try {
			FileInputStream openArq = new FileInputStream("serialized.dat");
			ObjectInputStream objRead = new ObjectInputStream(openArq);
			
			System.out.println(objRead.readObject());
			openArq.close();
			objRead.close();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
