package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;

@Regex(regex="(?i)\\s*go\\s+(\\w+)\\s*", priority=4)
public class GoCommand extends RegexHandler{	
		
	public GoCommand(Object target) {
		super(target);
	}

	@Override
	public void process(Matcher m) throws Exception {
		try{
			Context context = (Context) receiverObject;
			context.getState().otherCommand();
			System.out.println("GO " + m.group(1));
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
