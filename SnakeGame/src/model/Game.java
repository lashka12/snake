package model;

import java.util.Date;

public class Game {

	private String nickName;
	private Date date;
	private int score;
	private double duration;
	private int numOfApples;
	private int numOfPears;
	private int numOfBannanas;
	private int numOfMouses;

	public Game(String nickName, Date date) {
		this.nickName = nickName;
		this.date = date;
		this.score = 0;
		this.duration = 0;
		this.numOfApples = 0;
		this.numOfBannanas = 0;
		this.numOfPears = 0;
		this.numOfMouses = 0;
	}

	
	public void incrementNumOfApples() {
		numOfApples++;
	}
	public void incrementNumOfPears() {
		numOfPears++;
	}
	public void incrementNumOfBannanas() {
		numOfBannanas++;
	}
	public void incrementNumOfMouse() {
		numOfMouses++;
	}
	public int getNumOfApples() {
		return numOfApples;
	}

	public void setNumOfApples(int numOfApples) {
		this.numOfApples = numOfApples;
	}

	public int getNumOfPears() {
		return numOfPears;
	}

	public void setNumOfPears(int numOfPears) {
		this.numOfPears = numOfPears;
	}

	public int getNumOfBannanas() {
		return numOfBannanas;
	}

	public void setNumOfBannanas(int numOfBannanas) {
		this.numOfBannanas = numOfBannanas;
	}

	public int getNumOfMouses() {
		return numOfMouses;
	}

	public void setNumOfMouses(int numOfMouses) {
		this.numOfMouses = numOfMouses;
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
