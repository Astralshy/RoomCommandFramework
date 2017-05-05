package smsframework.inputreader;

import java.util.Scanner;

public class ConsoleReader implements ReadBehavior {
	private Scanner sc;
	private boolean notFinishedReading;
	
	public ConsoleReader(){
		sc = new Scanner(System.in);
		notFinishedReading = true;
	}
	
	@Override
	public String readLine() {
		String nl = sc.nextLine();
		if(nl.equalsIgnoreCase("exit")){
			notFinishedReading = false;
		}
		return nl;
	}

	@Override
	public boolean notFinishedReading() {
		return notFinishedReading;
	}
}
