package controller;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utilities.Constants;
import utilities.SoundEffects;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/view/MainPage.fxml"));
		primaryStage.setTitle("Registration Form FXML Application");
		Scene scene = new Scene(root);
		GameEngine ge = new GameEngine(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {

		SoundEffects.playStartSound();
		launch(args);

	}

}
