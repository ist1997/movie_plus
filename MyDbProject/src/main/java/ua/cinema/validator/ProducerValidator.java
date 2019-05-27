package ua.cinema.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.cinema.form.ProducerForm;
import ua.cinema.service.ProducerService;

public class ProducerValidator implements Validator {
private final static Pattern REG = Pattern.compile("^[0-9]+$");
	
	private final ProducerService producerService;

	public ProducerValidator(ProducerService producerService) {
		this.producerService = producerService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ProducerForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProducerForm producerForm=(ProducerForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", " ", "This field can't be empty!");

		if(REG.matcher(producerForm.getName()).matches()){
			errors.rejectValue("name", "", "Enter only letters!");
		}
		
		if(!producerForm.getFile().getOriginalFilename().contains(".png") &&!producerForm.getFile().getOriginalFilename().contains(".jpg")){
			errors.rejectValue("file", " ", "Image must have only .png or .jpg format!");
		}
		
		if(!REG.matcher(producerForm.getAge()).matches()){
			errors.rejectValue("age", "", "Enter only digits!");
		}
		if(errors.getFieldError("age")==null){
			if(producerService.findUnique(producerForm.getName(),producerForm.getAge(),producerForm.getNationality())!=null)
					{
				
						errors.rejectValue("name", "", "Already exist!");
					}		
		}
		
	}
	
	
	
	


}
