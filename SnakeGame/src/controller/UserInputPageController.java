package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilities.SoundEffects;

public class UserInputPageController implements Initializable {

	private static UserInputPageController instance;

	public static UserInputPageController getInstance() {
		return instance;
	}

	@FXML
	private JFXTextField nickName;
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
	void play() {

		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(() -> {
					SoundEffects.playButtonSound();
					FadeTransition ft = new FadeTransition(Duration.millis(800), root);
					ft.setFromValue(1.0);
					ft.setToValue(0.0);
					ft.play();

				});
				Thread.sleep(800);
				Platform.runLater(() -> {
					Stage stage = (Stage) nickName.getScene().getWindow();
					stage.close();

				});
				Thread.sleep(100);
				Platform.runLater(() -> {
					MainPageController.getInstance().openGamePane();
				});

			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();

	}

	@FXML
	void quit() {
		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(() -> {
					SoundEffects.playButtonSound();

					FadeTransition ft = new FadeTransition(Duration.millis(400), root);
					ft.setFromValue(1.0);
					ft.setToValue(0.0);
					ft.play();

				});
				Thread.sleep(400);
				Platform.runLater(() -> {
					Stage stage = (Stage) nickName.getScene().getWindow();
					stage.close();

				});

			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();

	}

}
