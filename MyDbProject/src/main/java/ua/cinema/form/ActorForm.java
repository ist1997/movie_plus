package ua.cinema.form;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import ua.cinema.entity.Country;
import ua.cinema.entity.Film;

public class ActorForm {
	private int id;
	private String name;
	private String age;
	private Country nationality;
	private int version;
	private MultipartFile file;
	private Set<Film>films=new HashSet<Film>();

	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Country getNationality() {
		return nationality;
	}
	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}
	public Set<Film> getFilms() {
		return films;
	}
	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	
	
	

}
