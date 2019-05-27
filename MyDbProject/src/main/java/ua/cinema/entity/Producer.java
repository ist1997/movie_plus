package ua.cinema.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="producer")
public class Producer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_country")
	private Country nationality;
	@OneToMany(mappedBy="producer")
	private List<Film> films = new ArrayList<Film>();
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
	
	public Producer(){
		
	}

	public Producer(String name, int age, Country nationality, List<Film> films) {
		super();
		this.name = name;
		this.age = age;
		this.nationality = nationality;
		this.films = films;
	}

	public Producer(String name, int age, Country nationality) {
		super();
		this.name = name;
		this.age = age;
		this.nationality = nationality;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Country getNationality() {
		return nationality;
	}

	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	
}
