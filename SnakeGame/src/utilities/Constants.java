package utilities;

import java.io.File;

import javafx.scene.image.Image;

public class Constants {

	public static int GAME_HIGHT = 51;
	public static int GAME_WIDTH = 77;
	public static final int BLOCK_SIZE = 9;
	public static int SNAKE_LENGTH = 20;
	public static final Image DIRT_BACKGROUND_IMAGE = new Image(new File("resources/pics/g4.jpg").toURI().toString());
	public static final Image SNAKE_BODY_IMAGE = new Image(new File("resources/pics/t.png").toURI().toString());
	public static final Image SNAKE_HEAD_IMAGE = new Image(new File("resources/pics/bbb.png").toURI().toString());
	public static final Image APPLE_IMAGE = new Image(new File("resources/pics/apple22.png").toURI().toString());
	public static final Image BANANA_IMAGE = new Image(new File("resources/pics/banana22.png").toURI().toString());
	public static final Image PEAR_IMAGE = new Image(new File("resources/pics/pear22.png").toURI().toString());
	public static final Image MOUSE_LEFT_IMAGE = new Image(new File("resources/pics/mouseLeft.gif").toURI().toString());
	public static final Image MOUSE_RIGHT_IMAGE = new Image(new File("resources/pics/mouseRight.gif").toURI().toString());
	public static final Image MOUSE_UP_IMAGE = new Image(new File("resources/pics/mouseUp.gif").toURI().toString());
	public static final Image MOUSE_DOWN_IMAGE = new Image(new File("resources/pics/mouseDown.gif").toURI().toString());
	public static final Image QUESTION_IMAGE = new Image(new File("resources/pics/question22.png").toURI().toString());
	public static final Image GROUND_IMAGE = new Image(new File("resources/pics/s.png").toURI().toString());
	public static final Image FIRE_IMAGE = new Image(new File("resources/pics/bum.gif").toURI().toString());
	public static final Image SPLASH_IMAGE = new Image(new File("resources/pics/splash.gif").toURI().toString());
	public static final Image POINTS20_IMAGE = new Image(new File("resources/pics/20.png").toURI().toString());
	public static final Image POINTS15_IMAGE = new Image(new File("resources/pics/15.png").toURI().toString());
	public static final Image POINTS10_IMAGE = new Image(new File("resources/pics/10.png").toURI().toString());
	public static final Image READY_IMAGE = new Image(new File("resources/pics/Ready.png").toURI().toString());
	public static final Image GO_IMAGE = new Image(new File("resources/pics/GO.png").toURI().toString());
	public static final String GAME_SOUND = "resources/audio/funny.mp3";
	public static final String BUBBLE_SOUND = "resources/audio/Bubble.mp3";
	public static final String BUTTON_SOUND = "resources/audio/buttonClick.mp3";
	public static final String CHIME_SOUND = "resources/audio/chime.mp3";
	public static final String START_SOUND = "resources/audio/start.mp3";

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
