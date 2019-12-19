package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Fruit;
import model.Game;
import model.Segment;
import utilities.Constants;
import utilities.Direction;
import utilities.SoundEffects;
import view.GameSimulator;

public class GameController {

	private Game game;
	private GameSimulator view;
	private AnimationTimer timer;
	private long then = System.nanoTime();
	private static GameController instance = null;

	public GameController(Game game, GameSimulator view) {

		if (instance == null) {
			instance = this;

			this.game = game;
			this.view = view;

			timer = new AnimationTimer() { // the game frame updater timer
				@Override
				public void handle(long now) {
					if (now - then > 1000000000 / 55) {
						update();
						then = now;
					}
				}
			};
		} else {
			System.out.println("this class is a singltone ! ");
		}

	}

	public void update() { // must update the logic and the view

		if ((game.getPlayGround().getSnake().getHead().getY() < 0)
				|| (game.getPlayGround().getSnake().getHead().getY() > Constants.GAME_HIGHT - 2)
				|| (game.getPlayGround().getSnake().getHead().getX() < 0)
				|| (game.getPlayGround().getSnake().getHead().getX() >= Constants.GAME_WIDTH - 2)) {

			pauseGame();
			game.setLives(game.getLives() - 1);
			MainPageController.getInstance().updateLives(game.getLives());
			game.getPlayGround().setHit(true);

			if (game.getLives() == 0) {

				game.setDuration(calculateDuration(game.getDate(), new Date()));
				game.setOver(true);
				SoundEffects.stopSound();

				Thread thread = new Thread(() -> {
					try {

						Thread.sleep(3 * 1000); // waiting
						Platform.runLater(() -> {
							try {
								FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/RatingPage.fxml"));
								Parent root = (Parent) fxmlLoader.load();
								Stage stage = new Stage();
								stage.initModality(Modality.APPLICATION_MODAL);
								Scene scene = new Scene(root);
								stage.setScene(scene);
								stage.initStyle(StageStyle.TRANSPARENT);
								scene.setFill(Color.TRANSPARENT);
								stage.show();
								SoundEffects.playRatingMusic();
							} catch (Exception e) {
								e.printStackTrace();
							}

						});

					} catch (Exception exc) {
						throw new Error("Unexpected interruption");
					}
				});
				thread.start();

			}

			else {

				Thread thread = new Thread(() -> {
					try {

						Thread.sleep(1 * 1000); // waiting
						Platform.runLater(() -> {
							game.getPlayGround().setHit(false);
							game.getPlayGround().getSnake().getHead().setX(Constants.GAME_WIDTH / 2);
							game.getPlayGround().getSnake().getHead().setY(Constants.GAME_HIGHT / 2);
							resumeGame();

						});

					} catch (Exception exc) {
						throw new Error("Unexpected interruption");
					}
				});
				thread.start();
			}
		}

		for (Segment segment : game.getPlayGround().getSnake().getBody()) // update snake pos
			segment.move();

		game.getPlayGround().getMouse().update(); // update mouse pos

		if (mouseWasEaten()) {
			SoundEffects.playBubbleSound();
			game.addEatenObject(game.getPlayGround().getMouse());
			game.getPlayGround().getMouse().setEaten(true);
			game.getPlayGround().addMouse();

		}

		for (Fruit fruit : game.getPlayGround().getFruits().values()) { // Iterate over fruits and check their state

			if (snakeHit(fruit) && fruit.isEaten() == false) {

				game.addEatenObject(fruit);
				fruit.setEaten(true);
				game.setScore(game.getScore() + fruit.getType().getPoints());
				MainPageController.getInstance().updateScore(game.getScore());

				Thread thread = new Thread(() -> {
					try {
						Platform.runLater(() -> {
							SoundEffects.playBubbleSound();
							game.getPlayGround().getSnake().addSegment();
						});

						Thread.sleep(fruit.getType().getSecondsDelay() * 1000); // waiting
						Platform.runLater(() -> {
							game.getPlayGround().addFruit(fruit.getType());
						});

					} catch (Exception exc) {
						throw new Error("Unexpected interruption");
					}
				});
				thread.start();

			}

		}

		view.render(); // refresh view

	}

	public void changeDiriction(Direction dir) {

		game.getPlayGround().getSnake().setDirection(dir);

	}

	public Boolean snakeHit(Fruit fruit) {

		if ((Math.abs(fruit.getX() - game.getPlayGround().getSnake().getHead().getX()) >= 0
				&& Math.abs(fruit.getX() - game.getPlayGround().getSnake().getHead().getX()) < 2)
				&& (Math.abs(fruit.getY() - game.getPlayGround().getSnake().getHead().getY()) >= 0
						&& Math.abs(fruit.getY() - game.getPlayGround().getSnake().getHead().getY()) < 2))

			return true;

		return false;

	}

	public boolean mouseWasEaten() { // move to playground

		if ((Math.abs(game.getPlayGround().getMouse().getX() - game.getPlayGround().getSnake().getHead().getX()) >= 0
				&& Math.abs(
						game.getPlayGround().getMouse().getX() - game.getPlayGround().getSnake().getHead().getX()) < 2)
				&& (Math.abs(
						game.getPlayGround().getMouse().getY() - game.getPlayGround().getSnake().getHead().getY()) >= 0
						&& Math.abs(game.getPlayGround().getMouse().getY()
								- game.getPlayGround().getSnake().getHead().getY()) < 2))
			return true;

		return false;

	}

	public void pauseGame() {
		timer.stop();
	}

	public void resumeGame() {
		timer.start();
	}

	public void StartGame() {

		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(800);
				Platform.runLater(() -> {
					view.showReady();
				});

				Thread.sleep(1700);
				Platform.runLater(() -> {
					view.showGo();
				});
				Thread.sleep(700);
				Platform.runLater(() -> resumeGame());
			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();
	}

	public static GameController getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

	public double calculateDuration(Date start, Date end) {

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		String dateStart = format.format(start);
		String dateStop = format.format(end);

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		// in milliseconds
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;

		double result = Double.parseDouble(diffMinutes + "." + diffSeconds);
		return result;

	}

}
