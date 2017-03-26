package components;

import java.util.ArrayList;
import java.util.HashMap;

public class Processor {

	private SessionManager instance;
	private String currentRoom;
	private String state;
	private boolean isRunning = false;
	private boolean isRegistered = false;
	private ArrayList<String> commands;
	
	public Processor(SessionManager sm){
		
		instance = sm;
		commands = new ArrayList<String>();
	}
	
	public String run(HashMap<String,String> args){
		
		String cmd = args.get("cmd");
		
		if(commands.contains(cmd)){
			
			//lol
			
		}
		else{
			
			switch(cmd){
			
			case "REGISTER":
				
				break;
			case "START":
				isRunning = true;
				break;
			case "HINT":
				break;
			case "GO":
				break;
			}
			
		}
			
		
	}
	
	public String getCurrentRoom() {
		return currentRoom;
	}

	public String getState() {
		return state;
	}
	
	public boolean isRunning(){
		
		return isRunning;
		
	}
	
}
