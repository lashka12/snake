package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilities.SoundEffects;

/**
 * this class controls the PausePage.MainPage.FXML page , initialize it and
 * handle it's actions
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
public class PausePageController implements Initializable {
	
	public static PausePageController instance;

	@FXML
	private StackPane root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (instance == null) {
			instance = this;
		}

		FadeTransition ft = new FadeTransition(Duration.millis(300), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();

	}

	@FXML
	void restartGame(ActionEvent event) {

		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(() -> {
					GameController.getInstance().restartGame();
					SoundEffects.playButtonSound();
					FadeTransition ft = new FadeTransition(Duration.millis(500), root);
					ft.setFromValue(1.0);
					ft.setToValue(0.0);
					ft.play();

				});
				Thread.sleep(500);
				Platform.runLater(() -> {
					Stage stage = (Stage) root.getScene().getWindow();
					stage.close();

				});

			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();

	}

	@FXML
	void leaveGame() {

		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(() -> {
					SoundEffects.stopSound();
					GameController.getInstance().pauseGame();
					MainPageController.getInstance().openHomePane();
					SoundEffects.playButtonSound();
					FadeTransition ft = new FadeTransition(Duration.millis(500), root);
					ft.setFromValue(1.0);
					ft.setToValue(0.0);
					ft.play();

				});
				Thread.sleep(500);
				Platform.runLater(() -> {
					Stage stage = (Stage) root.getScene().getWindow();
					stage.close();

				});

			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();

	}

	@FXML
	void resumeGame(ActionEvent event) {
		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(() -> {
					GameController.getInstance().startTimer();
					SoundEffects.playButtonSound();
					FadeTransition ft = new FadeTransition(Duration.millis(500), root);
					ft.setFromValue(1.0);
					ft.setToValue(0.0);
					ft.play();

				});
				Thread.sleep(500);
				Platform.runLater(() -> {
					Stage stage = (Stage) root.getScene().getWindow();
					stage.close();

				});

			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();
	}

}
