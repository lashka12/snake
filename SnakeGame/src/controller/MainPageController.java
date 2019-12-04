package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.PlayGround;
import model.Snake;
import utilities.Constants;

public class MainPageController implements Initializable {

	private static MainPageController instance;

	@FXML
	private AnchorPane pane;

	@FXML
	private VBox mainVBox;

	@FXML
	private AnchorPane toAddTo;
	@FXML
	private AnchorPane playGroundPane;

	@FXML
	private Label score;

	@FXML
	void startGame() {

		String path1 = "src/audio/bl.mp3";
		Media media1 = new Media(new File(path1).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
		mediaPlayer1.setAutoPlay(true);

		String path = "src/audio/funny.mp3";
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.play();

		Constants.setGAME_HIGHT((int) playGroundPane.getHeight() / Constants.BLOCK_SIZE);
		Constants.setGAME_WIDTH((int) playGroundPane.getWidth() / Constants.BLOCK_SIZE);

		PlayGround playGround = new PlayGround(Constants.GAME_WIDTH, Constants.GAME_HIGHT); // new play ground
		playGround.addSnake(new Snake(Constants.SNAKE_LENGTH)); // adding snake // maybe to move to playground class
		PlayGround.getInstance().setStyle("-fx-background-color: rgba(50, 50, 50, 0.5);  ");

		playGroundPane.getChildren().add(PlayGround.getInstance());
		GameEngine ge = new GameEngine(playGround.getScene());

	}

	public void updateScore(int score) {

		this.score.setText(score + "");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (instance == null)
			instance = this;

	}

	public static MainPageController getInstance() {
		return instance;
	}

}
