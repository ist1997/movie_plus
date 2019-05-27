package ua.cinema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.cinema.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
	
	@Query("SELECT c FROM Comment c LEFT JOIN FETCH c.filmComment LEFT JOIN FETCH c.userComment"
			+ " WHERE c.id=?1")
	List<Comment> findAllById(Integer id);
	@Query("SELECT c FROM Comment c LEFT JOIN FETCH c.filmComment LEFT JOIN FETCH c.userComment WHERE c.id=?1")
	Comment findOne(Integer id);
}
