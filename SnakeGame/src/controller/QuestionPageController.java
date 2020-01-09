package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.Question;
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
public class QuestionPageController implements Initializable {

	private static Question curQuestion;

	@FXML
	private StackPane root;
	@FXML
	private Text content;
	@FXML
	private Text ans1;
	@FXML
	private Text ans2;
	@FXML
	private Text ans3;
	@FXML
	private Text ans4;
	@FXML
	private RadioButton choose1;
	@FXML
	private RadioButton choose2;
	@FXML
	private RadioButton choose3;
	@FXML
	private RadioButton choose4;

	@FXML
	void emptyOthers(ActionEvent event) {

		RadioButton choosen = (RadioButton) event.getSource();

		if (choose1.isSelected() && !choose1.equals(choosen))
			choose1.setSelected(false);
		if (choose2.isSelected() && !choose2.equals(choosen))
			choose2.setSelected(false);
		if (choose3.isSelected() && !choose3.equals(choosen))
			choose3.setSelected(false);
		if (choose4.isSelected() && !choose4.equals(choosen))
			choose4.setSelected(false);

	}

	private boolean answeredRight() {

		if ((choose1.isSelected() && curQuestion.getCorrectAnswer().equals("1"))
				|| (choose2.isSelected() && curQuestion.getCorrectAnswer().equals("2"))
				|| (choose3.isSelected() && curQuestion.getCorrectAnswer().equals("3"))
				|| (choose4.isSelected() && curQuestion.getCorrectAnswer().equals("4"))) {
			return true;
		}

		return false;

	}

	@FXML
	void answer(ActionEvent event) {

		if (answeredRight()) {

			Thread thread = new Thread(() -> {
				try {

					Platform.runLater(() -> {
						Game.getInstance()
								.setScore(Game.getInstance().getScore() + curQuestion.getLevel().getPointsToAdd());
						MainPageController.getInstance().updateScore(Game.getInstance().getScore());
						MainPageController.getInstance().getStarsBox().toFront();
						FadeTransition ft1 = new FadeTransition(Duration.millis(3000),
								MainPageController.getInstance().getStarsBox());
						ft1.setFromValue(1.0);
						ft1.setToValue(0.0);
						ft1.play();
						GameController.getInstance().startTimer();
						SoundEffects.playButtonSound();
						FadeTransition ft = new FadeTransition(Duration.millis(500), root);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					});
					Thread.sleep(100);

					Platform.runLater(() -> {
						SoundEffects.playChimeSound();
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
		} else {

			Thread thread = new Thread(() -> {
				try {

					Platform.runLater(() -> {
						Game.getInstance()
								.setScore(Game.getInstance().getScore() - curQuestion.getLevel().getPointsToRemove());
						MainPageController.getInstance().updateScore(Game.getInstance().getScore());
						MainPageController.getInstance().getStarsBox().toFront();

						// *********************************************************
						//
						// wrong answer animation
						//
						// *********************************************************

						GameController.getInstance().startTimer();
						SoundEffects.playButtonSound();
						FadeTransition ft = new FadeTransition(Duration.millis(500), root);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					});
					Thread.sleep(100);

					Platform.runLater(() -> {
						SoundEffects.playWrongAnswerSound();
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

		Game.getInstance().getPlayGround().addQuestion(curQuestion.getLevel());

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		FadeTransition ft = new FadeTransition(Duration.millis(1000), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		content.setText(curQuestion.getContent());
		ans1.setText(curQuestion.getAns1());
		ans2.setText(curQuestion.getAns2());
		ans3.setText(curQuestion.getAns3());
		ans4.setText(curQuestion.getAns4());

	}

	public QuestionPageController(Question question) {
		curQuestion = question;

	}

}
