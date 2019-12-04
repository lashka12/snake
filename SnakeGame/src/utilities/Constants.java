package utilities;

import java.io.File;

import javafx.scene.image.Image;

public class Constants {

	public static int GAME_HIGHT = 51;
	public static int GAME_WIDTH = 77;
	public static final int BLOCK_SIZE = 9;
	public static int SNAKE_LENGTH = 20;
	public static final Image DIRT_BACKGROUND_IMAGE = new Image(new File("src/pics/g4.jpg").toURI().toString());
	public static final Image SNAKE_BODY_IMAGE = new Image(new File("src/pics/t.png").toURI().toString());
	public static final Image SNAKE_HEAD_IMAGE = new Image(new File("src/pics/bbb.png").toURI().toString());
	public static final Image APPLE_IMAGE = new Image(new File("src/pics/apple22.png").toURI().toString());
	public static final Image BANANA_IMAGE = new Image(new File("src/pics/banana22.png").toURI().toString());
	public static final Image PEAR_IMAGE = new Image(new File("src/pics/pear22.png").toURI().toString());
	public static final Image MOUSE_LEFT_IMAGE = new Image(new File("src/pics/mouseLeft.gif").toURI().toString());
	public static final Image MOUSE_RIGHT_IMAGE = new Image(new File("src/pics/mouseRight.gif").toURI().toString());
	public static final Image MOUSE_UP_IMAGE = new Image(new File("src/pics/mouseUp.gif").toURI().toString());
	public static final Image MOUSE_DOWN_IMAGE = new Image(new File("src/pics/mouseDown.gif").toURI().toString());
	public static final Image QUESTION_IMAGE = new Image(new File("src/pics/palm.png").toURI().toString());
	public static final Image GROUND_IMAGE = new Image(new File("src/pics/s.png").toURI().toString());
	public static final Image FIRE_IMAGE = new Image(new File("src/pics/bum.gif").toURI().toString());
	public static final Image GRASS_IMAGE = new Image(new File("src/pics/wl.png").toURI().toString());
	public static final Image SPLASH_IMAGE = new Image(new File("src/pics/splash.gif").toURI().toString());

	public static int getGAME_HIGHT() {
		return GAME_HIGHT;
	}

	public static void setGAME_HIGHT(int gAME_HIGHT) {
		GAME_HIGHT = gAME_HIGHT;
	}

	public static int getGAME_WIDTH() {
		return GAME_WIDTH;
	}

	public static void setGAME_WIDTH(int gAME_WIDTH) {
		GAME_WIDTH = gAME_WIDTH;
	}

	public static int getSNAKE_LENGTH() {
		return SNAKE_LENGTH;
	}

	public static void setSNAKE_LENGTH(int sNAKE_LENGTH) {
		SNAKE_LENGTH = sNAKE_LENGTH;
	}

}
