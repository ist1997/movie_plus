package ua.cinema.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.cinema.entity.Genre;
import ua.cinema.filter.SimpleFilter;

public interface GenreSrvice {

	List<Genre> findAll();
	
	void delete(int id);
	
	Genre findOne(int id);
	
	void save(Genre entity);

	Genre findByType(String type);
	
	List<Genre> findByTypeAll(String type);

	Page<Genre> findAll(Pageable pageable, SimpleFilter filter);
}
