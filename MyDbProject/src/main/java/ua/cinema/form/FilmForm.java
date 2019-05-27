package ua.cinema.form;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import ua.cinema.entity.Actor;
import ua.cinema.entity.Country;
import ua.cinema.entity.Genre;
import ua.cinema.entity.Producer;

public class FilmForm {
	
	private int id;
	
	private String name;
	
	private String date;
	
	private String price;

	private Country nationality;
	
	private Genre ganre;
	
	private Producer producer;
	
	private String info;
	
	private String  duration;
	
	private String limit;
	
	private int version;
	
	private Set<Actor>actors=new HashSet<Actor>();
	
	
	private MultipartFile file;
	
	private  MultipartFile videoFile;
	
	private int videoVersion;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Country getNationality() {
		return nationality;
	}

	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}

	public Genre getGanre() {
		return ganre;
	}

	public void setGanre(Genre ganre) {
		this.ganre = ganre;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public Set<Actor> getActors() {
		return actors;
	}
	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
	public MultipartFile getVideoFile() {
		return videoFile;
	}
	public void setVideoFile(MultipartFile videoFile) {
		this.videoFile = videoFile;
	}
	public int getVideoVersion() {
		return videoVersion;
	}
	public void setVideoVersion(int videoVersion) {
		this.videoVersion = videoVersion;
	}	
		

}
