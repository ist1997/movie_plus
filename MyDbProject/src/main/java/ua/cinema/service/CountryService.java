package ua.cinema.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.cinema.entity.Country;
import ua.cinema.filter.SimpleFilter;

public interface CountryService {
	
	List<Country> findAll();
	
	void delete(int id);
	
	Country findOne(int id);
	
	void save(Country entity);
	
	Country findByName(String name);

	Page<Country> findAll(Pageable pageable, SimpleFilter filter);
}
