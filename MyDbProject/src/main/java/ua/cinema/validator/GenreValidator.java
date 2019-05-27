package ua.cinema.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.cinema.entity.Genre;
import ua.cinema.service.GenreSrvice;

public class GenreValidator implements Validator{
	
	private final GenreSrvice genreSrvice;

	public GenreValidator(GenreSrvice genreSrvice) {
		this.genreSrvice = genreSrvice;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Genre.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Genre genre =(Genre)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", " ", "This field can't be empty!");
		if(genreSrvice.findByType(genre.getType())!=null)
		{
			errors.rejectValue("type", " ", "This type is already exist!");
		}
		
	}
	
	

}
