package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;

@Regex(regex="(?i)\\s*hint\\s*", priority=3)
public class HintCommand extends RegexHandler{
	
	public HintCommand(Object target) {
		super(target);
	}

	@Override
	public void process(Matcher m) throws Exception {
		try{
			Context context = (Context) receiverObject;
			context.getState().otherCommand();
			System.out.println("HINT");
		} catch(Exception e){
			System.out.println(e.getMessage());;
		}
	}
}
