package ua.cinema.controller.user;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.cinema.DTO.MarkDTO;
import ua.cinema.filter.FilmFilter;
import ua.cinema.form.UserForm;
import ua.cinema.service.CommentService;
import ua.cinema.service.FilmService;
import ua.cinema.service.MarkService;
import ua.cinema.service.UserService;
import ua.cinema.validator.UserValidator;

@SessionAttributes("user")
@Controller
@Transactional
public class IndexController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private MarkService markService;
	
	@InitBinder("user")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new UserValidator(userService));
		}
	
	@ModelAttribute("filter")
	public FilmFilter getFilter(){
		return new FilmFilter();
	}
	
	

	@GetMapping("/")
	public String index(Principal principal,Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") FilmFilter filter){
		if(principal!=null){
			System.out.println(principal.getName());
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		model.addAttribute("page", filmService.findAll(pageable, filter));
 		return "user-index";
	}
	
	@GetMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user",new UserForm());
		return "user-registration";
	}
	
	@PostMapping("/registration")
	 	public String save(@ModelAttribute("user") @Valid UserForm user,BindingResult res, SessionStatus status, Model model){
			if(res.hasErrors())
				{
					return "user-registration";
				}
			userService.save(user);
			status.setComplete();
	 		return "redirect:/login";
	 	}
	
	@GetMapping("/login")
	 	public String login(){	
	 		return "user-login";
	 	}
	
	@GetMapping("/info/{id}")
	public String filmInfo(@PathVariable int id, Model model){
		MarkDTO markInfo=markService.recalculateMarks(id);
		model.addAttribute("filmInfo", filmService.findForm(id));
		model.addAttribute("comments",commentService.findAllByFilmID(id));
		model.addAttribute("amountOfMarks", markInfo.getAmount());
		model.addAttribute("markRate", markInfo.getSumOfMarks());
		return "user-info";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
	@GetMapping("/schedule")
	public String schedule(Model model)
	{
		model.addAttribute("films", filmService.findAll());
		return "user-schedule";
	}
	
	@RequestMapping(value="/imax") 
	public String imax(){
	return "user-imax"; 
	}
	
	@RequestMapping(value="/4DX") 
	public String dx4(){
	return "user-4DX"; 
	}
	
	
}
