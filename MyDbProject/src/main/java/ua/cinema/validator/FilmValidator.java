package ua.cinema.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.cinema.form.FilmForm;
import ua.cinema.service.FilmService;

public class FilmValidator implements Validator {
	
	private final static Pattern REG = Pattern.compile("^[0-9]+$");
	
	private final static Pattern REGDATE=Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
	
	private final  FilmService filmService;

	public FilmValidator(FilmService filmService) {
		this.filmService = filmService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilmForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FilmForm filmForm=(FilmForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", " ", "This field can't be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "info", " ", "This field can't be empty!");

			if(!REGDATE.matcher(filmForm.getDate()).matches())
			{
				errors.rejectValue("date", "","Enter date like this: YYYY-MM-DD");
			}
		if(!REG.matcher(filmForm.getPrice()).matches()){
			errors.rejectValue("price", "", "Enter only digits!");
			}
		
		if(!REG.matcher(filmForm.getDuration()).matches()){
			errors.rejectValue("duration", "", "Enter only digits!");
			}
		
		if(!REG.matcher(filmForm.getLimit()).matches()){
			errors.rejectValue("limit", "", "Enter only digits!");
			}

		if(filmForm.getActors().size()==0){
			errors.rejectValue("actors", "", "Select at least one actor!");
			}
	
		if(!filmForm.getFile().getOriginalFilename().contains(".png") &&!filmForm.getFile().getOriginalFilename().contains(".jpg")){
			errors.rejectValue("file", " ", "Image must have only .png or .jpg format!");
		}
		
		if(!filmForm.getVideoFile().getOriginalFilename().contains(".mov")){
			errors.rejectValue("videoFile", " ", "Video must have only .mov  format!");
		}
		
		if(errors.getFieldError("date")==null){
			if(filmService.findUnique(filmForm.getName(),filmForm.getDate(),filmForm.getNationality(),filmForm.getGanre(),filmForm.getProducer(),filmForm.getPrice(),filmForm.getDuration(),filmForm.getInfo(),filmForm.getLimit())!=null)
					{
				
						errors.rejectValue("name", "", "Already exist!");
					}	
			
		}
		
	}
}
