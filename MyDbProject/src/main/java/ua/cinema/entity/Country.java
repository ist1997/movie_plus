package ua.cinema.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="Country")
public class Country  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="nationality")
	private List<Actor>actors=new ArrayList<Actor>();
	
	@OneToMany(mappedBy="nationality")
	private List<Producer>producers=new ArrayList<Producer>();
	
	@OneToMany(mappedBy="nationality")
	private List<Film>films=new ArrayList<Film>();
	
	@Column(name="version")
	private int version;
	
	@Transient
	private transient MultipartFile file;
	 
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
	
	
	public List<Producer> getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Country(){
		
	}

	public Country(String name) {
		super();
		this.name = name;
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
}
