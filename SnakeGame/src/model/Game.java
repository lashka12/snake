package model;

import java.util.Date;

public class Game {
	
	private String nickName;
	private Date date;
	private int score;
	private double duration;
	
	
	public Game(String nickName, Date date, int score, double duration) {
		this.nickName = nickName;
		this.date = date;
		this.score = score;
		this.duration = duration;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Game [nickName=" + nickName + ", date=" + date + ", score=" + score + ", duration=" + duration + "]";
	}
	
	
	
}
