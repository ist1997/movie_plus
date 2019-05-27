package ua.cinema.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.cinema.entity.Country;
import ua.cinema.entity.Producer;
import ua.cinema.filter.ActorAndProducerFilter;
import ua.cinema.form.ProducerForm;

public interface ProducerService {

	List<Producer> findAll();
	
	void delete(int id);
	
	Producer findOne(int id);
	
	void save(ProducerForm entity);
	
	ProducerForm findForm(int id);
	
	Producer findUnique(String name, String age,
			Country country);
	
	Page<Producer> findAll(Pageable pageable, ActorAndProducerFilter filter);
}
