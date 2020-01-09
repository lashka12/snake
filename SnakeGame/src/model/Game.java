package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * this class represent a game while playing snake
 * 
 * @author Lawrence Ashkar
 *
 */
public class Game {

	private static Game singleton;
	private List<GameObserver> observers;
	private String nickName;
	private Date date;
	private Integer score;
	private int lives;
	private double duration;
	private HashMap<String, Integer> eatenObjects;
	private PlayGround playGround;
	private boolean over;
	private boolean paused;
	private Block lastEatenBlock;
	private int mode;

	/**
	 * constructor
	 */
	public Game() {

		if (singleton == null)
			singleton = this;
		observers = new ArrayList<GameObserver>();
		restart();

	}

	/**
	 * this method restart the game when needed
	 */
	public void restart() {

		singleton = this;
		this.playGround = new PlayGround();
		this.eatenObjects = new HashMap<String, Integer>();
		this.date = new Date();
		this.duration = 0;
		this.score = 0;
		this.lives = 3;
		this.setMode(-1);
		this.over = false;
		this.paused = false;
	

	}

	/**
	 * full constructor of the class
	 * 
	 * @param nickName     : player nickName
	 * @param date         : date and time of the game
	 * @param score        : game final score
	 * @param duration     : duration in sec
	 * @param eatenObjects : things that were eaten during the game
	 */
	public Game(String nickName, Date date, int score, double duration, HashMap<String, Integer> eatenObjects) {
		this.nickName = nickName;
		this.date = date;
		this.score = score;
		this.duration = duration;
		this.eatenObjects = eatenObjects;
	}

	/**
	 * this method is used to register a observer to the game
	 * 
	 * @param observer - the observer we wish to add
	 */
	public void register(GameObserver observer) {
		observers.add(observer);
	}

	/**
	 * this method is used to unRegister a observer to the game
	 * 
	 * @param observer - the observer we wish to remove
	 */
	public void unRegister(GameObserver observer) {
		observers.remove(observer);

	}

	/**
	 * notify all the observers of the game about a change in state when there is
	 * need
	 */
	private void notifyObservers() {

		for (GameObserver gameObserver : observers) {
			gameObserver.update();
		}

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
			toAdd = 30;

		if (!eatenObjects.containsKey(key))
			eatenObjects.put(key, 1);

		else
			eatenObjects.put(key, eatenObjects.get(key) + 1);

		score = getScore() + toAdd;
		setLastEatenBlock(eatenObject);
		notifyObservers();

	}

	/**
	 * this comparator is used to compare between two games based on the score this
	 * method is used whenever there is need to sort the games list
	 * 
	 * @return Comparator<Game>
	 */

	public static Comparator<Game> getCompByName() {
		Comparator<Game> comp = new Comparator<Game>() {
			@Override
			public int compare(Game g1, Game g2) {
				return g2.score.compareTo(g1.score);
			}
		};
		return comp;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public static Game getInstance() {
		return singleton;
	}

	public HashMap<String, Integer> getEatenObjects() {
		return eatenObjects;
	}

	public void setEatenObjects(HashMap<String, Integer> eatenObjects) {
		this.eatenObjects = eatenObjects;
	}

	public PlayGround getPlayGround() {
		return playGround;
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

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public Block getLastEatenBlock() {
		return lastEatenBlock;
	}

	public void setLastEatenBlock(Block lastEatenBlock) {
		this.lastEatenBlock = lastEatenBlock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [nickName=" + nickName + ", date=" + date + ", score=" + score + ", lives=" + lives + ", duration="
				+ duration + ", eatenObjects=" + eatenObjects + ", playGround=" + playGround + ", over=" + over
				+ ", paused=" + paused + "]";
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

}
