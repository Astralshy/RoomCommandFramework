package demo;
import solution.Context;

public class Demo {
	public static void main(String[] args){
		//Context c = new Context();
		Context c = new Context("./src/demo/Input.txt");
		while(c.inputReader.readerNotFinished()){
			System.out.println(c.inputReader.readLine());
		}
	}
}