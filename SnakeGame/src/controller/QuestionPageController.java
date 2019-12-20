package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Question;
import model.SysData;
import utilities.SoundEffects;

public class QuestionPageController implements Initializable {

	private static Question question;
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
	void answer(ActionEvent event) {

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		FadeTransition ft = new FadeTransition(Duration.millis(1000), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
//		content.setText(question.getContent());
//		ans1.setText(question.getAnswers().get(0));
//		ans2.setText(question.getAnswers().get(1));
//		ans3.setText(question.getAnswers().get(2));
//		ans4.setText(question.getAnswers().get(3));

	}

	public static Question getQuestion() {
		return question;
	}

	public static void setQuestion(Question question) {
		QuestionPageController.question = question;
	}

}
