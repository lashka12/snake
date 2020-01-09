package model;

/**
 * this is the basic logical object that all of the other game objects extends
 * it represent a block on the playground with a specific x and y
 * 
 * @author L.A
 *
 */
public abstract class Block {

	private int x, y;

	/**
	 * full constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
