package ua.cinema.service;

import java.util.List;

import ua.cinema.entity.Comment;

public interface CommentService {
	
	void delete(int id);
	
	Comment findOne(int id);
	
	void save(Comment entity);
	
	List<Comment> findAllByFilmID(int id);
	

}
