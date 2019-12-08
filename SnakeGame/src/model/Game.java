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
		eatenObjects = new HashMap<String, Integer>();
		this.date = date;
		this.score = 0;
		this.duration = 0;

	}
	
	

	public Game(String nickName, Date date, int score, double duration, HashMap<String, Integer> eatenObjects) {
		super();
		this.nickName = nickName;
		this.date = date;
		this.score = score;
		this.duration = duration;
		this.eatenObjects = eatenObjects;
	}



	/**
	 * this method adds a eaten object to the game and update the score
	 * 
	 * @param eatenObject eaten object on playGround
	 */
	
	public void addEatenObject(Block eatenObject) {

		String key = eatenObject.getClass().getSimpleName();
		int toAdd = 0;

		if (eatenObject instanceof Fruit) {
			key = ((Fruit) eatenObject).getType().name();
			toAdd = ((Fruit) eatenObject).getType().getPoints();
		}

		if (eatenObject instanceof Mouse)
			toAdd = 20;

		if (eatenObject instanceof Question)
			toAdd = 0;// question points will be added when we implement questionPage Controller based
						// on the answer

		if (!eatenObjects.containsKey(key))
			eatenObjects.put(key, 1);

		else
			eatenObjects.put(key, eatenObjects.get(key) + 1);

		score = getScore() + toAdd;

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
