package demo;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import solution.Context;

public class Demo {
	public static void main(String[] args){
		AbstractApplicationContext ctx;
    	
        // load application context files
        ctx = new ClassPathXmlApplicationContext(new String []{"applicationContext.xml", "applicationContext-jpa.xml"});
		
		Context c = new Context();
		//Context c = new Context("./src/demo/Input.txt");
		while(c.inputReader.readerNotFinished()){
			System.out.println(c.inputReader.readLine());
		}
	}
}