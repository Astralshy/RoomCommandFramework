package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;

@Regex(regex="(?i)\\s*go\\s+(\\w+)\\s*")
public class GoCommand implements RegexHandler{	
	private Context context;
	
	public GoCommand(Object c){
		context = (Context) c;
	}
	
	@Override
	public void process(Matcher m) throws Exception {
		try{
			context.getState().otherCommand();
			System.out.println("GO " + m.group(1));
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
