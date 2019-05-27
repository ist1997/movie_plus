package ua.cinema.controller.admin;

import java.util.List;

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

import ua.cinema.editor.CountryEditor;
import ua.cinema.editor.FilmEditor;
import ua.cinema.entity.Country;
import ua.cinema.entity.Film;
import ua.cinema.filter.ActorAndProducerFilter;
import ua.cinema.form.ActorForm;
import ua.cinema.service.ActorService;
import ua.cinema.service.CountryService;
import ua.cinema.service.FilmService;
import ua.cinema.util.ParamBuilder;
import ua.cinema.validator.ActorValidator;

@Controller
@RequestMapping("/admin/actor")
@SessionAttributes("actor")
public class ActorController{
	
	@Autowired
	private ActorService actorService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private FilmService filmService;
	
	@InitBinder("actor")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.registerCustomEditor(Film.class, new FilmEditor(filmService));
		binder.setValidator(new ActorValidator(actorService));        
	}
	
	@ModelAttribute("filter")
	public ActorAndProducerFilter getFilter(){
		return new ActorAndProducerFilter();
	}
	
	@ModelAttribute("actor")
	public ActorForm getForm(){
		return new ActorForm();
	}
	
	@GetMapping
 	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ActorAndProducerFilter filter){
 		model.addAttribute("page", actorService.findAll(pageable, filter));
 		model.addAttribute("countries", countryService.findAll());
 		return "admin-actor";
 	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") ActorAndProducerFilter filter){
		actorService.delete(id);
		return "redirect:/admin/actor"+getParams(pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ActorAndProducerFilter filter){
		model.addAttribute("actor", actorService.findForm(id));
	
		show(model, pageable, filter);
		return "admin-actor";
	}
	
	@ModelAttribute("films")
    public List<Film> initializeProfiles() {
        return filmService.findAll();
    }
	
	
	@PostMapping
	public String save(@ModelAttribute("actor") @Valid ActorForm actor,BindingResult res, SessionStatus status, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ActorAndProducerFilter filter){
		if(res.hasErrors())
		{
			return show(model,pageable,filter);
		}
		actorService.save(actor);
		status.setComplete();
		return "redirect:/admin/actor"+getParams(pageable, filter);
		 	}
	
	private String getParams(Pageable pageable, ActorAndProducerFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder buffer = new StringBuilder(page);
		if(!filter.getSearch().isEmpty()){
			buffer.append("&search=");
			buffer.append(filter.getSearch());
		}
		
		if(!filter.getCountriesId().isEmpty()){
			for (Integer id : filter.getCountriesId()) {
				buffer.append("&countiresId=");
				buffer.append(id);
			}
		}
		return buffer.toString();
	}
	@GetMapping("/actor_film/{id}")
	public String actorFilm(@PathVariable int id, Model model){
		model.addAttribute("actorInfo", actorService.findForm(id));
		return "admin-actorFilms";
	}
	
	
}
	
