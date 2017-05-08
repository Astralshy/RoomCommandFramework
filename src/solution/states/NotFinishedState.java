package solution.states;

public class NotFinishedState implements State{
	@Override
	public void registerCommand() {
		throw new RuntimeException("Already registered. To play as another user, end your session by using the command EXIT.");
	}

	@Override
	public void startCommand() {
		return;
	}

	@Override
	public void otherCommand(){
		return;
	}
}