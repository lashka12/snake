package model;

import java.util.Random;
import utilities.Constants;
import utilities.Direction;

public class Mouse extends Block {

	private Direction direction;
	private boolean eaten;

	public Mouse(int x, int y) {
		super(x, y);
		stepsCount = 0;
		this.direction = Direction.UP;

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

			if (stepsCount == 30) { // keep walking in the same direction 30 steps before changing direction

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

	public void moveUp() {

		if (getY() > 4) {
			setY(getY() - 1);
		}

	}

	public void moveDown() {

		if (getY() < Constants.GAME_HIGHT - 9) {
			setY(getY() + 1);
		}

	}

	public void moveLeft() {

		if (getX() > 4) {
			setX(getX() - 1);
		}

	}

	public void moveRight() {

		if (getX() < Constants.GAME_WIDTH - 9) {
			setX(getX() + 1);
		}

	}

	public static int getStepsCount() {
		return stepsCount;
	}

	public static void setStepsCount(int stepsCount) {
		Mouse.stepsCount = stepsCount;
	}

}
