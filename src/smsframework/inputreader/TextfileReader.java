package smsframework.inputreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextfileReader implements ReadBehavior {	
	private File file;
	private Scanner sc;
	
	public TextfileReader(String path){
		file = new File(path);
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
	
	@Override
	public String readLine() {
		return sc.nextLine();
	}

	@Override
	public boolean notFinishedReading() {
		return sc.hasNextLine();
	}
}
