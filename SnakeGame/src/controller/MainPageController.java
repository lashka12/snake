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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.PlayGround;
import model.Snake;
import utilities.Constants;
import utilities.SoundEffects;

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
	void startGame() {

	}

	public void updateScore(int score) {

		this.score.setText(score + "");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (instance == null)
			instance = this;

	}

	public void openGamePane() {

		FadeTransition ft = new FadeTransition(Duration.millis(1000), homePane);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();

		ft = new FadeTransition(Duration.millis(1000), gamePane);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		gamePane.toFront();
		SoundEffects.playGameBoardMusic();
		Constants.setGAME_HIGHT((int) playGroundPane.getHeight() / Constants.BLOCK_SIZE);
		Constants.setGAME_WIDTH((int) playGroundPane.getWidth() / Constants.BLOCK_SIZE);
		PlayGround playGround = new PlayGround(Constants.GAME_WIDTH, Constants.GAME_HIGHT); // new play ground
		playGround.addSnake(new Snake(Constants.SNAKE_LENGTH)); // adding snake // maybe to move to playground class
		PlayGround.getInstance().setStyle("-fx-background-color: rgba(50, 50, 50, 0.5);  ");
		playGroundPane.getChildren().add(PlayGround.getInstance());

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
						Parent root1 = (Parent) fxmlLoader.load();
						Stage stage = new Stage();
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setTitle("ABC");
						Scene scene = new Scene(root1);
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
