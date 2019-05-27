package ua.cinema.entity;

import java.util.Date;

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
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Lob 
	@Column(name="text")
	private String textOfComment;
	
	@Column(name="time")
	private Date timeOfComment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private User userComment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_film")
	private Film filmComment;

	public Comment() {
	}
	
	

	public Comment(String textOfComment, Date timeOfComment, User userComment, Film filmComment) {
		this.textOfComment = textOfComment;
		this.timeOfComment = timeOfComment;
		this.userComment = userComment;
		this.filmComment = filmComment;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextOfComment() {
		return textOfComment;
	}

	public void setTextOfComment(String textOfComment) {
		this.textOfComment = textOfComment;
	}

	public Date getTimeOfComment() {
		return timeOfComment;
	}

	public void setTimeOfComment(Date timeOfComment) {
		this.timeOfComment = timeOfComment;
	}

	public User getUserComment() {
		return userComment;
	}

	public void setUserComment(User userComment) {
		this.userComment = userComment;
	}

	public Film getFilmComment() {
		return filmComment;
	}

	public void setFilmComment(Film filmComment) {
		this.filmComment = filmComment;
	}
	
	
}
