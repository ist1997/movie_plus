package ua.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.cinema.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	User findByEmail(String username);
	
	@Query("SELECT u FROM User u WHERE u.email=?1")
	User findUnique(String login);
	@Query("SELECT u FROM User u WHERE u.id=?1")
	User findOne(Long id);
	@Query("SELECT u FROM User u WHERE u.email=?1")
	User findByEamil(String email);

}
