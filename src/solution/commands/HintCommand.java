package solution.commands;

import java.util.regex.Matcher;

import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;

@Regex(regex="(?i)\\s*hint\\s*")
public class HintCommand implements RegexHandler{
	@Override
	public void process(Matcher m) throws Exception {
		System.out.println("hint");
	}
}
