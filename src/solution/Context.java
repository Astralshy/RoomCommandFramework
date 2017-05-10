package solution;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import smsframework.SMSFramework;
import smsframework.annotations.Regex;
import smsframework.inputreader.InputReader;
import solution.db.entity.User;
import solution.db.repositories.UserRepository;
import solution.states.NotRegisteredState;
import solution.states.State;

@Component
public class Context {
	HashMap<String,Object> args;
	private State state;
	private String name;
	private SMSFramework sms;
	private InputReader inputReader;
	
	@Autowired
	UserRepository ur;
		
	public Context(){
		setState(new NotRegisteredState());
		inputReader = new InputReader();
	}
	
	public Context(String inputFilePath){
		setState(new NotRegisteredState());
		inputReader = new InputReader(inputFilePath);
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
	
	public boolean readerNotFinished(){
		return inputReader.readerNotFinished();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}