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

public class PausePageController implements Initializable {

	@FXML
	private StackPane root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		FadeTransition ft = new FadeTransition(Duration.millis(300), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}

	@FXML
	void restartGame(ActionEvent event) {

	}

	@FXML
	void resumeGame(ActionEvent event) {
		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(() -> {
					GameController.getInstance().StartGame();
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
