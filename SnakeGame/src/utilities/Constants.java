package utilities;

import java.io.File;

import javafx.scene.image.Image;

// thinking to move the pics to a 

public class Constants {

	public static int GAME_HIGHT = 53; // in pixels
	public static int GAME_WIDTH = 104;
	public static final int BLOCK_SIZE = 9;
	public static int SNAKE_LENGTH = 10;
	public static final Image SNAKE_BODY_IMAGE = new Image(new File("resources/pics/t.png").toURI().toString());
	public static final Image APPLE_IMAGE = new Image(new File("resources/pics/apple22.png").toURI().toString());
	public static final Image BANANA_IMAGE = new Image(new File("resources/pics/banana22.png").toURI().toString());
	public static final Image PEAR_IMAGE = new Image(new File("resources/pics/pear22.png").toURI().toString());
	public static final Image MOUSE_LEFT_IMAGE = new Image(new File("resources/pics/mouseLeft.gif").toURI().toString());
	public static final Image MOUSE_RIGHT_IMAGE = new Image(
			new File("resources/pics/mouseRight.gif").toURI().toString());
	public static final Image MOUSE_UP_IMAGE = new Image(new File("resources/pics/mouseUp.gif").toURI().toString());
	public static final Image MOUSE_DOWN_IMAGE = new Image(new File("resources/pics/mouseDown.gif").toURI().toString());
	public static final Image QUESTION_IMAGE = new Image(new File("resources/pics/question22.png").toURI().toString());
	public static final Image FIRE_IMAGE = new Image(new File("resources/pics/bum.gif").toURI().toString());
	public static final Image POINTS20_IMAGE = new Image(new File("resources/pics/20.png").toURI().toString());
	public static final Image POINTS15_IMAGE = new Image(new File("resources/pics/15.png").toURI().toString());
	public static final Image POINTS10_IMAGE = new Image(new File("resources/pics/10.png").toURI().toString());
	public static final Image READY_IMAGE = new Image(new File("resources/pics/Ready.png").toURI().toString());
	public static final Image GO_IMAGE = new Image(new File("resources/pics/GO.png").toURI().toString());
	public static final Image GRAY_HEART = new Image(new File("resources/pics/l2.png").toURI().toString());
	public static final Image RED_HEART = new Image(new File("resources/pics/l1.png").toURI().toString());
	public static final Image GAME_OVER_IMAGE = new Image(new File("resources/pics/GAME-OVER.png").toURI().toString());
	public static final Image HIT_IMAGE = new Image(new File("resources/pics/-1.png").toURI().toString());
	public static final Image EASY_QUESTION = new Image(new File("resources/pics/whiteQuestion22.png").toURI().toString());
	public static final Image INTER_QUESTION = new Image(new File("resources/pics/yellowQuestion22.png").toURI().toString());
	public static final Image HARD_QUESTION = new Image(new File("resources/pics/redQuestion22.png").toURI().toString());
	public static final String GAME_SOUND = "resources/audio/funny.mp3";
	public static final String BUBBLE_SOUND = "resources/audio/Bubble.mp3";
	public static final String BUTTON_SOUND = "resources/audio/buttonClick.mp3";
	public static final String CHIME_SOUND = "resources/audio/chime.mp3";
	public static final String START_SOUND = "resources/audio/start.mp3";
	public static final String END_SOUND = "resources/audio/gameOver.mp3";
	public static final String RATING_SOUND = "resources/audio/ratingSound.mp3";
	public static final String QUESTION_OPEN_SOUND = "resources/audio/openQuestion.mp3";
	public static final String NEGATIVE_SOUND = "resources/audio/negative3.mp3";

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
