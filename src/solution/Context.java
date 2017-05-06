package solution;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import smsframework.inputreader.InputReader;
import solution.db.entity.User;
import solution.db.repositories.UserRepository;
import solution.states.NotRegisteredState;
import solution.states.State;

@Component
public class Context {
	HashMap<String,Object> args;
	private State state;
	public InputReader inputReader;
	
	@Autowired
	UserRepository ur;
	
	public Context(){
		state = new NotRegisteredState();
		inputReader = new InputReader();
	}
	
	public Context(String path){
		state = new NotRegisteredState();
		inputReader = new InputReader(path);
	}
	
	@PostConstruct
	public void runDB(){
		
	}
}