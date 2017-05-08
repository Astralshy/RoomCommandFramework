package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;

@Regex(regex="(?i)\\s*hint\\s*")
public class HintCommand implements RegexHandler{
	private Context context;
	
	public HintCommand(Object c){
		context = (Context) c;
	}
	
	@Override
	public void process(Matcher m) throws Exception {
		try{
			context.getState().otherCommand();
			System.out.println("HINT");
		} catch(Exception e){
			System.out.println(e.getMessage());;
		}
	}
}
