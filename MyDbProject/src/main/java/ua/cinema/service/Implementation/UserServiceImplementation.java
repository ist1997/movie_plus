package ua.cinema.service.Implementation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.cinema.dao.UserDao;
import ua.cinema.entity.Role;
import ua.cinema.entity.User;
import ua.cinema.form.UserForm;
import ua.cinema.service.UserService;

@Service("userDetailsService")
public class UserServiceImplementation implements UserService, UserDetailsService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder encoder;
	 	
	 
	@Override
	public void save(UserForm form) {
		User entity = new User();
		entity.setName(form.getName());
		entity.setSecondName(form.getSecondName());
		entity.setMobile(Float.valueOf(form.getMobile()));
		entity.setEmail(form.getEmail());
		entity.setId(form.getId());
		
		entity.setRole(Role.ROLE_USER);
		entity.setPassword(encoder.encode(form.getPassword()));
		userDao.save(entity);
		
	}


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		return userDao.findByEmail(userName);
		
	}
	
	@PostConstruct
	 	public void addAdmin(){
	 		User user = userDao.findByEmail("admin");
	 		if(user==null){
	 			user = new User();
	 			user.setEmail("admin");
	 			user.setPassword(encoder.encode("admin"));
	 			user.setRole(Role.ROLE_ADMIN);
	 			userDao.save(user);
	 		}
	 	}


	@Override
	public User findUnique(String login) {
		return userDao.findUnique(login);
	}


	@Override
	public User findOne(long id) {
		return userDao.findOne(id);
	}

}
