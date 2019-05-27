package ua.cinema.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.cinema.DTO.MarkDTO;
import ua.cinema.entity.Mark;
import ua.cinema.service.FilmService;
import ua.cinema.service.MarkService;
import ua.cinema.service.UserService;

@Controller
public class MarkController {
	
	@Autowired
	private MarkService markService;
	@Autowired
	private FilmService filmService;
	@Autowired
	private UserService userService;
	
	@ModelAttribute("mark")
	public Mark getForm(){
		return new Mark();
	}
	
	@RequestMapping("/createMark")
	public @ResponseBody MarkDTO save(@RequestParam double rate, @RequestParam long userId,@RequestParam int filmID){
		Mark mark = markService.findMarkByUserAndFilm(userId,filmID);
		if(mark==null) {
			markService.save(new Mark(rate, userService.findOne(userId), filmService.findOne(filmID)));
		}
		else{
			mark.setValue(rate);
			markService.save(mark);
		}
		MarkDTO recalculatedData=markService.recalculateMarks(filmID);
		return recalculatedData;
	}
}
