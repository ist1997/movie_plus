package ua.cinema.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.cinema.entity.Film;

public interface FilmDao extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film> {

	@Query("SELECT DISTINCT f FROM Film f LEFT JOIN FETCH f.ganre LEFT JOIN FETCH f.nationality LEFT JOIN FETCH f.producer JOIN FETCH f.actors")
	List<Film> findAll();
	@Query("SELECT f FROM Film f LEFT JOIN FETCH f.ganre LEFT JOIN FETCH f.nationality LEFT JOIN FETCH f.producer JOIN FETCH f.actors WHERE f.id=?1")
	Film findOne(Integer id);
	@Query("SELECT f FROM Film f WHERE f.name=?1 and f.date=?2 and f.nationality.id=?3 and f.ganre.id=?4 and f.producer.id=?5 and f.price=?6 and f.duration=?7 and f.info=?8 and f.limitAge=?9")
	Film findUnique(String name, LocalDate date, Integer countryId,Integer genreId, Integer producerId,Integer price, Integer duration, String info, Integer limit);
	@Query(value="SELECT f FROM Film f LEFT JOIN FETCH f.ganre LEFT JOIN FETCH f.nationality LEFT JOIN FETCH f.producer",
			countQuery="SELECT count(f.id) FROM Film f")
	Page<Film> findAll(Pageable pageable);
}
