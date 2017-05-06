package solution.states;

public interface State {
	public void registerCommand();
	public void startCommand();
	public void goCommand();
	public void genericCommand();
	public void hintCommand();
}
