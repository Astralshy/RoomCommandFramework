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
		
		Context c = new Context();
		//Context c = new Context("./src/demo/Input.txt");
		c.setSMSFrameworkPath("solution.commands");
		while(c.readerNotFinished()){
			c.runCommand(c.readerReadLine());
		}
	}
}