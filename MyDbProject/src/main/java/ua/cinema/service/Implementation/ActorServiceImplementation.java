package ua.cinema.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.cinema.dao.ActorDao;
import ua.cinema.entity.Actor;
import ua.cinema.entity.Country;
import ua.cinema.entity.Film;
import ua.cinema.filter.ActorAndProducerFilter;
import ua.cinema.form.ActorForm;
import ua.cinema.service.ActorService;
import ua.cinema.service.FileWriter;
import ua.cinema.service.FileWriter.Folder;
import ua.cinema.specification.ActorSpecification;

@Service
public class ActorServiceImplementation implements ActorService {
	
	@Autowired
	private ActorDao actorDao;

	@Autowired
	private FileWriter fileWriter;
	
	public List<Actor> findAll() {
		return actorDao.findAll();
	}

	public void delete(int id) {
		actorDao.delete(id);
		
	}
	public void addFilm(Film film,Actor actor){
		actor.getFilms().add(film);
		actorDao.save(actor);
	}

	public Actor findOne(int id) {
		return actorDao.findOne(id);
	}

	@Override
	public void save(ActorForm form) {
		Actor entity = new Actor();
		entity.setName(form.getName());
		entity.setAge(Integer.valueOf(form.getAge()));
		entity.setNationality(form.getNationality());
		entity.setFilms(form.getFilms());
		entity.setId(form.getId());
		entity.setFile(form.getFile());
		MultipartFile file = entity.getFile();
		entity = actorDao.saveAndFlush(entity);
		 if(fileWriter.write(Folder.ACTOR, file, entity.getId())){
			 entity.setVersion(entity.getVersion()+1);
			 actorDao.saveAndFlush(entity);
		 	}
		
	}

	@Override
	public ActorForm findForm(int id) {
		ActorForm form = new ActorForm();
		Actor entity = actorDao.findOne(id);
		form.setName(entity.getName());
		form.setAge(String.valueOf(entity.getAge()));
		form.setNationality(entity.getNationality());
		form.setFilms(entity.getFilms());
		form.setId(entity.getId());		
		return form;
	}

	@Override
	public Actor findUnique(String name, String age, Country country) {
		return actorDao.findUnique(name,Integer.valueOf(age), country.getId());
	}

	@Override
	public Page<Actor> findAll(Pageable pageable, ActorAndProducerFilter filter) {
		
		return actorDao.findAll(new ActorSpecification(filter), pageable);
	}

}
