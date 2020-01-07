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
import model.Block;
import model.Fruit;
import model.Game;
import model.Question;
import model.Segment;
import model.SysData;
import utilities.Constants;
import utilities.Direction;
import utilities.FruiteType;
import utilities.SoundEffects;
import view.GameSimulator;

public class GameController {

	private static GameController instance = null;
	private Game game;
	private GameSimulator view;
	private static AnimationTimer timer;
	private long then = System.nanoTime();
	private int appleTimer;
	private int bananaTimer;
	private int mouseTimer;

	boolean exitlegal = true;
	boolean enterLegal = true;

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

	private boolean snakeHitWall() {
		if ((game.getPlayGround().getSnake().getHead().getY() < 0)
				|| (game.getPlayGround().getSnake().getHead().getY() > Constants.GAME_HIGHT - 2)
				|| (game.getPlayGround().getSnake().getHead().getX() < 0)
				|| (game.getPlayGround().getSnake().getHead().getX() >= Constants.GAME_WIDTH - 2)) {
			return true;
		}
		return false;
	}

	private boolean snakeHitBody() { // use abs | | between head and body segment
		for (Segment s : game.getPlayGround().getSnake().getBody()) {
			if ((game.getPlayGround().getSnake().getHead().getX() == s.getX())
					&& (game.getPlayGround().getSnake().getHead().getY() == s.getY())
					&& !s.equals(game.getPlayGround().getSnake().getHead())) {
				return true;
			}

		}
		return false;
	}

