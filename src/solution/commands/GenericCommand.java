package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;

@Regex(regex="(?i)\\s*(?!\\b(go|register|start)\\b)(\\w+)(\\s+(\\w+))?\\s*")
public class GenericCommand extends RegexHandler{
		
	public GenericCommand(Object target) {
		super(target);
	}

	@Override
	public void process(Matcher m) throws Exception {
		try{
			Context context = (Context) receiverObject;
			context.getState().otherCommand();
			if(m.group(4) == null){
				System.out.println("One word command: " + m.group(2));
			} else {
				System.out.println("Command with params: " + m.group(2) + " " + m.group(4));
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
