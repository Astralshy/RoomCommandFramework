package solution.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import solution.db.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	public List<User> findByName(String s);
	
	@Query("select u from User u")
	public List<User> myQuery();
	
}
