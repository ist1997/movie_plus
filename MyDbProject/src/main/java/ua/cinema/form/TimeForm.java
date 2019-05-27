package ua.cinema.form;

import java.util.HashSet;
import java.util.Set;

import ua.cinema.entity.Film;

public class TimeForm {
		
		private int id;
		
		private String time;
		
		private Set<Film>films=new HashSet<Film>();

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public Set<Film> getFilms() {
			return films;
		}

		public void setFilms(Set<Film> films) {
			this.films = films;
		}

				
		
		

}
