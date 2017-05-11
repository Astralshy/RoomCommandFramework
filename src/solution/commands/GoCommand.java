package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;

@Regex(regex="(?i)\\s*go\\s+(\\w+)\\s*", priority=5)
public class GoCommand extends RegexHandler{	
		
	public GoCommand(Object target) {
		super(target);
	}

	@Override
	public void process(Matcher m) throws Exception {
		try{
			Context context = (Context) receiverObject;
			context.getState().otherCommand();
			if(m.group(1).matches("Room[1-5]")){
				System.out.println("GO " + m.group(1));
			}
			else{
				System.out.println("Invalid Room.");
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
