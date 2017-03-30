package components;

import java.util.HashMap;

import annotations.Regex;
import validation.RegexValidator;

public class SessionManager {

	private Processor p;
	
	public SessionManager(){

		p = new Processor(this);
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
		
		//logic for saving session to .txt file or DB
		
	}
	
	public boolean isRunning(){
		
		return p.isRunning();
		
	}
}
