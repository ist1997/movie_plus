package ua.cinema.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Mark {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="value")
	private double value;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private User userMark;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_film")
	private Film filmMark;

	public Mark() {
	}

	public Mark(double value,User user,Film film) {
		this.value=value;
		this.userMark=user;
		this.filmMark=film;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public User getUserMark() {
		return userMark;
	}

	public void setUserMark(User userMark) {
		this.userMark = userMark;
	}

	public Film getFilmMark() {
		return filmMark;
	}

	public void setFilmMark(Film filmMark) {
		this.filmMark = filmMark;
	}
	
}
