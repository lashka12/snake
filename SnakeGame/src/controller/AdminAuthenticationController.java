package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilities.Constants;
import utilities.SoundEffects;

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

public class AdminAuthenticationController implements Initializable {

	@FXML
	private StackPane root;
	@FXML
	private JFXPasswordField password;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FadeTransition ft = new FadeTransition(Duration.millis(300), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		// delete this later
		password.setText("abc123");
	}

	@FXML
	void enter(ActionEvent event) {

		if (password.getText().equals("abc123")) {

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
						Parent root;
						try {
							root = FXMLLoader
									.load(getClass().getClassLoader().getResource("view/QuestionsManagmentPage.fxml"));
							Scene scene = new Scene(root);
							Stage stg = new Stage();
							stg.setScene(scene);
							stg.getIcons().add(Constants.STACK_IMAGE);
							stg.setAlwaysOnTop(true);
							stg.show();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});

				} catch (Exception exc) {
					throw new Error("Unexpected interruption");
				}
			});
			thread.start();

		} else {
			SoundEffects.playNegativeSound();
		}

	}

	@FXML
	void quit(ActionEvent event) {

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
