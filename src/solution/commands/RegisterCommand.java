package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;
import solution.states.NotStartedState;

@Regex(regex="(?i)\\s*register\\s+(\\w+)\\s*")
public class RegisterCommand extends RegexHandler{
		
	public RegisterCommand(Object target) {
		super(target);
	}

	@Override
	public void process(Matcher m) throws Exception {
		try{
			Context context = (Context) receiverObject;
			context.getState().registerCommand();
			/*TODO
			if(user does not exist in db)
				context.setState(new NotStartedState());
				sysout("Welcome to DragonSMS")
			else
				context.setState(new NotFinishedState());
				sysout("Welcome back to DragonSMS")
			*/
			context.setState(new NotStartedState());
			System.out.println("REGISTERING " + m.group(1));
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
