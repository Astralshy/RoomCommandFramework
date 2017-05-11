package solution.states;

import solution.Context;

public interface State {
	
	public void registerCommand();
	public void startCommand();
	public void otherCommand();
}