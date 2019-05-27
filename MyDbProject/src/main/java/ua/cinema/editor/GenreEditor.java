package ua.cinema.editor;

import java.beans.PropertyEditorSupport;

import ua.cinema.entity.Genre;
import ua.cinema.service.GenreSrvice;

public class GenreEditor extends PropertyEditorSupport {
	
	private GenreSrvice genreSrvice;

	public GenreEditor(GenreSrvice genreSrvice) {
		this.genreSrvice = genreSrvice;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		Genre genre=genreSrvice.findOne(Integer.valueOf(text));
		setValue(genre);
	}
	
	

}
