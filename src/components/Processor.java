package components;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import room.RoomCommandManager;

public class Processor {

	private SessionManager instance;
	private String currentRoom;
	private int state;
	private String name;
	private boolean isRunning = false;
	private boolean isRegistered = false;
	private ArrayList<String> commands;
	private RoomCommandManager rm = new RoomCommandManager();
	
	public Processor(SessionManager sm){
		
		instance = sm;
		commands = new ArrayList<String>();
	}
	
	public String run(HashMap<String,String> args){
		
		HashMap<String,Object> output;
		String cmd = args.get("cmd");
		switch(cmd){
			
			case "REGISTER":
				name = args.get("params");
				if(name.equals("")){
					return "<NAME> Missing. Use the command REGISTER <NAME>";
				} else {
					isRunning = true;
					isRegistered = true;
					return "Hello "+name+", welcome to DragonSMS";
				}
			case "START":
				if(isRunning()){
					output = rm.processRoom("Room1", 0, "checkRoom");
					state = (int)output.get("status");
					currentRoom = "Room1";
					return (String)output.get("message");
					
				} else {
					return "Session not started. Use the command REGISTER <NAME>";
				}
			case "HINT":
				if(isRunning()){
					if(!currentRoom.equals("Room1")){
						output = rm.processRoom(currentRoom, state, "look");
						state = (int)output.get("status");
						return (String)output.get("message");
					}
					else{
						output = rm.processRoom(currentRoom, state, "checkRoom");
						state = (int)output.get("status");
						return (String)output.get("message");
					}
				} else {
					return "Session not started. Use the command REGISTER <NAME>";
				}
			case "GO":
				if(isRunning()){
					output = rm.processRoom(args.get("params"), state, "checkRoom");
					state = (int)output.get("status");
					currentRoom = args.get("params");
					return (String)output.get("message");
				} else {
					return "Session not started. Use the command REGISTER <NAME>";
				}
			case "EXIT":
				if(isRunning()){
					isRunning = false;
					return "Session ended. Thank you for playing, " + name + "!";
				} else {
					return "Session not started. Use the command REGISTER <NAME>";
				}
			default:
				try{
					output = rm.processRoom(currentRoom, state, args.get("cmd") + " " + args.get("params"));
					state = (int)output.get("status");
					return (String)output.get("message");
				}
				catch(Exception e){
					
					return "Command not recognized.";
					
				}
				
				
		}
	}
	
	public String getCurrentRoom() {
		return currentRoom;
	}

	public int getState() {
		return state;
	}
	
	public boolean isRunning(){
		
		return isRunning;
		
	}
	
	public boolean isRegistered(){
		
		return isRegistered;
	}
	
}
