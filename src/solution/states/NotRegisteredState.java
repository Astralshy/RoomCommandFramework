package solution.states;

public class NotRegisteredState implements State {
	@Override
	public void RegisterCommand() {
		
	}
	
	@Override
	public void StartCommand() {
		System.out.println("Session not started. Use the command REGISTER <NAME>");
	}

	@Override
	public void GoCommand() {
		System.out.println("Session not started. Use the command REGISTER <NAME>");
	}

	@Override
	public void GenericCommand() {
		System.out.println("Session not started. Use the command REGISTER <NAME>");
	}

	@Override
	public void HintCommand() {
		System.out.println("Session not started. Use the command REGISTER <NAME>");
	}
}