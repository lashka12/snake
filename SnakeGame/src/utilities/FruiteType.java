package utilities;

import javafx.scene.image.Image;

public enum FruiteType {

	APPLE(5, 10, 1, Constants.APPLE_IMAGE), BANANA(10, 15, 1, Constants.BANANA_IMAGE),
	PEAR(0, 20, 1, Constants.PEAR_IMAGE);

	private final int secondsDelay;
	private final int points;
	private final int tailExtension;
	private final Image image;

	private FruiteType(int full, int abbr, int originalColony, Image image) {
		this.secondsDelay = full;
		this.points = abbr;
		this.tailExtension = originalColony;
		this.image = image;

	}

	public int getSecondsDelay() {
		return secondsDelay;
	}

	public int getPoints() {
		return points;
	}

	public int getTailExtension() {
		return tailExtension;
	}

	public Image getImage() {
		return image;
	}

}
