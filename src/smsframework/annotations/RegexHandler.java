package smsframework.annotations;

import java.util.regex.Matcher;

public interface RegexHandler {
	public void process(Matcher m) throws Exception;
}