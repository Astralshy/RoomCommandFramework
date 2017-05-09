package smsframework;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import smsframework.annotations.Regex;
import smsframework.annotations.RegexHandler;
import solution.Context;

public class SMSFramework {
	private LinkedHashMap<String, RegexHandler> map = new LinkedHashMap<String, RegexHandler>();
	
	public SMSFramework(String path, Context context) throws Exception{
		ScanResult results = new FastClasspathScanner(path).scan();
		List<String> allResults = results.getNamesOfClassesWithAnnotation(Regex.class);
		PriorityQueue<Command> pq = new PriorityQueue<Command>();
		for(String s : allResults){
			Class c = Class.forName(s);
			Regex regexAnnotation = (Regex) c.getAnnotation(Regex.class);
			Command command = new Command(s, regexAnnotation.priority());
			pq.add(command);
		}
		while(!pq.isEmpty()){
			Command c = pq.poll();
			Class command = Class.forName(c.getClassName());
			Regex regexAnnotation = (Regex) command.getAnnotation(Regex.class);
			map.put(regexAnnotation.regex(), (RegexHandler) command.getDeclaredConstructor(Object.class).newInstance(context));
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