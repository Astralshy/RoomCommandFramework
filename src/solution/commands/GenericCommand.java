package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;

@Regex(regex="(?i)\\s*(?!\\b(go|register)\\b)(\\w+)\\s+(\\w+)\\s*")
public class GenericCommand implements RegexHandler{
	@Override
	public void process(Matcher m) throws Exception {
		System.out.println("generic");
	}
}
