package model;

public class Shield extends Block {

	private boolean eaten;
	public Shield(int x, int y) {
		super(x, y);
		setEaten(false);
	}
	public boolean isEaten() {
		return eaten;
	}
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}
	

}
