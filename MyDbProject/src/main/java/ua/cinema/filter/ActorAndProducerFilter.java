package ua.cinema.filter;

import java.util.ArrayList;
import java.util.List;

public class ActorAndProducerFilter  {
	
	private String search="";
	
	private List <Integer>countriesId=new ArrayList<Integer>();

	
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Integer> getCountriesId() {
		return countriesId;
	}

	public void setCountriesId(List<Integer> countriesId) {
		this.countriesId = countriesId;
	}
	
}
