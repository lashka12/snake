package utilities;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundEffects {

	private static MediaPlayer mediaPlayer;
	private static Media media;

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

	public static void playButtonSound() {

		media = new Media(new File(Constants.BUTTON_SOUND).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media);
		mediaPlayer1.setAutoPlay(true);
	}

	public static void playStartSound() {

		media = new Media(new File(Constants.START_SOUND).toURI().toString());
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

}