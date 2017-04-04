package validation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import annotations.Regex;

@ValidationTarget(target = Regex.class)
public class RegexValidator implements Handler {

	private ArrayList<String> commands = new ArrayList<String>();
	public static String INVALID = "INVALID";
	
	public RegexValidator(){
		
		commands.add("GO");
		commands.add("REGISTER");
		commands.add("START");
		commands.add("HINT");
		commands.add("EXIT");
		
	}
	
	@Override
	public void process(Object o, Method m, Object[] args) throws Exception {
		
		String cmd = ((String)args[0]).trim();
		
		if(!cmd.equals("")){
		
			cmd = cmd.replaceAll("\\s+", " ");
			
			String[] list = cmd.split(" ");
			
			if(commands.contains(list[0].toUpperCase())){
				list[0] = list[0].toUpperCase();
			}
			
			String cleanString = list[0] + " ";
			
			if(list[0].equals("GO")){
				
				if(list[1].matches("(?i)(room[1-5])")){
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
			
			args[0] = cleanString;
			return;
		}
		
		args[0] = this.INVALID;
		
	}

}
