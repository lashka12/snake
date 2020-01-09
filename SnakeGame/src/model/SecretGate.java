package model;

/**
 * this class is a bonus feature it used to move the snake from one point to
 * another - still not done yet there is no java doc
 * 
 * @author L.A
 *
 */
public class SecretGate {

	int enterX, enterY, exitX, exitY;

	/**
	 * full constructor
	 * 
	 * @param enterX
	 * @param enterY
	 * @param exitX
	 * @param exitY
	 */
	public SecretGate(int enterX, int enterY, int exitX, int exitY) {

		this.enterX = enterX;
		this.enterY = enterY;
		this.exitX = exitX;
		this.exitY = exitY;
	}

	public int getEnterX() {
		return enterX;
	}

	public void setEnterX(int enterX) {
		this.enterX = enterX;
	}

	public int getEnterY() {
		return enterY;
	}

	public void setEnterY(int enterY) {
		this.enterY = enterY;
	}

	public int getExitX() {
		return exitX;
	}

	public void setExitX(int exitX) {
		this.exitX = exitX;
	}

	public int getExitY() {
		return exitY;
	}

	public void setExitY(int exitY) {
		this.exitY = exitY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + enterX;
		result = prime * result + enterY;
		result = prime * result + exitX;
		result = prime * result + exitY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecretGate other = (SecretGate) obj;
		if (enterX != other.enterX)
			return false;
		if (enterY != other.enterY)
			return false;
		if (exitX != other.exitX)
			return false;
		if (exitY != other.exitY)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SecretGate [enterX=" + enterX + ", entery=" + enterY + ", exitX=" + exitX + ", exitY=" + exitY + "]";
	}

}
