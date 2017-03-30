package test;


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
		
		//Session starts
		while(true){
			String s = sm.process(sc.nextLine());
			System.out.print(s);
			
			if(!sm.isRunning() && sm.isRegistered()){
				
				
				break;
				
			}
		}
		sm.export();
	}
	
}
