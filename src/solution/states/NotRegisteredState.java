package solution.states;

public class NotRegisteredState implements State {
	@Override
	public void registerCommand() {
		
	}
	
	@Override
	public void startCommand() {
		System.out.println("Session not started. Use the command REGISTER <NAME>");
	}

	@Override
	public void goCommand() {
		System.out.println("Session not started. Use the command REGISTER <NAME>");
	}

	@Override
	public void genericCommand() {
		System.out.println("Session not started. Use the command REGISTER <NAME>");
	}

	@Override
	public void hintCommand() {
		System.out.println("Session not started. Use the command REGISTER <NAME>");
	}
}