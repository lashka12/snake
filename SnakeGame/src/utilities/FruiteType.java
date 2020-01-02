package utilities;

public enum FruiteType {

	APPLE(5, 10, 1), BANANA(10, 15, 1), PEAR(0, 20, 1);

	private final int secondsDelay;
	private final int points;
	private final int tailExtension;

	private FruiteType(int full, int abbr, int originalColony) {
		this.secondsDelay = full;
		this.points = abbr;
		this.tailExtension = originalColony;

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

}
