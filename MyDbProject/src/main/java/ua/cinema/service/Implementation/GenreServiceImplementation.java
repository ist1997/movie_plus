package ua.cinema.service.Implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.cinema.dao.GenreDao;
import ua.cinema.entity.Genre;
import ua.cinema.filter.SimpleFilter;
import ua.cinema.service.GenreSrvice;
import ua.cinema.specification.GenreSpecification;
@Service
public class GenreServiceImplementation implements GenreSrvice {
	
	@Autowired
	private GenreDao genreDao;

	public List<Genre> findAll() {
		return genreDao.findAll();
	}

	public void delete(int id) {
		genreDao.delete(id);
	}

	public Genre findOne(int id) {
		return genreDao.findOne(id);
	}

	public void save(Genre genre) {
		genreDao.saveAndFlush(genre);
	}

	@Override
	public Genre findByType(String type) {
		return genreDao.findByType(type);
	}

	@Override
	public Page<Genre> findAll(Pageable pageable, SimpleFilter filter) {
		
		return genreDao.findAll(new GenreSpecification(filter),pageable);
	}

	@Override
	public List<Genre> findByTypeAll(String type) {
		List<Genre> allGenre = genreDao.findAll();
		List<Genre> returnlist = new ArrayList<Genre>();
		for(Genre next : allGenre){
			if(next.getType().toUpperCase().contains(type.toUpperCase())){
				returnlist.add(next);
			}
		}
		
		Collections.sort(returnlist,new Comparator<Genre>(){
			@Override
			public int compare(Genre g1,Genre g2){
				return g1.getType().compareTo(g2.getType());
			}
		});
		return returnlist;
	}

}
