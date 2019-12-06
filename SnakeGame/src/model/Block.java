package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.Constants;

public abstract class Block extends ImageView {

	private int posX, posY;

	public Block(int x, int y, Image image) {

		super(image); // setting the image according to the object
		this.posX = x;
		this.posY = y;
		ViewOnScreen();

	}

	public void ViewOnScreen() {
		setTranslateX(posX * Constants.BLOCK_SIZE);
		setTranslateY(posY * Constants.BLOCK_SIZE);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}
