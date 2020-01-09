package model;

import java.util.concurrent.atomic.AtomicInteger;
import utilities.Direction;

/**
 * this class represents a part of the snake's body
 * 
 * @author L.A
 *
 */
public class Segment extends Block {

	private static AtomicInteger count;
	private int id; // unique id for each segment - was used later in the view
	private int oldX, oldY;
	private Segment previous;
	private Direction direction = Direction.LEFT;

	/**
	 * full constructor
	 * 
	 * @param x         - the x position of the segment on the playground
	 * @param y         - the y position of the segment on the playground
	 * @param prevBlock - the previous segment of it
	 */
	public Segment(int x, int y, Segment prevBlock) {
		super(x, y);
		this.id = count.getAndIncrement();
		this.previous = prevBlock;

	}

	/**
	 * updates the segment position logically step at a time we only update the head
	 * position , other segments will follow the head
	 */
	public void move() {

		oldX = getX();
		oldY = getY();

		if (previous == null) { // this segment is a head it has no previous

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

	public static void restartCounter() {
		count = new AtomicInteger(0);

	}

	@Override
	public String toString() {
		return "Segment [id=" + id + ", oldX=" + oldX + ", oldY=" + oldY + ", previous=" + previous + ", direction="
				+ direction + "]";
	}

}
