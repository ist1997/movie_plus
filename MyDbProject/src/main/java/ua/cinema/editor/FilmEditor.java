package ua.cinema.editor;

import java.beans.PropertyEditorSupport;

import ua.cinema.service.FilmService;
import ua.cinema.entity.Film;

public class FilmEditor extends PropertyEditorSupport{
	
	private FilmService filmService;

	public FilmEditor(FilmService filmService) {
		this.filmService = filmService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Film film =filmService.findOne(Integer.valueOf(text));
		setValue(film);
	}
	
	

}
