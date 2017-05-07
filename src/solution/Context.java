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
	private SMSFramework sms;
	public InputReader inputReader;
	
	@Autowired
	UserRepository ur;
	
	public Context(){}
	
	public Context(String annotationsPackageName) throws Exception{
		setState(new NotRegisteredState());
		inputReader = new InputReader();
		sms = new SMSFramework(annotationsPackageName);
	}
	
	public Context(String annotationsPackageName, String inputFilePath) throws Exception{
		setState(new NotRegisteredState());
		inputReader = new InputReader(inputFilePath);
		sms = new SMSFramework(annotationsPackageName);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public void runCommand(String line) throws Exception{
		sms.processText(line);
	}
}