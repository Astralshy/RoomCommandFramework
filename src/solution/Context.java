package solution;

import smsframework.inputreader.InputReader;
import solution.states.NotRegisteredState;
import solution.states.State;

public class Context {
	private State state;
	public InputReader inputReader;
	
	public Context(){
		state = new NotRegisteredState();
		inputReader = new InputReader();
	}
	
	public Context(String path){
		state = new NotRegisteredState();
		inputReader = new InputReader(path);
	}
}