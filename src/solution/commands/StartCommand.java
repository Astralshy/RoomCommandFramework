package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;
import solution.states.NotFinishedState;

@Regex(regex="(?i)\\s*start\\s*")
public class StartCommand implements RegexHandler{
	private Context context;
	
	public StartCommand(Object c){
		context = (Context) c;
	}
	
	@Override
	public void process(Matcher m) throws Exception {
		try{
			context.getState().startCommand();
			context.setState(new NotFinishedState());
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
