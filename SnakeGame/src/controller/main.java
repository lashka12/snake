package controller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.PlayGround;
import model.Snake;
import utilities.Constants;
import utilities.Direction;

public class main extends Application {

	private long then = System.nanoTime(); // now time seconds
	public static AnimationTimer timer;

	@Override
	public void start(Stage ps) {

		// comment for you george
		
		
		VBox root = new VBox(6); // the main view with 10px padding between elements
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
		Scene scene = new Scene(root); // will be changed to the main frame scene
		ps.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);

		scene.setOnKeyPressed(e -> { // i can move this part the the mainFram controller to control the main scene

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
		// ps.setResizable(false);
		ps.show();

	}

	public static void main(String[] args) { // will be moved to the main frame controller
		launch(args); // start method will be called
	}

}
