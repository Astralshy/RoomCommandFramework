package solution;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import room.RoomCommandManager;
import smsframework.SMSFramework;
import smsframework.annotations.Regex;
import smsframework.inputreader.InputReader;
import solution.db.entity.User;
import solution.db.repositories.UserRepository;
import solution.states.NotRegisteredState;
import solution.states.State;

public class Context {
	HashMap<String,Object> args;
	private State state;
	private String name;
	private int gameState;
	private String currentRoom;
	private RoomCommandManager rcm;
	private SMSFramework sms;
	private InputReader inputReader;
	private boolean isRunning;
	
	UserRepository ur;
		
	public Context(){
		inputReader = new InputReader();
		init();
	}
	
	public Context(String inputFilePath){
		inputReader = new InputReader(inputFilePath);
		init();
	}
	
	public void init(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(new String []{"applicationContext.xml", "applicationContext-jpa.xml"});
		ur = (UserRepository) ctx.getBean("userRepository");
		rcm = new RoomCommandManager();
		setState(new NotRegisteredState());
		isRunning = true;
	}
	
	public void setSMSFrameworkPath(String annotationsPackageName) throws Exception{
		sms = new SMSFramework(annotationsPackageName, this);
	}
	
	public void setInputReader(InputReader ir){
		inputReader = ir;
	}
	
	public String readerReadLine(){
		return inputReader.readLine();
	}
	
	public boolean sessionNotFinished(){
		return inputReader.readerNotFinished() && isRunning;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public void runCommand(String line) throws Exception{
		if(!(sms == null)){
			sms.processText(line);
		} else {
			throw new RuntimeException("No instance of SMSFramework found.");
		}
	}
	
	public void close(){
		
		try{
			state.startCommand();
			User u = ur.findByName(name).get(0);
			u.setName(name);
			u.setState(gameState);
			u.setCurrentRoom(currentRoom);
			ur.saveAndFlush(u);
		}catch(Exception e){
			isRunning = false;
		}
	}

	public boolean userExists(String name){
		User u;
		
		if(ur.findByName(name).isEmpty()){
			
			u = new User();
			u.setName(name);
			u.setState(0);
			u.setCurrentRoom("Room1");
			ur.saveAndFlush(u);
			gameState = u.getState();
			currentRoom = u.getCurrentRoom();
			this.name = u.getName();
			return false;
		}
		else{
			u = ur.findByName(name).get(0);
			gameState = u.getState();
			currentRoom = u.getCurrentRoom();
			this.name = u.getName();
			return true;
		}
	}
	
	public void resetGame(){
		gameState = 0;
		currentRoom = "Room1";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public String getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(String currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public RoomCommandManager getRcm() {
		return rcm;
	}


}