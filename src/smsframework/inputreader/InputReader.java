package smsframework.inputreader;

public class InputReader {
	private ReadBehavior readBehavior;
	public InputReader(){
		readBehavior = new ConsoleReader();
	}
	public InputReader(String path){
		readBehavior = new TextfileReader(path);
	}
	public String readLine(){
		return readBehavior.readLine();
	}
	public boolean readerNotFinished(){
		return readBehavior.notFinishedReading();
	}
}
