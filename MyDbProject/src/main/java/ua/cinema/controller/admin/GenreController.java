package ua.cinema.controller.admin;

import static ua.cinema.util.ParamBuilder.getParams;

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

import ua.cinema.entity.Genre;
import ua.cinema.filter.SimpleFilter;
import ua.cinema.service.GenreSrvice;
import ua.cinema.validator.GenreValidator;

@Controller
@RequestMapping("/admin/genre")
@SessionAttributes("genre")
public class GenreController{
	

	@Autowired
	private GenreSrvice genreSrvice;
	
	@InitBinder("genre")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new GenreValidator(genreSrvice));
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@ModelAttribute("genre")
	public Genre getForm(){
		return new Genre();
	}
	
	@GetMapping
	public String show(Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", genreSrvice.findAll(pageable, filter));
		return "admin-genre";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter ){
		model.addAttribute("genre", genreSrvice.findOne(id));
		show(model,pageable,filter);
		return "admin-genre";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		genreSrvice.delete(id);
		return "redirect:/admin/genre"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("genre") @Valid Genre genre,BindingResult res, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(res.hasErrors())
		{
			return show(model,pageable,filter);
		}
		genreSrvice.save(genre);
		status.setComplete();
		return "redirect:/admin/genre"+getParams(pageable, filter);
	}
}
