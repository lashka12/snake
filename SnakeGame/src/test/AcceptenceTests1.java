package test;

import model.Fruit;
import model.Mouse;
import model.Segment;
import model.Snake;
import utilities.Direction;
import utilities.FruiteType;

public class AcceptenceTests1 {

	public static void runTests() {

		int i = 1, MouseX = 5, MouseY = 5;
		Mouse mouse = new Mouse(MouseX, MouseY);
		Direction direction = Direction.DOWN;
		mouse.setDirection(direction);
		System.out.println("test " + i++ + " result is : " + mouse.getDirection().equals(direction));
		mouse.moveUp();
		System.out.println("test " + i++ + " result is : " + (mouse.getPosY() == (MouseY - 1)));
		MouseY = MouseY - 1;
		mouse.moveDown();
		System.out.println("test " + i++ + " result is : " + (mouse.getPosY() == (MouseY + 1)));
		MouseY = MouseY + 1;
		mouse.moveLeft();
		System.out.println("test " + i++ + " result is : " + (mouse.getPosX() == (MouseX - 1)));
		MouseX = MouseX - 1;
		mouse.moveRight();
		System.out.println("test " + i++ + " result is : " + (mouse.getPosX() == (MouseX + 1)));
		MouseX = MouseX + 1;

		// testing the methods in class Fruit
		FruiteType fruitType = FruiteType.APPLE;
		int FruitX = 15, FruitY = 15;
		Fruit fruit = new Fruit(FruitX, FruitY, fruitType);
		FruiteType fruitTypeChange = FruiteType.BANANA;
		fruit.setType(fruitTypeChange);
		System.out.println("test " + i++ + " result is : " + (fruit.getType().equals(fruitTypeChange)));

		// testing the methods in class Segment
		int SegX = 25, SegY = 25;
		Direction dir = Direction.LEFT;
		Segment segment = new Segment(SegX, SegY, null);
		segment.setDirection(dir);
		System.out.println("test " + i++ + " result is : " + ((segment.getDirection().equals(dir))));
		segment.update();
		System.out.println("test " + i++ + " result is : " + ((segment.getPosX() == SegX - 1)));
		segment.setOldPosX(SegX);
		segment.setOldPosY(SegY);
		System.out.println("test " + i++ + " result is : " + ((segment.getOldPosX() == SegX)));
		System.out.println("test " + i++ + " result is : " + ((segment.getOldPosY() == SegY)));

		// testing the methods in class Snake
		int length = 5;
		Snake snake = new Snake(length);
		snake.addSegment();
		System.out.println("test " + i++ + " result is : " + ((snake.getBody().size() == length + 1)));
		length++;

	}

}
