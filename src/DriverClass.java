

import java.util.HashMap;
import java.util.Scanner;

import annotations.Driver;
import annotations.Logic;
import annotations.Manager;
import components.SessionManager;

@Driver
public class DriverClass {

	@Manager
	SessionManager sm;
	
	@Logic
	public void run(){
		
		Scanner sc = new Scanner(System.in);
		
		//sm.init("name");
		
		while(!sm.isRunning()){
			
			
		}
		//Session starts
		while(sm.isRunning()){
			
			HashMap<String,String> args = new HashMap<String,String>();
			
			String[] lines = sc.nextLine().split(" ");
			
			args.put("cmd", lines[0]);
			if(lines.length == 2){
				
				args.put("params", lines[1]);
				
			}
			else{
				
				args.put("params", "");
				
			}
			
			String s = sm.process(args);
			System.out.println(s);
			
		}
		
		//sm.export();
	}
	
}
