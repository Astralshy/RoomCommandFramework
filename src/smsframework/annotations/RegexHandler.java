package smsframework.annotations;

import java.util.regex.Matcher;

public abstract class RegexHandler {
	protected Object receiverObject;
	public RegexHandler(Object target) {
		receiverObject = target;
	}
	public abstract void process(Matcher m) throws Exception;
}