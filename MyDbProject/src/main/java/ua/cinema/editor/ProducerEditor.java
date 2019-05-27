package ua.cinema.editor;

import java.beans.PropertyEditorSupport;

import ua.cinema.entity.Producer;
import ua.cinema.service.ProducerService;

public class ProducerEditor extends PropertyEditorSupport {
	
	private ProducerService producerService;

	public ProducerEditor(ProducerService producerService) {
		this.producerService = producerService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Producer producer=producerService.findOne(Integer.valueOf(text));
		setValue(producer);
	}
	
	

}