	public void update() {

		updateSecretGate();
		if (snakeHitBody() || snakeHitWall()) {

			SoundEffects.playNegativeSound();
			pauseGame();
			game.setLives(game.getLives() - 1);
			MainPageController.getInstance().updateLives(game.getLives());
			game.getPlayGround().setHit(true);
			game.getPlayGround().getSnake().setDirection(Direction.LEFT);

			if (game.getLives() == 0) {
				game.setDuration(calculateDuration(game.getDate(), new Date()));
				game.setOver(true);
				SoundEffects.stopSound();
				SoundEffects.playGameOverSound();
				SysData.addGame(game);
				SysData.Save();
				Thread thread = new Thread(() -> {
					try {

						Thread.sleep(3 * 1000); // waiting
						Platform.runLater(() -> {
							try {

								FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/RatingPage.fxml"));
								RatingPageController rpc = new RatingPageController(true);
								fxmlLoader.setController(rpc);
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

						Thread.sleep(1000);
						Platform.runLater(() -> {
							game.getPlayGround().setHit(false);
							int ipx = Constants.GAME_WIDTH / 2;
							int ipy = Constants.GAME_HIGHT / 2;
							for (Segment s : game.getPlayGround().getSnake().getBody()) {
								s.setX(ipx++);
								s.setY(ipy);
							}
							resumeGame();

						});

					} catch (Exception exc) {
						throw new Error("Unexpected interruption");
					}
				});
				thread.start();
			}
		}

		for (Segment segment : game.getPlayGround().getSnake().getBody()) // update snake logical pos
			segment.move();

		updateSecretGateAfterMove();

		if (!game.getPlayGround().getMouse().isEaten()) {
			game.getPlayGround().getMouse().update(); // update mouse logical pos
		} else {
			mouseTimer++;
			if (mouseTimer == 1920) { // 1920 iteration = 1 min
				game.addEatenObject(game.getPlayGround().getMouse());
				game.getPlayGround().addMouse();
				mouseTimer = 0;
			}

		}

		if (mouseWasEaten()) {
			SoundEffects.playBubbleSound();
			game.getPlayGround().getMouse().setEaten(true);
			game.addEatenObject(game.getPlayGround().getMouse());
			MainPageController.getInstance().updateScore(game.getScore());
			game.getPlayGround().getSnake().addSegment();
			game.getPlayGround().getSnake().addSegment();
			if (game.getLives() < 3) {
				game.setLives(game.getLives() + 1);
				MainPageController.getInstance().updateLives(game.getLives());

			}

		}

		if (game.getPlayGround().getFruits().get(FruiteType.APPLE).isEaten()) {
			appleTimer++;
			if (appleTimer == 160) {

				game.getPlayGround().addFruit(game.getPlayGround().getFruits().get(FruiteType.APPLE).getType());
				appleTimer = 0;
			}
		}
		if (game.getPlayGround().getFruits().get(FruiteType.BANANA).isEaten()) {
			bananaTimer++;
			if (bananaTimer == 320) { // updating the animation timer 320 times = 10 seconds delay

				game.getPlayGround().addFruit(game.getPlayGround().getFruits().get(FruiteType.BANANA).getType());
				bananaTimer = 0;
			}
		}
		if (game.getPlayGround().getFruits().get(FruiteType.PEAR).isEaten()) {
			game.getPlayGround().addFruit(game.getPlayGround().getFruits().get(FruiteType.PEAR).getType());

		}

		for (Fruit fruit : game.getPlayGround().getFruits().values()) { // Iterate over fruits and check their state

			if (snakeHit(fruit) && fruit.isEaten() == false) {

				game.addEatenObject(fruit);
				fruit.setEaten(true);
				MainPageController.getInstance().updateScore(game.getScore());
				SoundEffects.playBubbleSound();
				game.getPlayGround().getSnake().addSegment();
			}

		}

		for (Question question : game.getPlayGround().getQuestions().values()) {

			if (snakeHit(question) && question.isEaten() == false) {
				SoundEffects.playQuestionStartSound();
				pauseGame();
				game.getPlayGround().getSnake().addSegment();
				game.addEatenObject(question);
				question.setEaten(true);

				Thread thread = new Thread(() -> {
					try {

						Platform.runLater(() -> {
							try {

								FXMLLoader fxmlLoader = new FXMLLoader(
										getClass().getResource("/view/QuestionPage.fxml"));
								fxmlLoader.setController(new QuestionPageController(question));
								Parent root = (Parent) fxmlLoader.load();
								Stage stage = new Stage();
								stage.initModality(Modality.APPLICATION_MODAL);
								Scene scene = new Scene(root);
								stage.setScene(scene);
								stage.initStyle(StageStyle.TRANSPARENT);
								scene.setFill(Color.TRANSPARENT);
								stage.show();
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

		}
		view.render(); // refresh view

	}

	private void updateSecretGateAfterMove() {
		for (Segment s : game.getPlayGround().getSnake().getBody()) {

			if ((Math.abs(game.getPlayGround().getSecretGate().getExitX() - s.getX()) >= 0
					&& Math.abs(game.getPlayGround().getSecretGate().getExitX() - s.getX()) < 2)
					&& (Math.abs(game.getPlayGround().getSecretGate().getExitY() - s.getY()) >= 0
							&& Math.abs(game.getPlayGround().getSecretGate().getExitY() - s.getY()) < 2)) {
				exitlegal = false;
			}

			if ((Math.abs(game.getPlayGround().getSecretGate().getEnterX() - s.getX()) >= 0
					&& Math.abs(game.getPlayGround().getSecretGate().getEnterX() - s.getX()) < 2)
					&& (Math.abs(game.getPlayGround().getSecretGate().getEnterY() - s.getY()) >= 0
							&& Math.abs(game.getPlayGround().getSecretGate().getEnterY() - s.getY()) < 2)) {
				enterLegal = false;
			}

		}
	}

	private void updateSecretGate() {
		if ((Math
				.abs(game.getPlayGround().getSecretGate().getExitX() - game.getPlayGround().getSnake().getBody()
						.get(game.getPlayGround().getSnake().getBody().size() - 1).getX()) >= 0
				&& Math.abs(game.getPlayGround().getSecretGate().getExitX() - game.getPlayGround().getSnake().getBody()
						.get(game.getPlayGround().getSnake().getBody().size() - 1).getX()) < 2)
				&& (Math.abs(game.getPlayGround().getSecretGate().getExitY() - game.getPlayGround().getSnake().getBody()
						.get(game.getPlayGround().getSnake().getBody().size() - 1).getY()) >= 0
						&& Math.abs(game.getPlayGround().getSecretGate().getExitY() - game.getPlayGround().getSnake()
								.getBody().get(game.getPlayGround().getSnake().getBody().size() - 1).getY()) < 2)) {
			exitlegal = true;
			enterLegal = true;

		}

		if ((Math
				.abs(game.getPlayGround().getSecretGate().getEnterX() - game.getPlayGround().getSnake().getBody()
						.get(game.getPlayGround().getSnake().getBody().size() - 1).getX()) >= 0
				&& Math.abs(game.getPlayGround().getSecretGate().getEnterX() - game.getPlayGround().getSnake().getBody()
						.get(game.getPlayGround().getSnake().getBody().size() - 1).getX()) < 2)
				&& (Math.abs(game.getPlayGround().getSecretGate().getEnterY() - game.getPlayGround().getSnake()
						.getBody().get(game.getPlayGround().getSnake().getBody().size() - 1).getY()) >= 0
						&& Math.abs(game.getPlayGround().getSecretGate().getEnterY() - game.getPlayGround().getSnake()
								.getBody().get(game.getPlayGround().getSnake().getBody().size() - 1).getY()) < 2)) {
			enterLegal = true;
			exitlegal = true;

		}

		if ((Math
				.abs(game.getPlayGround().getSecretGate().getEnterX()
						- game.getPlayGround().getSnake().getHead().getX()) >= 0
				&& Math.abs(game.getPlayGround().getSecretGate().getEnterX()
						- game.getPlayGround().getSnake().getHead().getX()) < 2)
				&& (Math.abs(game.getPlayGround().getSecretGate().getEnterY()
						- game.getPlayGround().getSnake().getHead().getY()) >= 0
						&& Math.abs(game.getPlayGround().getSecretGate().getEnterY()
								- game.getPlayGround().getSnake().getHead().getY()) < 2)) {

			if (exitlegal) {
				game.getPlayGround().getSnake().getHead().setX(game.getPlayGround().getSecretGate().getExitX());
				game.getPlayGround().getSnake().getHead().setY(game.getPlayGround().getSecretGate().getExitY());
			}

		} else {

			if ((Math
					.abs(game.getPlayGround().getSecretGate().getExitX()
							- game.getPlayGround().getSnake().getHead().getX()) >= 0
					&& Math.abs(game.getPlayGround().getSecretGate().getExitX()
							- game.getPlayGround().getSnake().getHead().getX()) < 2)
					&& (Math.abs(game.getPlayGround().getSecretGate().getExitY()
							- game.getPlayGround().getSnake().getHead().getY()) >= 0
							&& Math.abs(game.getPlayGround().getSecretGate().getExitY()
									- game.getPlayGround().getSnake().getHead().getY()) < 2)) {

				if (enterLegal) {
					game.getPlayGround().getSnake().getHead().setX(game.getPlayGround().getSecretGate().getEnterX());
					game.getPlayGround().getSnake().getHead().setY(game.getPlayGround().getSecretGate().getEnterY());

				}
			}

		}
	}

	public void changeDiriction(Direction dir) {

		game.getPlayGround().getSnake().setDirection(dir);

	}

	public Boolean snakeHit(Block block) {

		if ((Math.abs(block.getX() - game.getPlayGround().getSnake().getHead().getX()) >= 0
				&& Math.abs(block.getX() - game.getPlayGround().getSnake().getHead().getX()) < 2)
				&& (Math.abs(block.getY() - game.getPlayGround().getSnake().getHead().getY()) >= 0
						&& Math.abs(block.getY() - game.getPlayGround().getSnake().getHead().getY()) < 2))

			return true;

		return false;

	}

	public boolean mouseWasEaten() {

		if (!game.getPlayGround().getMouse().isEaten() && ((Math
				.abs(game.getPlayGround().getMouse().getX() - game.getPlayGround().getSnake().getHead().getX()) >= 0
				&& Math.abs(
						game.getPlayGround().getMouse().getX() - game.getPlayGround().getSnake().getHead().getX()) < 2)
				&& (Math.abs(
						game.getPlayGround().getMouse().getY() - game.getPlayGround().getSnake().getHead().getY()) >= 0
						&& Math.abs(game.getPlayGround().getMouse().getY()
								- game.getPlayGround().getSnake().getHead().getY()) < 2)))

			return true;

		return false;

	}

	public void pauseGame() {
		game.setPaused(true);
		timer.stop();
	}

	public void resumeGame() {
		game.setPaused(false);
		timer.start();
	}

	public void startTimer() {

		game.setPaused(true);
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

	public void restartGame() {

		appleTimer = 0;
		bananaTimer = 0;
		mouseTimer = 0;
		game.restart();
		view.reset();
		MainPageController.getInstance().updateScore(0);
		MainPageController.getInstance().initLives();
		startTimer();

	}

}
