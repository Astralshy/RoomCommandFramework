package components;

import java.util.HashMap;

import annotations.Regex;

public class SessionManager {

	private Processor p;
	
	public SessionManager(){

		p = new Processor(this);
	}
	
	@Regex
	public String process(String cmd){
		String status;
		String[] processedString = cmd.split(" ");
		if(processedString.length > 2){
			status = "Input does not follow the proper format";
		} else {
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
