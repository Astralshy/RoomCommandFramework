package demo;

import solution.Context;

public class Demo {
	public static void main(String[] args) throws Exception{
		Context c = new Context();
		//Context c = new Context("./src/demo/Input.txt");
		c.setSMSFrameworkPath("solution.commands");
		while(c.sessionNotFinished()){
			c.runCommand(c.readerReadLine());
		}
	}
}