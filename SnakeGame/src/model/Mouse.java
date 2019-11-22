package model;

import java.util.Random;
import utilities.Constants;
import utilities.Direction;

public class Mouse extends Block {

	private Direction direction;
	private static int stepsCount;

	public Mouse(int x, int y) {
		super(x, y, Constants.MOUSE_RIGHT_IMAGE);

		stepsCount = 0;
		setTranslateX(getPosX() * Constants.BLOCK_SIZE);
		setTranslateY(getPosY() * Constants.BLOCK_SIZE);
		this.direction = Direction.UP;

	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void update() { // update position

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

			if (stepsCount == 30) { // keep walking in the same direction 10 steps before changing direction

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

		updatePosition();

	}

	// ---------------------------------------Control-----------------------------------

	private void moveUp() {

		if (getPosY() > 4) {
			setPosY(getPosY() - 1);
		}
		setImage(Constants.MOUSE_UP_IMAGE);

	}

	private void moveDown() {

		if (getPosY() < Constants.GAME_HIGHT - 9) {
			setPosY(getPosY() + 1);
		}
		setImage(Constants.MOUSE_DOWN_IMAGE);

	}

	private void moveLeft() {

		if (getPosX() > 4) {
			setPosX(getPosX() - 1);
		}

		setImage(Constants.MOUSE_LEFT_IMAGE);

	}

	private void moveRight() {

		if (getPosX() < Constants.GAME_WIDTH - 9) {
			setPosX(getPosX() + 1);
		}
		setImage(Constants.MOUSE_RIGHT_IMAGE);

	}

	public void updatePosition() { // visualy

		setTranslateX(getPosX() * Constants.BLOCK_SIZE);
		setTranslateY(getPosY() * Constants.BLOCK_SIZE);

	}

	// ---------------------------------------------------------------------------------

	public static int getStepsCount() {
		return stepsCount;
	}

	public static void setStepsCount(int stepsCount) {
		Mouse.stepsCount = stepsCount;
	}

}
