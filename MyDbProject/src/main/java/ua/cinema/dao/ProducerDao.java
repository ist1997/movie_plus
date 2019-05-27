package ua.cinema.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.cinema.entity.Producer;

public interface ProducerDao extends JpaRepository<Producer, Integer>, JpaSpecificationExecutor<Producer>{
	@Query("SELECT p FROM Producer p LEFT JOIN FETCH p.nationality")
	List<Producer> findAll();
	@Query("SELECT p FROM Producer p LEFT JOIN FETCH p.nationality WHERE p.id=?1")
	Producer findOne(Integer id);
	@Query("SELECT p FROM Producer p WHERE p.name=?1 and p.age=?2 and p.nationality.id=?3")
	Producer findUnique(String name, Integer age, Integer countryId);
	@Query(value="SELECT p FROM Producer p LEFT JOIN FETCH p.nationality",
			countQuery="SELECT count(p.id) FROM Producer p")
	Page<Producer> findAll(Pageable pageable);
}
