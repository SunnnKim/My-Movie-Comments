package dto;

import java.util.Calendar;

import controller.MovieController;

public class CommentDTO {
	
	private int id;
	private int writerId;
	private int movieId;	
	private String title;	// 입력값 
	private String contents;	//입력값
	private String stars;		//입력값 
	private String writerName;
	private Calendar writtenTime;
	private Calendar updatedTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWriterId() {
		return writerId;
	}
	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getStars() {
		return stars;
	}
	// 점수를 넣으면 별로 출력해주는 setter
	public void setStars(int score) {
		stars = "";
		for(int i=1; i<=score ; i++ ) {
			stars += "★";
		}
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public Calendar getWrittenTime() {
		return writtenTime;
	}
	public void setWrittenTime(Calendar writtenTime) {
		this.writtenTime = writtenTime;
	}
	public Calendar getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Calendar updatedTime) {
		this.updatedTime = updatedTime;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	
	
	
	
	
}
