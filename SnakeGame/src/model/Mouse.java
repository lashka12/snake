package model;

import java.util.Random;
import utilities.Constants;
import utilities.Direction;

/**
 * this class represents a mouse in the game that move randomly on the play
 * ground
 * 
 * @author L.A
 *
 */
public class Mouse extends Block {

	private Direction direction;
	private boolean eaten;

	/**
	 * full constructor
	 * 
	 * @param x - x position
	 * @param y - y position
	 */
	public Mouse(int x, int y) {
		super(x, y);
		stepsCount = 0;
		this.direction = Direction.UP;

	}

	/**
	 * moving the mouse one step up
	 */
	public void moveUp() {

		if (getY() > 0) {
			setY(getY() - 1);
		}

	}

	/**
	 * moving the mouse one step down
	 */
	public void moveDown() {

		if (getY() < Constants.GAME_HIGHT - 7) {
			setY(getY() + 1);
		}

	}

	/**
	 * moving the mouse one step left
	 */
	public void moveLeft() {

		if (getX() > 0) {
			setX(getX() - 1);
		}

	}

	/**
	 * moving the mouse one step right
	 */
	public void moveRight() {

		if (getX() < Constants.GAME_WIDTH - 7) {
			setX(getX() + 1);
		}

	}

	/**
	 * this method is called when ever the main timer of the game asks to , it
	 * updates the mouse position step at a time according to a specific algorithm
	 */
	public void update() {

		if (stepsCount == 0) {

			Random rand = new Random();
			int randomNum = rand.nextInt(4) + 1;

			while (true) {

				if ((direction == Direction.UP & randomNum == 2) || (direction == Direction.DOWN & randomNum == 2)
						|| (direction == Direction.LEFT & randomNum == 4)
						|| (direction == Direction.RIGHT & randomNum == 3)) {

					rand = new Random();
					randomNum = rand.nextInt(4) + 1;

				} else {
					break;
				}

			}

			switch (randomNum) {
			case 1:
				direction = Direction.UP;
				moveUp();
				break;
			case 2:
				direction = Direction.DOWN;
				moveDown();
				break;
			case 3:
				direction = Direction.LEFT;
				moveLeft();
				break;
			case 4:
				direction = Direction.RIGHT;
				moveRight();
				break;
			}

			stepsCount++;

		} else {

			switch (direction) {
			case UP:
				moveUp();
				break;
			case DOWN:
				moveDown();
				break;
			case LEFT:
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
			}

			if (stepsCount == 20) { // keep walking in the same direction 30 steps before changing direction

				Random rand = new Random();
				int randomNum = rand.nextInt(4) + 1;

				while (true) {

					if ((direction == Direction.UP & randomNum == 2) || (direction == Direction.DOWN & randomNum == 1)
							|| (direction == Direction.LEFT & randomNum == 4)
							|| (direction == Direction.RIGHT & randomNum == 3)) {

						rand = new Random();
						randomNum = rand.nextInt(4) + 1;

					} else {
						break;
					}

				}

				switch (randomNum) {
				case 1:
					direction = Direction.UP;
					moveUp();
					break;
				case 2:
					direction = Direction.DOWN;
					moveDown();
					break;
				case 3:
					direction = Direction.LEFT;
					moveLeft();
					break;
				case 4:
					direction = Direction.RIGHT;
					moveRight();
					break;
				}

				stepsCount = 0;
			}

			stepsCount++;

		}

	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	private static int stepsCount;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public static int getStepsCount() {
		return stepsCount;
	}

	public static void setStepsCount(int stepsCount) {
		Mouse.stepsCount = stepsCount;
	}

}
