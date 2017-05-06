package solution.states;

public class NotStartedState implements State{
	@Override
	public void registerCommand() {
		
	}

	@Override
	public void startCommand() {
		System.out.println("User registered but no game started. Use the command START");
	}

	@Override
	public void goCommand() {
		System.out.println("User registered but no game started. Use the command START");
	}

	@Override
	public void genericCommand() {
		System.out.println("User registered but no game started. Use the command START");
	}

	@Override
	public void hintCommand() {
		System.out.println("User registered but no game started. Use the command START");
	}
}