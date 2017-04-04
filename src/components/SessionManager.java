package components;

import java.util.HashMap;

import annotations.Regex;
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
	
	public HashMap<String,Object> export(){
		return p.export();
	}

	public void importUser(HashMap<String,Object> args){
		
		p.importUser(args);
		
	}
	
	public boolean isRegistered(){
		
		return p.isRegistered();
		
	}
	
	public boolean isRunning(){
		
		return p.isRunning();
		
	}
	
	public void toggleRegistered(){
		
		p.toggleRegistered();
		
	}
	
	public String getName(){
		
		return p.getName();
	}
	
	public boolean isNew(){
		
		return p.isNew();
		
	}
	
	public void toggleRunning(){
		
		p.toggleRunning();
		
	}
	
	public void toggleNew(){
		
		p.toggleNew();
		
	}
}
