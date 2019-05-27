package ua.cinema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.cinema.entity.Mark;

public interface MarkDao extends JpaRepository<Mark, Integer>, JpaSpecificationExecutor<Mark> {
	
	@Query("SELECT m FROM Mark m LEFT JOIN FETCH m.filmMark LEFT JOIN FETCH m.userMark")
	List<Mark> findAll();
	@Query("SELECT m FROM Mark m LEFT JOIN FETCH m.filmMark LEFT JOIN FETCH m.userMark"
			+ " WHERE m.filmMark.id=?1")
	List<Mark> findAllById(Integer id);
	@Query("SELECT m FROM Mark m LEFT JOIN FETCH m.filmMark LEFT JOIN FETCH m.userMark"
			+ " WHERE m.userMark.id=?1 AND m.filmMark.id=?2")
	Mark findByUserAndFilm(long userId,Integer filmID);
}
