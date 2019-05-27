package ua.cinema.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cinema.dao.CommentDao;
import ua.cinema.entity.Comment;
import ua.cinema.service.CommentService;

@Service
public class CommentServiceImplementation implements CommentService {
	
	@Autowired
	private CommentDao commentDao;


	public void delete(int id) {
		commentDao.delete(id);
	}

	public Comment findOne(int id) {
		return commentDao.findOne(id);
	}

	public void save(Comment comment) {
		commentDao.saveAndFlush(comment);
	}

	@Override
	public List<Comment> findAllByFilmID(int id) {
		return commentDao.findAllById(id);
	}

}
