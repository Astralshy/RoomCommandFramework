package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;

@Regex(regex="(?i)\\s*go\\s+(\\w+)\\s*")
public class GoCommand implements RegexHandler{	
	@Override
	public void process(Matcher m) throws Exception {
		//String room = m.group(1);
		System.out.println("go");
	}
}
