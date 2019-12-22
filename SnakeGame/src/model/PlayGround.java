package model;

import java.awt.Point;
import java.util.HashMap;
import utilities.Constants;
import utilities.FruiteType;
import utilities.Level;

public class PlayGround {

	private HashMap<FruiteType, Fruit> fruits;
	private HashMap<Level, Question> questions;
	private Snake snake;
	private Mouse mouse;
	private boolean hit;

	public PlayGround() {

		snake = new Snake(Constants.SNAKE_LENGTH);

		fruits = new HashMap<FruiteType, Fruit>();
		for (FruiteType type : FruiteType.values())
			addFruit(type);

		questions = new HashMap<Level, Question>();
		for (Level level : Level.values())
			addQuestion(level);

		addMouse();
		setHit(false);

	}

	public void addMouse() {

		Point p = getEmptyPoint();
		mouse = new Mouse(p.x, p.y);

	}

	public void addQuestion(Level level) {
		Question q = SysData.popRandomQuestion(level);
		Point p = getEmptyPoint();
		q.setX(p.x);
		q.setY(p.y);
		q.setEaten(false);
		questions.put(level, q);
	}

	public Point getEmptyPoint() {

		Point p = new Point((int) (Math.random() * Constants.GAME_WIDTH), (int) (Math.random() * Constants.GAME_HIGHT));

		// if point is not empty
		// loop till finding an empty point

		return p;

	}

	public Snake getSnake() {
		return snake;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void addFruit(FruiteType type) {

		Point p = getEmptyPoint();
		fruits.put(type, new Fruit(p.x, p.y, type));

	}

	public HashMap<FruiteType, Fruit> getFruits() {

		return fruits;
	}

	public HashMap<Level, Question> getQuestions() {
		return questions;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

}
