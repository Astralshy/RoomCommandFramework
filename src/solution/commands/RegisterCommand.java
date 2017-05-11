package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;
import solution.states.NotFinishedState;
import solution.states.NotStartedState;

@Regex(regex="(?i)\\s*register\\s+(\\w+)\\s*", priority=1)
public class RegisterCommand extends RegexHandler{
		
	public RegisterCommand(Object target) {
		super(target);
	}

	@Override
	public void process(Matcher m) throws Exception {
		try{
			Context context = (Context) receiverObject;
			context.getState().registerCommand();
			if(!context.userExists(m.group(1))){
				context.setState(new NotStartedState());
				System.out.println("Welcome to DragonSMS, " + m.group(1) + ".");
			}
			else{
				context.setState(new NotFinishedState());
				System.out.println("Welcome back, " + m.group(1) + ".");
				System.out.print(context.getRcm().processRoom(context.getCurrentRoom(), context.getGameState(), "checkRoom").get("message"));
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
