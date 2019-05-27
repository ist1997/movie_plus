package ua.cinema.service.Implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.cinema.dao.CommentDao;
import ua.cinema.dao.FilmDao;
import ua.cinema.entity.Comment;
import ua.cinema.entity.Country;
import ua.cinema.entity.Film;
import ua.cinema.entity.Genre;
import ua.cinema.entity.Producer;
import ua.cinema.filter.FilmFilter;
import ua.cinema.form.FilmForm;
import ua.cinema.service.CommentService;
import ua.cinema.service.FileWriter;
import ua.cinema.service.FileWriter.Folder;
import ua.cinema.service.FilmService;
import ua.cinema.service.VideoWriter;
import ua.cinema.service.VideoWriter.FolderVideo;
import ua.cinema.specification.FilmSpecification;

@Service
public class FilmServiceImplementation implements FilmService {
	
	@Autowired
	private CommentService commentservice;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private FilmDao filmDao;
	
	@Autowired
	private FileWriter fileWriter;
	
	@Autowired
	private VideoWriter videoWriter;
	
	@Override
	public List<Film> findAll() {
		return filmDao.findAll();
	}
	
	@Override
	public void delete(int id) {
		for(Comment c :commentservice.findAllByFilmID(id)){
			commentDao.delete(c);
		}	
		filmDao.delete(id);
		
	}
	
	@Override
	public Film findOne(int id) {
		return filmDao.findOne(id);
	}
	
	@Override
	public void save(FilmForm form) {
		Film entity = new Film();
		entity.setName(form.getName());
		entity.setDate(LocalDate.parse(form.getDate()));
		entity.setNationality(form.getNationality());
		entity.setGanre(form.getGanre());
		entity.setPrice(Integer.valueOf(form.getPrice()));
		entity.setProducer(form.getProducer());
		entity.setId(form.getId());
		entity.setFile(form.getFile());
		entity.setVideoFile(form.getVideoFile());
		entity.setDuration(Integer.valueOf(form.getDuration()));
		entity.setInfo(form.getInfo());
		entity.setLimit(Integer.valueOf(form.getLimit()));
		entity.setActors(form.getActors());
		MultipartFile file = entity.getFile();
		entity = filmDao.saveAndFlush(entity);
		 if(fileWriter.write(Folder.FILM, file, entity.getId())){
			 entity.setVersion(entity.getVersion()+1);
			 filmDao.saveAndFlush(entity);
		 	}
		 MultipartFile videoFile = entity.getVideoFile();
		 entity = filmDao.saveAndFlush(entity);
		 if(videoWriter.write(FolderVideo.FILM, videoFile, entity.getId())){
			 entity.setVideoVersion(entity.getVideoVersion()+1);
			 filmDao.saveAndFlush(entity);
		 	}
	}

	@Override
	public FilmForm findForm(int id) {
		FilmForm form = new FilmForm();
		Film entity = filmDao.findOne(id);
		form.setName(entity.getName());
		form.setDate(String.valueOf(entity.getDate()));
		form.setGanre(entity.getGanre());
		form.setNationality(entity.getNationality());
		form.setId(entity.getId());	
		form.setPrice(String.valueOf(entity.getPrice()));
		form.setProducer(entity.getProducer());
		form.setDuration(String.valueOf(entity.getDuration()));
		form.setInfo(entity.getInfo());
		form.setLimit(String.valueOf(entity.getLimit()));
		form.setActors(entity.getActors());
		return form;
	}

	@Override
	public Film findUnique(String name, String date,  Country country,Genre genre, Producer producer,String price,String duration,String info,String limit) {
		return filmDao.findUnique(name, LocalDate.parse(date), country.getId(),genre.getId(),producer.getId(),Integer.valueOf(price),Integer.valueOf(duration),info,Integer.valueOf(limit));
	}

	@Override
	public Page<Film> findAll(Pageable pageable, FilmFilter filter) {
		
		return filmDao.findAll(new FilmSpecification(filter), pageable);
	}
	

}
