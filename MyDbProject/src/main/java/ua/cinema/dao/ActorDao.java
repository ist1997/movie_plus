package ua.cinema.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.cinema.entity.Actor;

public interface ActorDao extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor>{
	@Query("SELECT a FROM Actor a LEFT JOIN FETCH a.nationality ")
	List<Actor> findAll();
	@Query("SELECT a FROM Actor a LEFT JOIN FETCH a.nationality WHERE a.id=?1")
	Actor findOne(Integer id);
	@Query("SELECT a FROM Actor a WHERE a.name=?1 and a.age=?2 and a.nationality.id=?3")
	Actor findUnique(String name, Integer age, Integer countryId);
	
	@Query(value="SELECT a FROM Actor a LEFT JOIN FETCH a.nationality",
			countQuery="SELECT count(a.id) FROM Actor a")
	Page<Actor> findAll(Pageable pageable);
}
