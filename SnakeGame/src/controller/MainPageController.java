package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
 * objects with @FXML sign are the graphic components of the FXML file
 * 
 * methods with @FXML sign are the methods used to handle actions triggered by
 * the FXML File
 * 
 * @author Lawrence
 *
 */

public class MainPageController implements Initializable {

	private static MainPageController instance;

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
	void startGame() {

	}

	public void updateScore(int score) {

		this.score.setText(score + "");

	}

	public void updateLives(int lives) {

		switch (lives) {
		case 2:
			h3.setImage(Constants.GRAY_HEART);
			break;
		case 1:
			h2.setImage(Constants.GRAY_HEART);
			break;
		case 0:
			h1.setImage(Constants.GRAY_HEART);
			break;

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (instance == null)
			instance = this;

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

		playGroundPane.getChildren().add(GameSimulator.getInstance());

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
		return instance;
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

}
