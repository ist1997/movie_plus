package ua.cinema.service;

import java.util.List;

import ua.cinema.DTO.MarkDTO;
import ua.cinema.entity.Mark;

public interface MarkService {
	
	List<Mark> findAll();
	
	void save(Mark entity);
	
	List<Mark> findAllByFilmID(int id);

	MarkDTO recalculateMarks(Integer filmId);

	Mark findMarkByUserAndFilm(long  userId, Integer filmId);
}
