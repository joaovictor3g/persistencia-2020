import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;

import utils.FileNameWithoutExtension;

import com.google.gson.*;

public class ConvertToJSONAndXML {
	
	public void ConvertToJSON(String fileName) {
		System.out.println("Convertendo " + fileName + " para JSON...");
		FileNameWithoutExtension fnwe = new FileNameWithoutExtension();
		String fileNameWithoutExtension = fnwe.name(fileName);
		
		GsonBuilder gsonBuilder = new GsonBuilder()
				.setPrettyPrinting()
				.enableComplexMapKeySerialization();
		
		
		try {
			FileInputStream file = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(file);
			BufferedReader br = new BufferedReader(isr);
			
			FileWriter fileWriter = new FileWriter(fileNameWithoutExtension+".json", true);
			
			System.out.println("Gerando arquivo " + fileNameWithoutExtension+".json ......");
			
			String line;
			String[] words = null;
			String[] headers = null;
			line = br.readLine();
			
			headers = line.split(";");
			Map<String, String> original = new LinkedHashMap<String, String>();
			List<Map<String, String>> list = new ArrayList<>();
			
			while((line = br.readLine()) != null) {
				words=line.split(";");
				for(int i = 0; i < words.length; i++)
					original.put(headers[i], words[i]);
				list.add(original);
			}
			
			String json = gsonBuilder
					.create()
					.toJson(list);
			
			fileWriter.write(json);
			fileWriter.close();		
			
			System.out.println("Arquivo "+fileNameWithoutExtension+".json foi criado");
 			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	
		
	}
	
	public void convertToXML(String fileName) {
		System.out.println("Convertendo " + fileName + " para CSV..");
		FileNameWithoutExtension fnwe = new FileNameWithoutExtension();
		try {
			FileInputStream file = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(file);
			BufferedReader br = new BufferedReader(isr);
			
			String fileNameWithoutExtension = fnwe.name(fileName);
			
            FileWriter fileWriter = new FileWriter(fileNameWithoutExtension+".xml", true);
            
            String line = br.readLine();

            String[] header = line.split(";");

            List<String[]> out = new ArrayList<>();
            List<List<String[]>> outList = new ArrayList<>();
            
            while((line = br.readLine())!=null){
                List<String[]> item = new ArrayList<String[]>();
                String[] words = line.split(";");
                for (int i = 0; i < words.length; i++) {
                    String[] keyVal = new String[2];
                    keyVal[0] = header[i];
                    keyVal[1] = words[i];
                    out.add(keyVal);
                }
                outList.add(out);
            }
            System.out.println("Gerando arquivo " + fileNameWithoutExtension+".xml ......");
            XStream xstream = new XStream(new DomDriver());
         
            xstream.toXML(outList, fileWriter);
            
            System.out.println("Arquivo "+fileNameWithoutExtension+".xml foi criado");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
}
