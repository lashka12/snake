package utilities;

public enum Level {

	EASY(1, 10), INTERMEDIATE(2, 20), HARD(3, 30);

	private final int pointsToAdd;
	private final int pointsToRemove;

	private Level(int pointsToAdd, int pointsToRemove) {
		this.pointsToAdd = pointsToAdd;
		this.pointsToRemove = pointsToRemove;

	}

	public int getPointsToRemove() {
		return pointsToRemove;
	}

	public int getPointsToAdd() {
		return pointsToAdd;
	}

}
