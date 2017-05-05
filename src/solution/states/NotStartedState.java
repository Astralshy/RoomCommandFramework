package solution.states;

public class NotStartedState implements State{
	@Override
	public void RegisterCommand() {
		
	}

	@Override
	public void StartCommand() {
		System.out.println("User registered but no game started. Use the command START");
	}

	@Override
	public void GoCommand() {
		System.out.println("User registered but no game started. Use the command START");
	}

	@Override
	public void GenericCommand() {
		System.out.println("User registered but no game started. Use the command START");
	}

	@Override
	public void HintCommand() {
		System.out.println("User registered but no game started. Use the command START");
	}
}