package model;

import utilities.FruiteType;

public class test {

	public static void main(String[] args) {
		printTest(new Fruit(1, 1, FruiteType.APPLE));

	}

	private static void printTest(Block b) {
		System.out.println(b);

	}

}
