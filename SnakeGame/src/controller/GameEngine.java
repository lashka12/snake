package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.PlayGround;
import utilities.Direction;

public class GameEngine {

	private long then = System.nanoTime();
	public static AnimationTimer timer;

	public GameEngine(Scene scene) {

		setUpGameTimer();
		setKeyBoardControllers(scene);

	}

	private void setUpGameTimer() {
		timer = new AnimationTimer() { // the game frame updater timer
			@Override
			public void handle(long now) {
				if (now - then > 1000000000 / 55) {
					PlayGround.getInstance().update(); // update the hole game pane
					then = now;
				}

			}
		};
		timer.start();
	}

	public void stopTimer() {
		timer.stop();
	}

	private void setKeyBoardControllers(Scene scene) {
		scene.setOnKeyPressed(e -> {

			if (e.getCode().equals(KeyCode.UP) && PlayGround.getInstance().getSnake().getDirection() != Direction.DOWN)
				PlayGround.getInstance().getSnake().setDirection(Direction.UP);
			if (e.getCode().equals(KeyCode.DOWN) && PlayGround.getInstance().getSnake().getDirection() != Direction.UP)
				PlayGround.getInstance().getSnake().setDirection(Direction.DOWN);
			if (e.getCode().equals(KeyCode.LEFT)
					&& PlayGround.getInstance().getSnake().getDirection() != Direction.RIGHT)
				PlayGround.getInstance().getSnake().setDirection(Direction.LEFT);
			if (e.getCode().equals(KeyCode.RIGHT)
					&& PlayGround.getInstance().getSnake().getDirection() != Direction.LEFT)
				PlayGround.getInstance().getSnake().setDirection(Direction.RIGHT);
			if (e.getCode().equals(KeyCode.P))timer.stop();
			if (e.getCode().equals(KeyCode.R))timer.start();

		});
	}

}
