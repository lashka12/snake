package model;

import utilities.FruiteType;

public class Fruit extends Block {

	private FruiteType type;

	public Fruit(int x, int y, FruiteType type) {

		super(x, y, type.getImage());
		this.type = type;

	}

	public FruiteType getType() {
		return type;
	}

	public void setType(FruiteType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Fruit [type=" + type + "]";
	}

}
