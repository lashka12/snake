package model;

import java.awt.Point;
import java.util.HashMap;
import utilities.Constants;
import utilities.FruiteType;

public class PlayGround {

	private HashMap<FruiteType, Fruit> fruits;
	private Snake snake;
	private Mouse mouse;
	private boolean hit;

	public PlayGround() { // set the height according to the main view size to be responsive


		snake = new Snake(Constants.SNAKE_LENGTH);
		fruits = new HashMap<FruiteType, Fruit>();
		this.setHit(false);
		addFruit(FruiteType.APPLE);
		addFruit(FruiteType.BANANA);
		addFruit(FruiteType.PEAR);
		addMouse();

	}


	public void addMouse() {

		Point p = getEmptyPoint();
		mouse = new Mouse(p.x, p.y);

	}

	public void addQuestion() {

//		Question q = SysData.popRandomQuestion();
//		Point p = getEmptyPoint();
//		q.setLayoutX(p.x * Constants.BLOCK_SIZE);
//		q.setLayoutY(p.y * Constants.BLOCK_SIZE);
//		getChildren().add(q);

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

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

}
