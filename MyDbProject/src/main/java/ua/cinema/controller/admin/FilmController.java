package ua.cinema.controller.admin;





import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.cinema.editor.ActorEditor;
import ua.cinema.editor.CountryEditor;
import ua.cinema.editor.GenreEditor;
import ua.cinema.editor.ProducerEditor;
import ua.cinema.entity.Actor;
import ua.cinema.entity.Country;
import ua.cinema.entity.Genre;
import ua.cinema.entity.Producer;
import ua.cinema.filter.FilmFilter;
import ua.cinema.form.FilmForm;
import ua.cinema.service.ActorService;
import ua.cinema.service.CountryService;
import ua.cinema.service.FilmService;
import ua.cinema.service.GenreSrvice;
import ua.cinema.service.ProducerService;
import ua.cinema.util.ParamBuilder;
import ua.cinema.validator.FilmValidator;

@Controller
@RequestMapping("/admin/film")
@SessionAttributes("film")
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private GenreSrvice genreService;
	
	@Autowired
	private ProducerService producerService;
		
	@Autowired
	private ActorService actorService;
	
	@InitBinder("film")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.registerCustomEditor(Genre.class, new GenreEditor(genreService));
		binder.registerCustomEditor(Producer.class, new ProducerEditor(producerService));
		binder.registerCustomEditor(Actor.class, new ActorEditor(actorService));
		binder.setValidator(new FilmValidator(filmService));
		}
	
	@ModelAttribute("filter")
	public FilmFilter getFilter(){
		return new FilmFilter();
	}
	
	@ModelAttribute("film")
	public FilmForm getForm(){
		return new FilmForm();
	}
	
	@GetMapping
 	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") FilmFilter filter){
 		model.addAttribute("page", filmService.findAll(pageable, filter));
 		model.addAttribute("countries", countryService.findAll());
 		model.addAttribute("genres", genreService.findAll());
 		model.addAttribute("producers", producerService.findAll());
 		model.addAttribute("actors",actorService.findAll());
 		return "admin-film";
 	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") FilmFilter filter){
		filmService.delete(id);
		return "redirect:/admin/film"+getParams(pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") FilmFilter filter){
		model.addAttribute("film", filmService.findForm(id));
		show(model,pageable,filter);
		return "admin-film";
	}
	
	
	@PostMapping
	public String save(@ModelAttribute("film") @Valid FilmForm film,BindingResult res,Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") FilmFilter filter){
		if(res.hasErrors())
		{
			return show(model,pageable,filter);
		}
		
		filmService.save(film);
		status.setComplete();
		return "redirect:/admin/film"+getParams(pageable, filter);
		 	}
			
	private String getParams(Pageable pageable, FilmFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder buffer = new StringBuilder(page);
		if(!filter.getMax().isEmpty()){
			buffer.append("&max=");
			buffer.append(filter.getMax());
		}
		if(!filter.getMin().isEmpty()){
			buffer.append("&min=");
			buffer.append(filter.getMin());
		}
		if(!filter.getGenresId().isEmpty()){
			for (Integer id : filter.getGenresId()) {
				buffer.append("&genresId=");
				buffer.append(id);
			}
		}
		if(!filter.getSearch().isEmpty()){
			buffer.append("&search=");
			buffer.append(filter.getSearch());
		}
		
		return buffer.toString();
	}
	
}
