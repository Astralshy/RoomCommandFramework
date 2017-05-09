package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;

@Regex(regex="(?i)\\s*(\\w+)(\\s+(\\w+))?\\s*", priority=5)
public class GenericCommand extends RegexHandler{
		
	public GenericCommand(Object target) {
		super(target);
	}

	@Override
	public void process(Matcher m) throws Exception {
		try{
			Context context = (Context) receiverObject;
			context.getState().otherCommand();
			if(m.group(3) == null){
				System.out.println("One word command: " + m.group(1));
			} else {
				System.out.println("Command with params: " + m.group(1) + " " + m.group(3));
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
