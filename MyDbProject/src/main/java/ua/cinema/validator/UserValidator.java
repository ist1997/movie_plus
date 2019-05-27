package ua.cinema.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.cinema.form.UserForm;
import ua.cinema.service.UserService;

public class UserValidator implements Validator {
	
	private final static Pattern REG = Pattern.compile("^[0-9]+$");
	private final static Pattern REGEMAIL=Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	private final  UserService userService;

	public UserValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm userForm=(UserForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", " ", "This field can't be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secondName", " ", "This field can't be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", " ", "This field can't be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", " ", "This field can't be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", " ", "This field can't be empty!");
		
		if(errors.getFieldError("mobile")==null){
			if(!REG.matcher(userForm.getMobile()).matches()){
				errors.rejectValue("mobile", " ", "Enter only numbers!");
			}
		}
		if(errors.getFieldError("email")==null){
			if(!REGEMAIL.matcher(userForm.getEmail()).matches()){
				errors.rejectValue("email", " ", "Wrong email!");
			}
		}
		
		if(REG.matcher(userForm.getName()).matches()){
			errors.rejectValue("name", " ", "Enter only letters!");
		}
		if(REG.matcher(userForm.getSecondName()).matches()){
			errors.rejectValue("secondName", " ", "Enter only letters!");
		}
		
		if(errors.getFieldError("mobile")==null){
			if(userService.findUnique(userForm.getEmail())!=null)
					{
				
						errors.rejectValue("email", " ", "user with this email  is already exist!");
					}		
		}
		
	}


}
