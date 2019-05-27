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

import ua.cinema.editor.CountryEditor;
import ua.cinema.entity.Country;
import ua.cinema.filter.ActorAndProducerFilter;
import ua.cinema.form.ProducerForm;
import ua.cinema.service.CountryService;
import ua.cinema.service.ProducerService;
import ua.cinema.util.ParamBuilder;
import ua.cinema.validator.ProducerValidator;

@Controller
@RequestMapping("/admin/producer")
@SessionAttributes("producer")
public class ProducerController{
	
	@Autowired
	private ProducerService producerService;
	@Autowired
	private CountryService countryService;
	
	@InitBinder("producer")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.setValidator(new ProducerValidator(producerService));
		}
	
	@ModelAttribute("filter")
	public ActorAndProducerFilter getFilter(){
		return new ActorAndProducerFilter();
	}
	
	@ModelAttribute("producer")
	public ProducerForm getForm(){
		return new ProducerForm();
	}
	
	@GetMapping
 	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ActorAndProducerFilter filter){
		model.addAttribute("page", producerService.findAll(pageable, filter));
 		model.addAttribute("countries", countryService.findAll());
 		return "admin-producer";
 	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") ActorAndProducerFilter filter){
		producerService.delete(id);
		return "redirect:/admin/producer"+getParams(pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ActorAndProducerFilter filter){
		model.addAttribute("producer", producerService.findForm(id));
		show(model,pageable,filter);
		return "admin-producer";
	}
	
	
	@PostMapping
	public String save(@ModelAttribute("producer") @Valid ProducerForm producer,BindingResult res, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") ActorAndProducerFilter filter){
		if(res.hasErrors())
		{
			return show(model,pageable,filter);
		}
		producerService.save(producer);
		status.setComplete();
		return "redirect:/admin/producer"+getParams(pageable, filter);
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
}
