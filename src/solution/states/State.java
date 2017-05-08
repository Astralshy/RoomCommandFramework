package solution.states;

public interface State {
	public void registerCommand();
	public void startCommand();
	public void otherCommand();
}