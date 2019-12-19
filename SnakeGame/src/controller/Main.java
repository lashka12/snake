package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Game;
import utilities.Direction;
import utilities.SoundEffects;
import view.GameSimulator;

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

		Game game = new Game(""); // nick name will be added later
		GameSimulator view = new GameSimulator();
		GameController gc = new GameController(game, view);

		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/MainPage.fxml"));
		Scene scene = new Scene(root);
		scene.setOnKeyPressed(e -> {

			if (e.getCode().equals(KeyCode.UP) && game.getPlayGround().getSnake().getDirection() != Direction.DOWN)
				gc.changeDiriction(Direction.UP);
			if (e.getCode().equals(KeyCode.DOWN) && game.getPlayGround().getSnake().getDirection() != Direction.UP)
				gc.changeDiriction(Direction.DOWN);
			if (e.getCode().equals(KeyCode.LEFT) && game.getPlayGround().getSnake().getDirection() != Direction.RIGHT)
				gc.changeDiriction(Direction.LEFT);
			if (e.getCode().equals(KeyCode.RIGHT) && game.getPlayGround().getSnake().getDirection() != Direction.LEFT)
				gc.changeDiriction(Direction.RIGHT);

		});
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {

		SoundEffects.playStartSound();
		launch(args);

	}

}
