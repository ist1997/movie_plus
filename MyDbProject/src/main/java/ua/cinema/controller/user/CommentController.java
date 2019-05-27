package ua.cinema.controller.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.cinema.DTO.CommentTransfer;
import ua.cinema.entity.Comment;
import ua.cinema.service.CommentService;
import ua.cinema.service.FilmService;
import ua.cinema.service.UserService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private FilmService filmService;
	@Autowired
	private UserService userService;
	
	@ModelAttribute("comment")
	public Comment getForm(){
		return new Comment();
	}
	
	@RequestMapping("/createComment")
	public @ResponseBody CommentTransfer save(@RequestParam String text, @RequestParam long userId,@RequestParam int filmID){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
		Date date=new Date();
		commentService.save(new Comment(text,date,userService.findOne(userId),filmService.findOne(filmID)));
		CommentTransfer result=new CommentTransfer(text,dateFormat.format(date),userService.findOne(userId).getName(),userService.findOne(userId).getSecondName());
		return result;
	}
	@GetMapping("/deleteComment/{filmId}/{id}")
	public String delete(@PathVariable int filmId,@PathVariable int id){
		commentService.delete(id);
		return "redirect:/info/{filmId}";
	}
	
	
	
}
