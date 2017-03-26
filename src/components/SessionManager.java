package components;

import java.util.HashMap;

import annotations.Regex;

public class SessionManager {

	private String name;
	private Processor p;
	
	public SessionManager(){

		p = new Processor(this);
	}
	
	@Regex
	public String process( HashMap<String, String> args){
		
		String status;
		
		status = p.run(args);
		
		return status;
		
	}
	
	public void export(){
		
		//logic for saving session to .txt file or DB
		
	}
	
	public boolean isRunning(){
		
		return p.isRunning();
		
	}
}
