package model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Random;

import utilities.Constants;
import utilities.FruiteType;
import utilities.Level;

public class PlayGround {

	private HashMap<FruiteType, Fruit> fruits;
	private HashMap<Level, Question> questions;
	private int lastPearPos;
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

	/**
	 * this method adds a mouse to the playground
	 */
	public void addMouse() {

		Point p = getEmptyPoint();
		mouse = new Mouse(p.x, p.y);

	}

	/**
	 * this method adds a question to the playground
	 * 
	 * @param level : the level of the question wee need to add
	 */
	public void addQuestion(Level level) {
		Question q = SysData.popRandomQuestion(level);
		Point p = getEmptyPoint();
		q.setX(p.x);
		q.setY(p.y);
		q.setEaten(false);
		questions.put(level, q);
	}

	/**
	 * this method gives us an logic empty point on the play ground where we can
	 * place an object
	 * 
	 * @return empty point (x,y)
	 */
	public Point getEmptyPoint() {

		Point p = new Point((int) (Math.random() * (Constants.GAME_WIDTH-3)), (int) (Math.random() * (Constants.GAME_HIGHT-3)));

		// if point is not empty
		// loop till finding an empty point

		return p;

	}

	/**
	 * this method adds fruit object to the play ground logically
	 * 
	 * @param type : the type of the fruit needed
	 */
	public void addFruit(FruiteType type) {

		if (type.equals(FruiteType.PEAR)) {
			Point p = getCornerPoint();
			fruits.put(type, new Fruit(p.x, p.y, type));

		} else {

			Point p = getEmptyPoint();
			fruits.put(type, new Fruit(p.x, p.y, type));
		}
	}

	/**
	 * this method generate a random position on the board corners for the pear , it
	 * makes sure that the pear will not be in the same corner as the last position
	 * 
	 * @return random corner position
	 */

	private Point getCornerPoint() {

		Point p0 = new Point(2, 2);
		Point p1 = new Point(Constants.GAME_WIDTH - 4, 2);
		Point p2 = new Point(Constants.GAME_WIDTH - 4, Constants.GAME_HIGHT - 4);
		Point p3 = new Point(2, Constants.GAME_HIGHT - 4);
		Point newPoint = null;
		Random rand = new Random();
		int n = rand.nextInt(3) + 1;

		switch (lastPearPos) {
		case 0: {

			if (n == 1) {
				newPoint = p1;
				lastPearPos = 1;
			}
			if (n == 2) {
				newPoint = p2;
				lastPearPos = 2;
			}
			if (n == 3) {
				newPoint = p3;
				lastPearPos = 3;
			}
			break;
		}
		case 1: {

			if (n == 1) {
				newPoint = p0;
				lastPearPos = 0;
			}
			if (n == 2) {
				newPoint = p2;
				lastPearPos = 2;
			}
			if (n == 3) {
				newPoint = p3;
				lastPearPos = 3;
			}
			break;
		}
		case 2: {

			if (n == 1) {
				newPoint = p0;
				lastPearPos = 0;
			}
			if (n == 2) {
				newPoint = p1;
				lastPearPos = 1;

			}
			if (n == 3) {
				newPoint = p3;
				lastPearPos = 3;

			}
			break;
		}
		case 3: {

			if (n == 1) {
				newPoint = p0;
				lastPearPos = 0;

			}
			if (n == 2) {
				newPoint = p1;
				lastPearPos = 1;

			}
			if (n == 3) {
				newPoint = p2;
				lastPearPos = 2;

			}
			break;
		}
		}
		return newPoint;

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

	public Snake getSnake() {
		return snake;
	}

	public Mouse getMouse() {
		return mouse;
	}
}
