package ua.cinema.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.cinema.entity.Actor;
import ua.cinema.entity.Country;
import ua.cinema.entity.Film;
import ua.cinema.filter.ActorAndProducerFilter;
import ua.cinema.form.ActorForm;

public interface ActorService {
	
	List<Actor> findAll();
	
	void delete(int id);
	
	Actor findOne(int id);
	
	void save(ActorForm entity);
	
	ActorForm findForm(int id);
	
	Actor findUnique(String name, String age,
			Country country);
	
	Page<Actor> findAll(Pageable pageable, ActorAndProducerFilter filter);
	
	void addFilm(Film film,Actor actor);
	
}
