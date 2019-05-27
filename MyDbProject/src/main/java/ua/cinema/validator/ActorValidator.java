package ua.cinema.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.cinema.form.ActorForm;
import ua.cinema.service.ActorService;

public class ActorValidator implements Validator {
	
	private final static Pattern REG = Pattern.compile("^[0-9]+$");
	
	private final  ActorService actorService;

	public ActorValidator(ActorService actorService) {
		this.actorService = actorService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ActorForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ActorForm actorForm=(ActorForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", " ", "This field can't be empty!");
		
		if(!REG.matcher(actorForm.getAge()).matches()){
			errors.rejectValue("age", " ", "Enter only numbers!");
		}
		
		if(!actorForm.getFile().getOriginalFilename().contains(".png") &&!actorForm.getFile().getOriginalFilename().contains(".jpg")){
			errors.rejectValue("file", " ", "Image must have only .png or .jpg format!");
		}
		
		if(REG.matcher(actorForm.getName()).matches()){
			errors.rejectValue("name", " ", "Enter only letters!");
		}
		
		if(errors.getFieldError("age")==null){
			if(actorService.findUnique(actorForm.getName(), actorForm.getAge(),
					actorForm.getNationality())!=null)
					{
				
						errors.rejectValue("name", " ", "Already exist!");
					}		
		}
		
	}

	
}


