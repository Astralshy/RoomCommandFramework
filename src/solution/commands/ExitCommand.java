package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;
import solution.states.NotFinishedState;

@Regex(regex="(?i)\\s*exit\\s*", priority=3)
public class ExitCommand extends RegexHandler{
	
	public ExitCommand(Object target) {
		super(target);
	}

	@Override
	public void process(Matcher m) throws Exception {
		try{
			Context context = (Context) receiverObject;
			System.out.println("Exiting DragonSMS");
			context.close();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
