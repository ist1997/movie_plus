package ua.cinema.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.cinema.editor.GenreEditor;
import ua.cinema.entity.Genre;
import ua.cinema.filter.FilmFilter;
import ua.cinema.form.FilmForm;
import ua.cinema.service.FilmService;
import ua.cinema.service.GenreSrvice;

@Controller
@RequestMapping("films")
public class UserFilmController {
	
	@Autowired
	private FilmService filmService;
	
	
	@Autowired
	private GenreSrvice genreService;
	
	
	private static final int WEAK=1;
	private static final int MEDIUM=5;
	private static final int STRONG=7;
	private static final String WEAK_COLOR="#FF0000";
	private static final String MEDIUM_COLOR="#FF9900";
	private static final String STRONG_COLOR="#00680E";
	@InitBinder("films")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Genre.class, new GenreEditor(genreService));
		}
	
	@ModelAttribute("filter")
	public FilmFilter getFilter(){
		return new FilmFilter();
	}
	
	@ModelAttribute("films")
	public FilmForm getForm(){
		return new FilmForm();
	}
	
	@GetMapping
 	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") FilmFilter filter){
 		model.addAttribute("page", filmService.findAll(pageable, filter));
 		model.addAttribute("genres", genreService.findAll());
 		return "user-film";
 	}
	
	@RequestMapping(value="/checkStrength",method=RequestMethod.GET)
	public @ResponseBody String checkStrength(@RequestParam("password") String password){
		String result="<span style=\"color:%s;font-weight:bold;\">%s</span>";
		if(password.length()>=WEAK & password.length()<MEDIUM){
			return  String.format(result, WEAK_COLOR,"Weak");
		}
		if(password.length()>=MEDIUM & password.length()<STRONG){
			return  String.format(result, MEDIUM_COLOR,"Medium");
		}
		if(password.length()>=STRONG){
			return  String.format(result, STRONG_COLOR,"Strong");
		}
		else{
			return "";
		}
		
	}
	
}
