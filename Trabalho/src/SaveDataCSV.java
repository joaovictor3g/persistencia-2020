import java.io.FileWriter;
import java.io.PrintWriter;

public class SaveDataCSV {
	public void addClientToFileCSV(String fileName, Client client) {
		try {
			FileWriter fw = new FileWriter(fileName, true);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(client);
			pw.close();
			fw.close();
			System.out.println("Cliente salvo no CSV!!!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
