package smsframework;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;

public class SMSFramework {
	private HashMap<String, RegexHandler> map = new HashMap();
	
	public SMSFramework(String path, Context context) throws Exception{
		ScanResult results = new FastClasspathScanner(path).scan();
		List<String> allResults = results.getNamesOfClassesWithAnnotation(Regex.class);
		for(String s : allResults){
			Class c = Class.forName(s);
			Regex regexAnnotation = (Regex) c.getAnnotation(Regex.class);
			map.put(regexAnnotation.regex(), (RegexHandler) c.getDeclaredConstructor(Object.class).newInstance(context));
		}
	}

	public void processText(String line) throws Exception{
		for(String regex : map.keySet()){
			if(line.matches(regex)){
				RegexHandler rh = map.get(regex);
				if(rh != null){
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(line);
					if(m.find()){
						rh.process(m);
					}
				}
				return;
			}
		}
		System.out.println("Command not recognized");
	}
}