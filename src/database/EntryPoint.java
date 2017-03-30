package database;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import database.entity.User;
import database.repositories.UserRepository;

@Component
public class EntryPoint {

	@Autowired
	UserRepository repo;
	
	@PostConstruct
	public void run(){
		System.out.println("Run");
		
	if(false){
		User u = new User();
		u.setName("Alice");
		u.setAge(2018);
		u.setAddress("homeless");
		repo.save(u);
		
		User u2 = new User();
		u2.setName("Cody");
		u2.setAge(2018);
		u2.setAddress("homeless");
		repo.save(u2);
		
		
		
		User u4 = new User();
		u4.setName("Zebra");
		u4.setAge(2018);
		u4.setAddress("homeless");
		repo.save(u4);
		
	}
		
	
		//System.out.println(repo.findByName("Zebra"));
	
		for(User u: repo.myQuery()){
			
			System.out.println(u.getId() + ": " + u.getName());
			
		}
		
	}
	
	public Iterable<Long> findUserIdByName(String name){
		
		ArrayList<Long> returnList = new ArrayList<Long>();
		
		for(User u: repo.findAll()){
			
			if(u.getName().equals(name)){
				
				returnList.add(u.getId());
			}
			
		}
		
		return returnList;
	}
	
	public Iterable<Long> findDistinctUserIdByName(String name){
		
		return findDistinctUserIdByName(name,Sort.Direction.ASC);
		
	}
	
	public Iterable<Long> findDistinctUserIdByName(String name, Sort.Direction s){
		
		ArrayList<Long> returnList = new ArrayList<Long>();
		
		for(User u: repo.findAll(new Sort(s,"id"))){
			
			if(u.getName().equals(name)){
				
				returnList.add(u.getId());
				return returnList;
			}
			
		}
		
		return null;
	}
}
