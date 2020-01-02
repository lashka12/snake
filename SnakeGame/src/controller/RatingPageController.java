package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.SysData;
import utilities.SoundEffects;

public class RatingPageController implements Initializable {

	@FXML
	private AnchorPane root;

	@FXML
	private GridPane scoreGrid;

	@FXML
	private JFXButton restartBtn;
	@FXML
	private JFXButton closeBtn;

	private static boolean showRestartOption;

	public RatingPageController(boolean b) {
		showRestartOption = b;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		restartBtn.setVisible(showRestartOption);
		closeBtn.setVisible(!showRestartOption);

		FadeTransition ft = new FadeTransition(Duration.millis(1000), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();

		int i = 1;
		for (Game game : SysData.getGames()) {

			Text apple;
			if (game.getEatenObjects().get("APPLE") != null) {
				apple = new Text("x " + game.getEatenObjects().get("APPLE").toString());
			} else {
				apple = new Text("0");

			}
			Text banana;
			if (game.getEatenObjects().get("BANANA") != null) {
				banana = new Text("x " + game.getEatenObjects().get("BANANA").toString());
			} else {
				banana = new Text("0");

			}
			Text pear;
			if (game.getEatenObjects().get("PEAR") != null) {
				pear = new Text("x " + game.getEatenObjects().get("PEAR").toString());
			} else {
				pear = new Text("0");

			}
			Text nickName = new Text(game.getNickName());
			nickName.setFont(new Font("Snap ITC", 14));
			nickName.setFill(Color.rgb(255, 94, 8));
			Text score = new Text(game.getScore() + "");
			score.setFont(new Font("Snap ITC", 14));
			score.setFill(Color.rgb(255, 94, 8));
			apple.setFont(new Font("Snap ITC", 14));
			apple.setFill(Color.rgb(255, 94, 8));
			banana.setFont(new Font("Snap ITC", 14));
			banana.setFill(Color.rgb(255, 94, 8));
			pear.setFont(new Font("Snap ITC", 14));
			pear.setFill(Color.rgb(255, 94, 8));
			scoreGrid.addRow(i++, nickName, score, apple, banana, pear);

		}
	}

	@FXML
	void restart(ActionEvent event) {
		GameController.getInstance().restartGame();
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
					Stage stage = (Stage) root.getScene().getWindow();
					stage.close();

				});

			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();

		SoundEffects.stopSound();
		SoundEffects.playGameBoardMusic();

	}

	@FXML
	void close() {

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
