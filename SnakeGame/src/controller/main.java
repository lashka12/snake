package controller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PlayGround;
import model.Snake;
import test.AcceptenceTests1;
import utilities.Constants;
import utilities.Direction;

public class main extends Application {

	private long then = System.nanoTime(); // now time seconds
	public static AnimationTimer timer;

	@Override
	public void start(Stage ps) {

		VBox root = new VBox(22); // the main view with 10px padding between elements
		PlayGround playGround = new PlayGround(Constants.GAME_WIDTH, Constants.GAME_HIGHT); // new play ground
		playGround.addSnake(new Snake(Constants.SNAKE_LENGTH)); // adding snake

		timer = new AnimationTimer() { // the game frame updater timer
			@Override
			public void handle(long now) {
				if (now - then > 1000000000 / 50) {
					playGround.update(); // update the hole game pane
					then = now;
				}

			}
		};
		timer.start();

		root.getChildren().add(playGround);
		root.setPadding(new Insets(10));
		Scene scene = new Scene(root);

		// this is the action listener that listen to the user keyboard input
		scene.setOnKeyPressed(e -> { // will be moved later to the mainFram controller to control the main scene

			if (e.getCode().equals(KeyCode.UP) && playGround.getSnake().getDirection() != Direction.DOWN)
				playGround.getSnake().setDirection(Direction.UP);
			if (e.getCode().equals(KeyCode.DOWN) && playGround.getSnake().getDirection() != Direction.UP)
				playGround.getSnake().setDirection(Direction.DOWN);
			if (e.getCode().equals(KeyCode.LEFT) && playGround.getSnake().getDirection() != Direction.RIGHT)
				playGround.getSnake().setDirection(Direction.LEFT);
			if (e.getCode().equals(KeyCode.RIGHT) && playGround.getSnake().getDirection() != Direction.LEFT)
				playGround.getSnake().setDirection(Direction.RIGHT);

		});

		ps.setScene(scene);
		ps.show();

	}

	public static void main(String[] args) { // will be moved to the main frame controller

		// activate the next code line to run tests
		// AcceptenceTests1.runTests();
		launch(args); // start method will be called
	}

}
