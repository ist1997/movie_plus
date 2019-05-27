package ua.cinema.filter;

public class SimpleFilter {
	
	private String search="";

	public SimpleFilter(String search) {
		this.search = search;
	}

	public  SimpleFilter() {
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
	
	

}