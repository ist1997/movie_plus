package ua.cinema.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="film")
public class Film  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	@Column(name="premier")
	private LocalDate date;
	@Column(name="price")
	private int price;
	@Lob 
	@Column(name="info")
	private String info;
	@Column(name="duration")
	private int duration;
	@Column(name="limitAge")
	private int limitAge;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_country")
	private Country nationality;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_genre")
	private Genre ganre;
	@ManyToMany
	private Set<Actor>actors=new HashSet<Actor>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producer")
	private Producer producer;
	@Column(name="image_version")
	private int version;
	@OneToMany(mappedBy="filmComment")
	private List<Comment>comments=new ArrayList<Comment>();
	
	@OneToMany(mappedBy="filmMark")
	private List<Mark>marks=new ArrayList<>();
	
	@Transient
	private transient MultipartFile file;
	
	@Transient
	private transient MultipartFile videoFile;
	
	@Column(name="video_version")
	private int videoVersion;
	public Film(){
		
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getLimit() {
		return limitAge;
	}

	public void setLimit(int limit) {
		this.limitAge = limit;
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

	public int getLimitAge() {
		return limitAge;
	}

	public void setLimitAge(int limitAge) {
		this.limitAge = limitAge;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
	
	
	
	
}
