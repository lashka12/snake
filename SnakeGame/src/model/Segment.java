package model;

import java.util.concurrent.atomic.AtomicInteger;
import utilities.Direction;

public class Segment extends Block {

	private static AtomicInteger count ;
	private int id;
	private int oldX, oldY;
	private Segment previous;
	private Direction direction = Direction.LEFT;

	public Segment(int x, int y, Segment prevBlock) {
		super(x, y);
		this.id = count.getAndIncrement();
		this.previous = prevBlock;

	}

	public static void restartCounter() {
		count = new AtomicInteger(0);

	}

	public void move() { // update position

		
		
		
		oldX = getX();
		oldY = getY();

		if (previous == null) { // this block is a head it has no previous
			switch (direction) {
			case UP:
				setY(getY() - 1);
				break;
			case DOWN:
				setY(getY() + 1);
				break;
			case LEFT:
				setX(getX() - 1);
				break;
			case RIGHT:
				setX(getX() + 1);
				break;
			}

		} else { // not a head

			setX(previous.oldX);
			setY(previous.oldY);

		}

	}

	public int getOldX() {
		return oldX;
	}

	public void setOldX(int oldX) {
		this.oldX = oldX;
	}

	public int getOldY() {
		return oldY;
	}

	public void setOldY(int oldY) {
		this.oldY = oldY;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Segment getPrevious() {
		return previous;
	}

	public void setPrevious(Segment previous) {
		this.previous = previous;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Segment [id=" + id + ", oldX=" + oldX + ", oldY=" + oldY + ", previous=" + previous + ", direction="
				+ direction + "]";
	}

}
