package solution.commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;

@Regex(regex="(?i)\\s*(\\w+)(\\s+(\\w+))?\\s*", priority=6)
public class GenericCommand extends RegexHandler{
		
	public GenericCommand(Object target) {
		super(target);
	}

	@Override
	public void process(Matcher m) throws Exception {
		try{
			Context context = (Context) receiverObject;
			context.getState().otherCommand();
			HashMap<String, Object> output;
			if(m.group(3) != null){
				output = context.getRcm().processRoom(context.getCurrentRoom(), context.getGameState(), m.group(1) + " " +m.group(3));
			}else{
				output = context.getRcm().processRoom(context.getCurrentRoom(), context.getGameState(), m.group(1));
			}
			context.setGameState((int)output.get("status"));
			System.out.print(output.get("message"));
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
