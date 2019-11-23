package test;



import model.Fruit;
import model.Mouse;
import utilities.Constants;
import javafx.scene.image.Image;

public class Tests {
	// testing the methods in class Mouse
			int MouseX=5,MouseY=5;
			Mouse mouse =new Mouse(MouseX,MouseY);
			Direction direction=Direction.DOWN;
			mouse.setDirection(direction);
			Assert.assertTrue(mouse.getDirection().equals(direction));
			mouse.moveUp();
			Assert.assertTrue(mouse.getPosY()==(MouseY-1));
			MouseY=MouseY-1;
			mouse.moveDown();
			Assert.assertTrue(mouse.getPosY()==(MouseY+1));
			MouseY=MouseY+1;
			mouse.moveLeft();
			Assert.assertTrue(mouse.getPosX()==(MouseX-1));
			MouseX=MouseX-1;
			mouse.moveRight();
			Assert.assertTrue(mouse.getPosX()==(MouseX+1));
			MouseX=MouseX+1;
			
			
			// testing the methods in class Fruit
			FruiteType fruitType= FruiteType.APPLE;
			int FruitX=15,FruitY=15;
			Fruit fruit =new Fruit(FruitX,FruitY,fruitType);
			FruiteType fruitTypeChange= FruiteType.BANANA;
			fruit.setType(fruitTypeChange);
			Assert.assertTrue(fruit.getType().equals(fruitTypeChange));
			
			
			//testing the methods in class Segment
			int oldPosX=-1, oldPosY=-1;
			int SegX=25,SegY=25;
			Direction dir=Direction.LEFT;
			Image headImage=Constants.SNAKE_HEAD_IMAGE;
		    Segment segment=new Segment(SegX,SegY,null);
		    segment.setDirection(dir);
		    Assert.assertTrue(segment.getDirection().equals(dir));
		    segment.update();
		    Assert.assertTrue(segment.getPosX()==SegX-1);
		    segment.setOldPosX(SegX);
		    segment.setOldPosY(SegY);
		    Assert.assertTrue(segment.getOldPosX()==SegX);
		    Assert.assertTrue(segment.getOldPosY()==SegY);
		    
		    
		    //testing the methods in class Snake
		    int length=5;
		    Snake snake=new Snake(length);
		    snake.addSegment();
		    Assert.assertTrue(snake.getBody().size()==length+1);
		    length++;

}
