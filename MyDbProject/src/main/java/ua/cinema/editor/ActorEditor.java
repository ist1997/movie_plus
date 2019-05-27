package ua.cinema.editor;

import java.beans.PropertyEditorSupport;

import ua.cinema.entity.Actor;
import ua.cinema.service.ActorService;

public class ActorEditor extends PropertyEditorSupport {
	
	private ActorService actorService;

	public ActorEditor(ActorService actorService) {
		this.actorService = actorService;
	}
	

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Actor actor=actorService.findOne(Integer.valueOf(text));
		setValue(actor);
	}
	
	
	
	

}
