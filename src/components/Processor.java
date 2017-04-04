package components;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import room.RoomCommandManager;

public class Processor {

	private static String currentRoom;
	private static int state;
	private static String name = "";
	private static boolean isRunning = false;
	private static boolean isRegistered = false;
	private static boolean isNew = true;
	private RoomCommandManager rm = new RoomCommandManager();
	
	
	public String run(HashMap<String,String> args){
		
		HashMap<String,Object> output;
		String cmd = args.get("cmd");
		switch(cmd){
			
			case "REGISTER":
				name = args.get("params");
				if(name.equals("")){
					return "<NAME> Missing. Use the command REGISTER <NAME>\n";
				} else {
					
					if(isRegistered){
						isRunning = true;
						if(isNew){
						return "Hello "+name+", welcome to DragonSMS\n";
						}
						else{
							if(!currentRoom.equals("Room1")){
								output = rm.processRoom(currentRoom, state, "look");
								state = (int)output.get("status");
							}
							else{
								output = rm.processRoom(currentRoom, state, "checkRoom");
								state = (int)output.get("status");
							}
						return "Hello "+name+", welcome back to DragonSMS \n" + output.get("message");
						}	
					}
					else{
						
						return "";
						
					}
				}
			case "START":
				if(isRunning()){
					isRunning = true;
					output = rm.processRoom("Room1", 0, "checkRoom");
					state = (int)output.get("status");
					currentRoom = "Room1";
					return (String)output.get("message");
					
				} else {
					return "Session not started. Use the command REGISTER <NAME>\n";
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
					return "Session not started. Use the command REGISTER <NAME>\n";
				}
			case "GO":
				if(isRunning()){
					output = rm.processRoom(args.get("params"), state, "checkRoom");
					state = (int)output.get("status");
					currentRoom = args.get("params");
					return (String)output.get("message");
				} else {
					return "Session not started. Use the command REGISTER <NAME>\n";
				}
			case "EXIT":
				if(isRunning()){
					isRunning = false;
					return "Session ended. Thank you for playing, " + name + "!\n";
				} else {
					return "Session not started. Use the command REGISTER <NAME>\n";
				}
			default:
				try{
					output = rm.processRoom(currentRoom, state, args.get("cmd") + " " + args.get("params"));
					state = (int)output.get("status");
					return (String)output.get("message");
				}
				catch(Exception e){
					
					return "Command not recognized.\n";
					
				}
				
				
		}
	}
	
	public String getName() {
		
		return name;
		
	}
	public boolean isRunning(){
		
		return isRunning;
		
	}
	
	public boolean isRegistered(){
		
		return isRegistered;
	}
	
	
	public HashMap<String,Object> export(){
		HashMap<String,Object> args = new HashMap<String,Object>();
		
		args.put("name", name);
		args.put("room", currentRoom);
		args.put("state", state);
		
		return args;
	}

	public void importUser(HashMap<String,Object> args){
		
		name = (String)args.get("name");
		currentRoom = (String)args.get("room");
		state = (int)args.get("state");
		
		
	}
	public boolean isNew(){
		
		return isNew;
		
	}
	
	public void toggleNew(){
		
		isNew = !isNew;
		
	}
	
	public void toggleRegistered(){
		
		isRegistered = !isRegistered;
		
	}
	
	public void toggleRunning(){
		
		isRunning = !isRunning;
	}
	
	 
}
