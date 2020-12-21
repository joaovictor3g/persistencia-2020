package utils;

public class FileNameWithoutExtension {
	public String name(String fileName) {
		int indexOfPoint = fileName.indexOf(".");
		String fileNameWithoutExtension = fileName.substring(0, indexOfPoint);
		
		return fileNameWithoutExtension;
	}
}
