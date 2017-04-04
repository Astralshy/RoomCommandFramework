package test;


import java.util.HashMap;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import annotations.Driver;
import annotations.Logic;
import annotations.Manager;
import components.SessionManager;
import test.entity.User;
import test.repositories.UserRepository;

@Component
@Driver
public class DriverClass {

	static HashMap<String,Object> args;
	static boolean isNew;
	
	@Autowired
	UserRepository ur;
	
	@Manager
	static SessionManager sm;
	
	@Logic
	public void run(){
		
		Scanner sc = new Scanner(System.in);
		
		//Session starts
		while(true){
			String input = sc.nextLine();
			String s = sm.process(input);
			System.out.print(s);
			
			if(!sm.isRegistered()){
				runDB();
				if(sm.isRegistered()){
					s = sm.process(input);
					System.out.print(s);
				}
			}
			
			
			if(!sm.isRunning() && sm.isRegistered()){
				
				
				break;
				
			}
		}
		args = sm.export();
		runDB();
	}
	
	
	public void runDB(){
		
		AbstractApplicationContext ctx;
    	
        // load application context files
        ctx = new ClassPathXmlApplicationContext(new String []{"applicationContext.xml", "applicationContext-jpa.xml"});
        
	}
	
	@PostConstruct
	public void dbLogic(){
		
		//System.out.println(sm.isRegistered());
		if(!sm.isRegistered()){
			
			//System.out.println(sm.getName());
			if(!sm.getName().equals("")){
				if(ur.findByName(sm.getName()).size() != 0){
					User u = ur.findByName(sm.getName()).get(0);
					
					args = new HashMap<String,Object>();
					args.put("name", u.getName());
					args.put("room", u.getCurrentRoom());
					args.put("state",u.getState());
					
					sm.toggleRunning();
					sm.toggleNew();
				}
				else{
	
					args = new HashMap<String,Object>();
					args.put("name", sm.getName());
					args.put("room", "Room1");
					args.put("state",0);
					
					
				}
				
				sm.importUser(args);
				sm.toggleRegistered();
				
			}
			
		}
		else{
			User u;
			if(sm.isNew()){
				u = new User();
			}
			else{
				u = ur.findByName(sm.getName()).get(0);
			}
			u.setName((String)args.get("name"));
			u.setCurrentRoom((String)args.get("room"));
			u.setState((Integer)args.get("state"));
			ur.saveAndFlush(u);
			
			
		}
	}
}
