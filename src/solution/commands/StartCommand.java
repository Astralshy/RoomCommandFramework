package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;
import solution.states.NotFinishedState;

@Regex(regex="(?i)\\s*start\\s*", priority=2)
public class StartCommand extends RegexHandler{
	
	public StartCommand(Object target) {
		super(target);
	}

	@Override
	public void process(Matcher m) throws Exception {
		try{
			Context context = (Context) receiverObject;
			context.getState().startCommand();
			context.setState(new NotFinishedState());
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
