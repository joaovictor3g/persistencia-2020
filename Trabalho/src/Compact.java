import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compact {
	public void compactFile(String fileName) throws IOException {
		int indexOfPoint = fileName.indexOf(".");
		String fileNameWithoutExtension = fileName.substring(0, indexOfPoint);
		
		try(FileInputStream fis = new FileInputStream(fileName);
			FileOutputStream fos = new FileOutputStream(fileNameWithoutExtension + ".zip");
			
			ZipOutputStream zos = new ZipOutputStream(fos)) {
			
			zos.putNextEntry(new ZipEntry(fileName));
			int content;
			
			
			while((content = fis.read()) != -1) {
				zos.write(content);
			}
			
			System.out.println("Arquivo Zipado com sucesso ");
			
			zos.closeEntry();		
			zos.close();
			
		}
	}
}
