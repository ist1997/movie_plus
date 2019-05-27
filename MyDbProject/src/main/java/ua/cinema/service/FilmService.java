package ua.cinema.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.cinema.entity.Country;
import ua.cinema.entity.Film;
import ua.cinema.entity.Genre;
import ua.cinema.entity.Producer;
import ua.cinema.filter.FilmFilter;
import ua.cinema.form.FilmForm;

public interface FilmService {

	List<Film> findAll();
	
	void delete(int id);
	
	Film findOne(int id);
	
	void save(FilmForm entity);
	
	FilmForm findForm(int id);
	
	Film findUnique(String name, String date,
			Country country,Genre genre,Producer producer,String price,String duration,String info,String limit);
	
	Page<Film> findAll(Pageable pageable, FilmFilter filter);
}
