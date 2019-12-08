package model;

import java.util.Date;
import java.util.HashMap;



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
		eatenObjects = new HashMap<>();
		eatenObjects.put("numOfApples", 0);
		eatenObjects.put("numOfBannanas", 0);
		eatenObjects.put("numOfPears", 0);
		eatenObjects.put("numOfMouses", 0);


	}

	
	/**
	 * a method to maintain game data history during the game
	 * @param name
	 */
	public void incrementNumOfEatenObjects(String objectEaten) {
		switch (objectEaten) {
			case "APPLE":
				Integer numOfApples = eatenObjects.get("numOfApples");
				eatenObjects.put("numOfApples", numOfApples + 1);
				break;
			case "BANANA":
				Integer numOfBannanas = eatenObjects.get("numOfBannanas");
				eatenObjects.put("numOfBannanas", numOfBannanas + 1);
				break;
			case "PEAR":
				Integer numOfPears = eatenObjects.get("numOfPears");
				eatenObjects.put("numOfPears", numOfPears + 1);
				break;
			case "MOUSE":
				Integer numOfMouses = eatenObjects.get("numOfMouses");
				eatenObjects.put("numOfMouses", numOfMouses + 1);
				break;
			default:
				break;
		}
	}
	
	
	public void incrementScore(int pointsToAdd) {
		
		this.score = this.score + pointsToAdd;
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
