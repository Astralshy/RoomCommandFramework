package solution.states;

public class NotRegisteredState implements State {
	@Override
	public void registerCommand() {
		return;
	}

	@Override
	public void startCommand() {
		throw new RuntimeException("Session not started. Use the command REGISTER <NAME>.");
	}

	@Override
	public void otherCommand(){
		throw new RuntimeException("Session not started. Use the command REGISTER <NAME>.");
	}
}