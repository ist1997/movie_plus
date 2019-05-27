package ua.cinema.filter;

import java.util.ArrayList;
import java.util.List;

public class FilmFilter {
	
	private String search="";
	
	private String max = "";
	
	private String min = "";
	
	private Integer maxValue;
	
	private Integer minValue;
	
	private List<Integer> genresId = new ArrayList<>();

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public Integer getMinValue() {
		return minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

	public List<Integer> getGenresId() {
		return genresId;
	}

	public void setGenresId(List<Integer> genresId) {
		this.genresId = genresId;
	}
	
	
	
	


	
	
}
