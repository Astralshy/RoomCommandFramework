package solution.states;

public class NotStartedState implements State{
	@Override
	public void registerCommand() {
		throw new RuntimeException("Already registered but no game found. To start a new game, use the command START.");
	}

	@Override
	public void startCommand() {
		return;
	}

	@Override
	public void otherCommand(){
		throw new RuntimeException("Already registered but no game found. To start a new game, use the command START.");
	}
}