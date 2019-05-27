package ua.cinema.editor;

import java.beans.PropertyEditorSupport;

import ua.cinema.entity.Country;
import ua.cinema.service.CountryService;

public class CountryEditor extends PropertyEditorSupport {

	private CountryService countryService;

	public CountryEditor(CountryService countryService) {
		this.countryService = countryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Country country=countryService.findOne(Integer.valueOf(text));
		setValue(country);
	}
	
	
}
