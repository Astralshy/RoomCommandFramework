package components;

import java.util.ArrayList;
import java.util.HashMap;

public class Processor {

	private SessionManager instance;
	private String currentRoom;
	private String state;
	private String name;
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
				String username = args.get("params");
				if(username.equals("")){
					return "<NAME> Missing. Use the command REGISTER <NAME>";
				} else {
					isRunning = true;
					return "Hello "+username+", welcome to DragonSMS";
				}
			case "START":
				if(isRunning()){
					
					
				} else {
					return "Session not started. Use the command REGISTER <NAME>";
				}
			case "HINT":
				if(isRunning()){
					
				} else {
					return "Session not started. Use the command REGISTER <NAME>";
				}
			case "GO":
				if(isRunning()){
					
				} else {
					return "Session not started. Use the command REGISTER <NAME>";
				}
			}
			
		}
			
		return "";
		
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
