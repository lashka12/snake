package model;

import utilities.Constants;
import utilities.Direction;

public class Segment extends Block {

	private int oldPosX, oldPosY;
	private Segment previous;
	private Direction direction = Direction.LEFT;

	public Segment(int x, int y, Segment prevBlock) {

		super(x, y, Constants.SNAKE_BODY_IMAGE);
		this.previous = prevBlock;

	}

	public void update() { // update position

		oldPosX = getPosX();
		oldPosY = getPosY();

		if (previous == null) { // this block is a head it has no previous
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

		} else { // not a head

			setPosX(previous.oldPosX);
			setPosY(previous.oldPosY);

		}
		updatePosition();
	}

	private void moveUp() {

		setPosY(getPosY() - 1);

		if (getPosY() < 0) {// it means snake hit a wall must change
			setPosY(Constants.GAME_HIGHT - 2);
		}

	}

	private void moveDown() {
		setPosY(getPosY() + 1);

		if (getPosY() >= Constants.GAME_HIGHT-1) { // it means snake hit a wall must change
			setPosY(0);
		}

	}

	private void moveLeft() {

		setPosX(getPosX() - 1);
		if (getPosX() < 0) {// it means snake hit a wall must change
			setPosX(Constants.GAME_WIDTH - 2);
		}
	}

	private void moveRight() {
		setPosX(getPosX() + 1);

		if (getPosX() >= Constants.GAME_WIDTH-1) {// it means snake hit a wall must change
			setPosX(0);
		}
	}

	public void updatePosition() { // visualy

		setTranslateX(getPosX() * Constants.BLOCK_SIZE);
		setTranslateY(getPosY() * Constants.BLOCK_SIZE);

	}

	public int getOldPosX() {
		return oldPosX;
	}

	public void setOldPosX(int oldPosX) {
		this.oldPosX = oldPosX;
	}

	public int getOldPosY() {
		return oldPosY;
	}

	public void setOldPosY(int oldPosY) {
		this.oldPosY = oldPosY;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Block getPrevious() {
		return previous;
	}

	public void setPrevious(Segment previous) {
		this.previous = previous;
	}

}
