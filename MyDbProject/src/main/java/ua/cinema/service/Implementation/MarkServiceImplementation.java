package ua.cinema.service.Implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cinema.DTO.MarkDTO;
import ua.cinema.dao.MarkDao;
import ua.cinema.entity.Mark;
import ua.cinema.service.MarkService;

@Service
public class MarkServiceImplementation implements MarkService {

	@Autowired
	private MarkDao markDao;
	
	@Override
	public List<Mark> findAll(){
		return markDao.findAll();
	};
	
	@Override
	public void save(Mark entity) {
		markDao.save(entity);
	};
	
	@Override
	public List<Mark> findAllByFilmID(int id){
		return markDao.findAllById(id);
	};
	
	@Override
	public MarkDTO recalculateMarks(Integer filmId) {
		List<Mark>marks=findAllByFilmID(filmId);
		Double sumOfMarks=0d;
		for (Mark mark : marks) {
			sumOfMarks+=mark.getValue();
		}
		if(marks.size()!=0) {
			sumOfMarks/=marks.size();
		}
		return new MarkDTO(marks.size(), new BigDecimal(sumOfMarks).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	@Override
	public Mark findMarkByUserAndFilm(long userId, Integer filmId) {
		return markDao.findByUserAndFilm(userId,filmId);
	}
}
