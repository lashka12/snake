package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.SysData;
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
 * @author Lawrence Ashkar
 *
 */

public class RatingPageController implements Initializable {

	// there is need to know from where this class was called in order to hide ,
	// show buttons as needed
	private static boolean wasCalledFromGame;

	private static ArrayList<Game> sortedGames;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private AnchorPane root;
	@FXML
	private GridPane scoreGrid;
	@FXML
	private JFXButton restartBtn;
	@FXML
	private JFXButton closeBtn;

	/**
	 * controller constructor
	 * 
	 * @param gameCall - was called from the game or from the main page
	 */
	public RatingPageController(boolean gameCall) {

		wasCalledFromGame = gameCall;
		sortedGames = SysData.getGames();
		Collections.sort(sortedGames, Game.getCompByName());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		restartBtn.setVisible(wasCalledFromGame);
		closeBtn.setVisible(!wasCalledFromGame);

		FadeTransition ft = new FadeTransition(Duration.millis(1000), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();

		int i = 1;

		for (Game game : sortedGames) {

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
			Text score = new Text(game.getScore() + "");
			score.setFont(new Font("Snap ITC", 14));
			apple.setFont(new Font("Snap ITC", 14));
			banana.setFont(new Font("Snap ITC", 14));
			pear.setFont(new Font("Snap ITC", 14));

			if (Game.getInstance().getDate().toString().equals(game.getDate().toString())) {
				nickName.setFill(Color.rgb(129, 152, 48));
				score.setFill(Color.rgb(129, 152, 48));
				apple.setFill(Color.rgb(129, 152, 48));
				banana.setFill(Color.rgb(129, 152, 48));
				pear.setFill(Color.rgb(129, 152, 48));
			} else {

				nickName.setFill(Color.rgb(255, 94, 8));
				score.setFill(Color.rgb(255, 94, 8));
				apple.setFill(Color.rgb(255, 94, 8));
				banana.setFill(Color.rgb(255, 94, 8));
				pear.setFill(Color.rgb(255, 94, 8));

			}

			scoreGrid.addRow(i++, nickName, score, apple, banana, pear);

		}

		if (wasCalledFromGame)
			ScrollDownToRatio(scrollPane);

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

	/**
	 * this method controls the scroll pane in the RatingPage , it scrolls down
	 * automatically to show current game score
	 * 
	 * @param scrollPane
	 */
	static void ScrollDownToRatio(ScrollPane scrollPane) {

		int index = 0;
		for (Game game : sortedGames) {
			if (Game.getInstance().getDate().toString().equals(game.getDate().toString()))
				break;
			index++;
		}
		double ratio = (double) index / sortedGames.size();

		Animation animation = new Timeline(
				new KeyFrame(Duration.seconds(2), new KeyValue(scrollPane.vvalueProperty(), ratio)));
		animation.play();
	}

}
