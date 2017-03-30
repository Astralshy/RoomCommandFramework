package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	public List<User> findByName(String s);
	public List<User> findByAgeGreaterThan(Integer age);
	
	@Query("select u from User u")
	public List<User> myQuery();
	
}
