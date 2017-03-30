package components;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import components.entity.User;
import components.repositories.UserRepository;
import room.RoomCommandManager;

@Component
public class Processor {

	private static String currentRoom;
	private static int state;
	private static String name;
	private static boolean isRunning = false;
	private static boolean isRegistered = false;
	private static boolean isNew = true;
	private RoomCommandManager rm = new RoomCommandManager();
	
	@Autowired
	UserRepository rp;
	
	@PostConstruct
	public void run(){
		
		if(!isRegistered){
			
			
			if(rp.findByName(name).size() == 0){
				isNew = true;
			}
			else{
				User u = rp.findByName(name).get(0);
				isNew = false;
				state = u.getState();
				currentRoom = u.getCurrentRoom();
				
			}
			isRegistered = true;
		}
		else{
			
			
			if(!isNew){
				User u = rp.findByName(name).get(0);
				u.setState(state);
				u.setCurrentRoom(currentRoom);
				rp.saveAndFlush(u);
			}
			else{
				User u = new User();
				u.setName(name);
				u.setState(state);
				u.setCurrentRoom(currentRoom);
				rp.saveAndFlush(u);
				
			}
			
			
		}
		
		
	}
	
	public String run(HashMap<String,String> args){
		
		HashMap<String,Object> output;
		String cmd = args.get("cmd");
		switch(cmd){
			
			case "REGISTER":
				name = args.get("params");
				if(name.equals("")){
					return "<NAME> Missing. Use the command REGISTER <NAME>\n";
				} else {
					startDB();
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
			case "START":
				if(isRunning()){
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
	
	private void startDB(){
		
		AbstractApplicationContext ctx;
    	
        // load application context files
        ctx = new ClassPathXmlApplicationContext(new String []{"applicationContext.xml", "applicationContext-jpa.xml"});
		ctx.close();
	}
	
	
	
	 
}
