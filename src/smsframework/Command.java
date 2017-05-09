package smsframework;

public class Command implements Comparable<Command>{
	private final String className;
	private final Integer priority;
	
	public Command(String c, int p){
		className = c;
		priority = p;
	}
	
	@Override
	public int compareTo(Command other) {
		return Integer.valueOf(priority).compareTo(other.priority);
	}

	public String getClassName() {
		return className;
	}
	
}
