package ua.cinema.service;

import ua.cinema.entity.User;
import ua.cinema.form.UserForm;

public interface UserService {
	
	void save(UserForm user);
	
	User findUnique(String login);
	
	User findOne(long id);	
}
