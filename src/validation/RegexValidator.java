package validation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import annotations.Regex;

@ValidationAnnotation(target = Regex.class)
public class RegexValidator implements ValidationHandler {

	private String[] commands = {"GO","REGISTER","START","HINT","EXIT"};
	
	@Override
	public void process(Object o, Method m, Object[] args) throws Exception {
		
		String cmd = ((String)args[0]).trim();
		
		cmd = cmd.replaceAll("\\s+", " ");
		
		String[] list = cmd.split(" ");
		
		list[0] = list[0].toUpperCase();
		
		String cleanString = list[0] + " ";
		if(list.length > 1){
			
			cleanString += list[1];
			
		}
		
		args[0] = cleanString;
		
		
	}

}
