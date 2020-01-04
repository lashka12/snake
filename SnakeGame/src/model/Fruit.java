package model;

import utilities.FruiteType;

/**
 * fruit objects on the play board
 * 
 * @author L.A
 *
 */
public class Fruit extends Block {

	private FruiteType type;
	private boolean eaten;

	/**
	 * full constructor
	 * 
	 * @param x    : logical x position
	 * @param y    : logical y position
	 * @param type : the type of the fruit @see utilities.FruitType
	 */
	public Fruit(int x, int y, FruiteType type) {
		super(x, y);
		this.type = type;
		this.eaten = false;

	}

	public FruiteType getType() {
		return type;
	}

	public void setType(FruiteType type) {
		this.type = type;
	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	@Override
	public String toString() {
		return "Fruit [type=" + type + ", eaten=" + eaten + "]";
	}

}
