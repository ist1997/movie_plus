package ua.cinema.DTO;

public class CommentTransfer {
	
	private String textOfComment;
	
	private String timeOfComment;
	
	private String  userName;
	
	private String userSecondName;

	

	public CommentTransfer(String textOfComment, String timeOfComment, String userName, String userSecondName) {
		this.textOfComment = textOfComment;
		this.timeOfComment = timeOfComment;
		this.userName = userName;
		this.userSecondName = userSecondName;
	}

	public CommentTransfer() {
	}

	public String getTextOfComment() {
		return textOfComment;
	}

	public void setTextOfComment(String textOfComment) {
		this.textOfComment = textOfComment;
	}

	public String getTimeOfComment() {
		return timeOfComment;
	}

	public void setTimeOfComment(String timeOfComment) {
		this.timeOfComment = timeOfComment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSecondName() {
		return userSecondName;
	}

	public void setUserSecondName(String userSecondName) {
		this.userSecondName = userSecondName;
	}


}
