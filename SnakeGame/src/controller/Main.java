package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.SysData;
import utilities.SoundEffects;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/MainPage.fxml"));
		Scene scene = new Scene(root);
		GameEngine ge = new GameEngine(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		SysData data=new SysData();

		SoundEffects.playStartSound();
		launch(args);

	}

}
