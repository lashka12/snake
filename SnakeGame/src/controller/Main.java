package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Block;
import model.Fruit;
import model.Mouse;
import model.SysData;
import utilities.FruiteType;
import utilities.SoundEffects;

/**
 * © 2019 Piranha Team , MIS - Haifa University Some Rights Reserved
 * 
 * the main class that runs the application
 * 
 * @author Lawrence
 *
 */

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
		SysData data = new SysData(); // here we create an instance of the System data to use it while game is running
		SoundEffects.playStartSound();
		launch(args);

	}

}
