package validation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import annotations.Regex;

@ValidationAnnotation(target = Regex.class)
public class RegexValidator implements ValidationHandler {

	private String[] commands = {"GO","REGISTER","START","HINT","EXIT"};
	public static String INVALID = "INVALID";
	
	
	@Override
	public void process(Object o, Method m, Object[] args) throws Exception {
		
		String cmd = ((String)args[0]).trim();
		
		if(!cmd.equals("")){
		
			cmd = cmd.replaceAll("\\s+", " ");
			
			String[] list = cmd.split(" ");
			
			
			list[0] = list[0].toUpperCase();
			
			String cleanString = list[0] + " ";
			
			if(list[0].equals("GO")){
				
				//TODO: Validate Room<N> formatting
				if(list[1].matches("([rR]oom[1-5])")){
					list[1] = (Character.toString((list[1].charAt(0)))).toUpperCase() + list[1].substring(1).toLowerCase();
				}
				else{
					
					args[0] = this.INVALID;
					return;
					
				}
				
			}
	
			if(list.length > 1){
				
				cleanString += list[1];
				
			}
			
			System.out.println(cleanString);
			
			args[0] = cleanString;
			return;
		}
		
		args[0] = this.INVALID;
		
	}

}
