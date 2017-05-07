package demo;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import solution.Context;
import solution.commands.GoCommand;

public class Demo {
	public static void main(String[] args) throws Exception{
		AbstractApplicationContext ctx;
    	
        // load application context files
        ctx = new ClassPathXmlApplicationContext(new String []{"applicationContext.xml", "applicationContext-jpa.xml"});
		
		Context c = new Context("solution.commands");
		//Context c = new Context("solution.commands", "./src/demo/Input.txt");
		while(c.inputReader.readerNotFinished()){
			c.runCommand(c.inputReader.readLine());
		}
	}
}