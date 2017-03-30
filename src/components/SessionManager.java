package components;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import annotations.Regex;
import components.repositories.UserRepository;
import validation.RegexValidator;


public class SessionManager {

	private Processor p;
	
	public SessionManager(){

		p = new Processor();
	}
	
	
	@Regex
	public String process(String cmd){
		String status;
		
		if(cmd.equals(RegexValidator.INVALID)){
			status = "Invalid Input";
		} else {
			String[] processedString = cmd.split(" ");
			HashMap<String,String> args = new HashMap<String,String>();
			args.put("cmd", processedString[0]);
			if(processedString.length == 2){
				args.put("params", processedString[1]);
			} else {
				args.put("params", "");
			}
			status = p.run(args);
		}
		return status;
	}
	
	public void export(){
		
		AbstractApplicationContext ctx;
    	
        // load application context files
        ctx = new ClassPathXmlApplicationContext(new String []{"applicationContext.xml", "applicationContext-jpa.xml"});
		
	}

	
	public boolean isRegistered(){
		
		return p.isRegistered();
		
	}
	
	public boolean isRunning(){
		
		return p.isRunning();
		
	}
}
