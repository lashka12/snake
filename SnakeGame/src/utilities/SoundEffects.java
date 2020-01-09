package utilities;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.GameObserver;

/**
 * this class is used to make sound effects , it implements game observer in
 * order to give a sound feedback when ever snake eats a object automatically
 * 
 * @see GameObserver.java
 * @see design patterns.PDF fro more information
 * @author L.A
 *
 */
public class SoundEffects implements GameObserver {

	private static MediaPlayer mediaPlayer;
	private static Media media;

	@Override
	public void update() {
		playBubbleSound();

	}

	public static void stopSound() {
		mediaPlayer.stop();
	}

	public static void playGameBoardMusic() {

		media = new Media(new File(Constants.GAME_SOUND).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.play();

	}

	public static MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public static void playRatingMusic() {

		media = new Media(new File(Constants.RATING_SOUND).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.play();

	}

	public static void playButtonSound() {

		media = new Media(new File(Constants.BUTTON_SOUND).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.play();
	}
	public static void magic1() {

		media = new Media(new File(Constants.magic1).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.play();
	}
	public static void swipe() {

		media = new Media(new File(Constants.swipe).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.play();
	}
	public static void enter() {

		media = new Media(new File(Constants.enter).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.play();
	}
	public static void warning() {

		media = new Media(new File(Constants.warning).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.play();
	}
	public static void magic2() {

		media = new Media(new File(Constants.magic2).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.play();
	}

	public static void playStartSound() {

		media = new Media(new File(Constants.START_SOUND).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.setAutoPlay(true);
	}

	public static void playGameOverSound() {

		media = new Media(new File(Constants.END_SOUND).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.setAutoPlay(true);
	}

	public static void playChimeSound() {

		media = new Media(new File(Constants.CHIME_SOUND).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.setAutoPlay(true);
	}

	public static void playBubbleSound() {

		media = new Media(new File(Constants.BUBBLE_SOUND).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.setAutoPlay(true);
	}

	public static void playQuestionStartSound() {
		media = new Media(new File(Constants.QUESTION_OPEN_SOUND).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.setAutoPlay(true);
	}

	public static void playNegativeSound() {
		media = new Media(new File(Constants.NEGATIVE_SOUND).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.setAutoPlay(true);
	}

	public static void playRightAnswerSound() {
		media = new Media(new File(Constants.RIGHT_ANSWER_SOUND).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.setAutoPlay(true);
	}

	public static void playWrongAnswerSound() {
		media = new Media(new File(Constants.WRONG_ANSWER_SOUND).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.setAutoPlay(true);
	}

}
