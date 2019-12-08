package model;

import java.util.Date;
import java.util.HashMap;

import utilities.Constants;
import utilities.FruiteType;

public class Game {

	private String nickName;
	private Date date;
	private int score;
	private double duration;
	private HashMap<String, Integer> eatenObjects;


	public Game(String nickName, Date date) {
		this.nickName = nickName;
		this.date = date;
		this.score = 0;
		this.duration = 0;
		eatenObjects.put("numOfApples", 0);
		eatenObjects.put("numOfPears", 0);
		eatenObjects.put("numOfBannanas", 0);
		eatenObjects.put("numOfMouses", 0);
	
	}

	
	/**
	 * a method to maintain game data history during the game
	 * @param name
	 */
	public void incrementNumOfEatenObjects(String name) {
		switch (name) {
			case "APPLE":
				String ap = "numOfApples";
				int numOfApples = eatenObjects.get(ap);
				eatenObjects.put(ap, numOfApples + 1);
				break;
			case "BANANA":
				String bn = "numOfBannanas";
				int numOfBannanas = eatenObjects.get(bn);
				eatenObjects.put(bn, numOfBannanas + 1);
				break;
			case "PEAR":
				String pr = "numOfPears";
				int numOfPears = eatenObjects.get(pr);
				eatenObjects.put(pr, numOfPears + 1);
				break;
			case "MOUSE":
				String ms = "numOfMouses";
				int numOfMouses = eatenObjects.get(ms);
				eatenObjects.put(ms, numOfMouses + 1);
				break;
			default:
				break;
		}
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
