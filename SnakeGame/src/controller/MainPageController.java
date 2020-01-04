package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utilities.Constants;
import utilities.SoundEffects;
import view.GameSimulator;

/**
 * this class controls the view.MainPage.FXML page , initialize it and handle
 * it's actions
 * 
 * objects with @FXML sign are references to the graphic components of the FXML
 * file
 * 
 * methods with @FXML sign are the methods used to handle actions triggered by
 * the FXML File
 * 
 * this class is a singleton because there is need to interact with this
 * particular instance from other controllers during the game
 * 
 * @author Lawrence Ashkar
 *
 */

public class MainPageController implements Initializable {

	private static MainPageController singleton;

	@FXML
	private AnchorPane gamePane;

	@FXML
	private AnchorPane homePane;

	@FXML
	private VBox mainVBox;

	@FXML
	private AnchorPane toAddTo;

	@FXML
	private AnchorPane playGroundPane;

	@FXML
	private Label score;

	@FXML
	private ImageView h1;

	@FXML
	private ImageView h2;

	@FXML
	private ImageView h3;

	@FXML
	private VBox starsBox;

	@FXML
	private StackPane root;

	public VBox getStarsBox() {
		return starsBox;
	}

	@FXML
	void startGame() {

	}

	@FXML
	void exit() {

		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(() -> {
					SoundEffects.playButtonSound();
				});
				Thread.sleep(400);
				Platform.runLater(() -> {
					Stage stage = (Stage) gamePane.getScene().getWindow();
					stage.close();

				});

			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();

	}

	@FXML
	void openRating() {
		try {
			SoundEffects.playButtonSound();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/RatingPage.fxml"));
			RatingPageController rpc = new RatingPageController(false);
			fxmlLoader.setController(rpc);
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
			scene.setFill(Color.TRANSPARENT);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void openAuthentication() {

		try {
			SoundEffects.playButtonSound();
			FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/view/AdminAuthenticationPage.fxml"));
			Parent root1 = (Parent) fxmlLoader1.load();
			Stage stage1 = new Stage();
			stage1.initModality(Modality.APPLICATION_MODAL);
			Scene scene1 = new Scene(root1);
			stage1.setScene(scene1);
			stage1.initStyle(StageStyle.TRANSPARENT);
			scene1.setFill(Color.TRANSPARENT);
			stage1.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void updateScore(int score) {

		this.score.setText(score + "");

	}

	public void updateLives(int lives) {

		switch (lives) {
		case 3: {
			initLives();
			break;
		}
		case 2:
			h1.setImage(Constants.RED_HEART);
			h2.setImage(Constants.RED_HEART);
			h3.setImage(Constants.GRAY_HEART);
			break;
		case 1:
			h1.setImage(Constants.RED_HEART);
			h2.setImage(Constants.GRAY_HEART);
			h3.setImage(Constants.GRAY_HEART);

			break;
		case 0:
			h1.setImage(Constants.GRAY_HEART);
			h2.setImage(Constants.GRAY_HEART);
			h3.setImage(Constants.GRAY_HEART);
			break;

		}

	}

	public void initLives() {

		h1.setImage(Constants.RED_HEART);
		h2.setImage(Constants.RED_HEART);
		h3.setImage(Constants.RED_HEART);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (singleton == null)
			singleton = this;

	}

	public void openGamePane() {

		SoundEffects.playGameBoardMusic();
		FadeTransition ft = new FadeTransition(Duration.millis(1000), homePane);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
		ft = new FadeTransition(Duration.millis(1000), gamePane);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		gamePane.toFront();

		if (!playGroundPane.getChildren().contains(GameSimulator.getInstance())) { // adding the PlayGround Graphics to
																					// the main page
			playGroundPane.getChildren().add(GameSimulator.getInstance());
		}
	}

	@FXML
	void openHomePane() {

		FadeTransition ft = new FadeTransition(Duration.millis(1000), gamePane);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
		ft = new FadeTransition(Duration.millis(1000), homePane);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		homePane.toFront();

	}

	public static MainPageController getInstance() {
		return singleton;
	}

	@FXML
	void openUserInputPage() {
		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(() -> {

					SoundEffects.playButtonSound();
					try {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UserInputPage.fxml"));
						Parent root = (Parent) fxmlLoader.load();
						Stage stage = new Stage();
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.initStyle(StageStyle.UNDECORATED);
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.initStyle(StageStyle.TRANSPARENT);
						scene.setFill(Color.TRANSPARENT);
						stage.show();
					} catch (Exception e) {
						e.printStackTrace();
					}

				});

			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();

	}

	@FXML
	void muteGame(ActionEvent event) {

		if (SoundEffects.getMediaPlayer().isMute()) {
			SoundEffects.getMediaPlayer().setMute(false);
		} else {
			SoundEffects.getMediaPlayer().setMute(true);

		}

	}

}
