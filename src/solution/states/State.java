package solution.states;

public interface State {
	public void RegisterCommand();
	public void StartCommand();
	public void GoCommand();
	public void GenericCommand();
	public void HintCommand();
}
